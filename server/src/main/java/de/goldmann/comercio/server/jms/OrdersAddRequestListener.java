package de.goldmann.comercio.server.jms;

import java.io.IOException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.domain.AppQeues;
import de.goldmann.comercio.domain.User;
import de.goldmann.comercio.domain.order.Order;
import de.goldmann.comercio.domain.order.Stock;
import de.goldmann.comercio.domain.service.OrderRepository;
import de.goldmann.comercio.domain.service.StockRepository;
import de.goldmann.comercio.domain.service.UserRepository;
import de.goldmann.comercio.transfer.up.OrderRow;
import de.goldmann.comercio.utils.JmsUtils;

//@Component
public class OrdersAddRequestListener implements MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(OrdersAddRequestListener.class);

    private UserRepository      userRepo;

    private OrderRepository     orderRepo;

    private StockRepository     stockRepo;

    private JmsTemplate         jmsTemplate;

    @Autowired
    public OrdersAddRequestListener(UserRepository userRepo, OrderRepository orderRepo,
            StockRepository stockRepo, JmsTemplate jmsTemplate)
    {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.stockRepo = stockRepo;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                final Destination jmsReplyTo = message.getJMSReplyTo();
                final TextMessage txtMsg = (TextMessage) message;
                final String messageText = txtMsg.getText();
                if (jmsReplyTo != null)
                {

                    logger.debug("[" + jmsReplyTo + "] MessageContent:" + messageText);
                    final ObjectMapper mapper = new ObjectMapper();
                    final OrderRow row = mapper.readValue(messageText, new TypeReference<OrderRow>()
                    {});
                    final Stock stock = stockRepo.findByName(row.getStockName());
                    final User user = userRepo.findOne(row.getUserId());
                    final Order order = new Order();
                    order.setAmountInput(row.getAmountInput());
                    order.setUser(user);
                    order.setStock(stock);
                    order.setDirection(row.getDirection());
                    order.setStockPrice(row.getStockPrice());
                    order.setMultiplikator(row.getMultiplikator());
                    orderRepo.save(order);
                    user.getOrders().add(order);

                    final de.goldmann.comercio.transfer.down.OrderRow response = new de.goldmann.comercio.transfer.down.OrderRow(
                            order.getId(), user.getAccount().getLogin(), order.getStamp().getTime(),
                            row.getLineNumber());

                    final String responseStr = mapper.writeValueAsString(response);

                    jmsTemplate.send(jmsReplyTo, JmsUtils.generateJmsMessage(responseStr));
                }
                else
                {
                    System.out.println("jmsReplyTo was null for message:" + messageText);
                }
            }
            else
            {
                System.out.println("Unknown message typ:" + message);
            }
        }
        catch (JMSException | IOException e)
        {
            logger.error("Error while reading message from queue:" + AppQeues.ORDERS_ADD_REQUEST, e);
        }
    }
}
