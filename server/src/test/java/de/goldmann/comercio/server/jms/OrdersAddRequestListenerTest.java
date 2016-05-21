package de.goldmann.comercio.server.jms;

import static de.goldmann.comercio.domain.AppQeues.ORDERS_ADD_REQUEST;
import static de.goldmann.comercio.domain.AppQeues.ORDERS_ADD_RESPONSE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.ComercioServerApplication;
import de.goldmann.comercio.domain.order.Order;
import de.goldmann.comercio.domain.order.OrderDirection;
import de.goldmann.comercio.domain.service.OrderRepository;
import de.goldmann.comercio.transfer.up.OrderRow;
import de.goldmann.comercio.utils.JmsUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComercioServerApplication.class)
public class OrdersAddRequestListenerTest extends JmsCleanUp
{
    @Autowired
    private JmsTemplate             jmsTemplate;

    @Autowired
    private OrderRepository         orderRepo;

    @Autowired
    private PooledConnectionFactory factory;

    @Before
    public void setup()
    {}

    @Test
    public void testOnMessage() throws JsonProcessingException
    {
        // Setup
        final ObjectMapper mapper = new ObjectMapper();
        final double amountInput = 50.0;
        final double stockPrice = 10000.0;
        final String stockName = "DAX";
        final int multiplikator = 10;
        final OrderRow order = new OrderRow(1, stockName, stockPrice, amountInput, OrderDirection.LONG,
                multiplikator, 0);
        final String requestStr = mapper.writeValueAsString(order);

        final MessageCreator messageCreator = JmsUtils.generateJmsMessage(requestStr, new ActiveMQQueue(
                ORDERS_ADD_RESPONSE));

        // Execute
        jmsTemplate.send(ORDERS_ADD_REQUEST, messageCreator);
        // Wait for response
        jmsTemplate.receive(ORDERS_ADD_RESPONSE);

        // Verify
        final List<Order> orders = orderRepo.findAll();
        assertNotNull(orders);
        assertEquals(1, orders.size());
        final Order storedorder = orders.get(0);
        assertNotNull(storedorder);
        assertEquals(amountInput, storedorder.getAmountInput(), 2.0);
        assertEquals(stockPrice, storedorder.getStockPrice(), 2.0);
        assertEquals(stockName, storedorder.getStock().getName());
        assertEquals(multiplikator, storedorder.getMultiplikator());
    }

}
