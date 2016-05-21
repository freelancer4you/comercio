package de.goldmann.comercio.client;

import static de.goldmann.comercio.domain.AppQeues.ORDERS_ADD_REQUEST;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.domain.AppQeues;
import de.goldmann.comercio.domain.order.OrderDirection;
import de.goldmann.comercio.transfer.down.UserRow;
import de.goldmann.comercio.transfer.up.OrderRow;

public class UserOrderJobImpl implements UserOrderJob
{
    private UserRow     user;
    private long        checkInterVall = 60000;

    private JmsTemplate jmsTemplate;

    public UserOrderJobImpl(UserRow user, JmsTemplate jmsTemplate)
    {
        this.user = user;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void execute()
    {
        try
        {

            final Path path = Paths.get(ClientUtil.USER_CONFIG_PATH + user.getLogin() + ".txt");
            final List<String> lines = Files.readAllLines(path);
            int lineNumber = 0;
            for (String line : lines)
            {
                final String[] splitteLine = line.split(";");

                if (lineHasNoOrderId(splitteLine))
                {
                    System.out.println(user.getLogin() + " executing an order ...");
                    insertOrder(user, splitteLine, lineNumber);
                }

                lineNumber++;
            }

        }
        catch (IOException | JMSException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public long getInterval()
    {
        return checkInterVall;
    }

    @Override
    public String getName()
    {
        return user.getLogin();
    }

    @Override
    public long getStartAfter()
    {
        return 10;
    }

    @Override
    public String getTriggerName()
    {
        return user.getLogin() + "_trigger";
    }

    private void insertOrder(UserRow user, String[] splitteLine, int lineNumber) throws JMSException,
            JsonProcessingException
    {
        final String stockName = splitteLine[0];
        final double stockPrice = Double.parseDouble(splitteLine[1]);
        final double amountInput = Double.parseDouble(splitteLine[3]);
        final OrderDirection direction = OrderDirection.valueOf(splitteLine[2]);
        final int multiplikator = 10;

        final OrderRow row = new OrderRow(user.getUserId(), stockName, stockPrice, amountInput, direction,
                multiplikator, lineNumber);

        final String orderJson = new ObjectMapper().writeValueAsString(row);

        final MessageCreator messageCreator = new MessageCreator()
        {
            @Override
            public Message createMessage(Session session) throws JMSException
            {
                final TextMessage createTextMessage = session.createTextMessage(orderJson);
                createTextMessage.setJMSReplyTo(new ActiveMQQueue(AppQeues.ORDERS_ADD_RESPONSE));
                return createTextMessage;
            }
        };

        jmsTemplate.send(ORDERS_ADD_REQUEST, messageCreator);
    }

    public static boolean lineHasNoOrderId(String[] splitteLine)
    {
        return splitteLine.length <= 4;
    }
}
