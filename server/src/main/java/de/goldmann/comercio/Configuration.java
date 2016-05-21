package de.goldmann.comercio;

//import static de.goldmann.comercio.domain.AppQeues.ORDERS_ADD_REQUEST;
//import static de.goldmann.comercio.domain.AppQeues.USERS_LIST_REQUEST;
//
import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.xbean.BrokerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.listener.DefaultMessageListenerContainer;
//
//import de.goldmann.comercio.server.jms.OrdersAddRequestListener;
//import de.goldmann.comercio.server.jms.UserListRequestListener;

import de.goldmann.comercio.server.services.amq.AmqServices;

@org.springframework.context.annotation.Configuration
public class Configuration
{
    private static final Logger logger    = LoggerFactory.getLogger(Configuration.class);
    private final static String config    = "org/apache/activemq/broker.xml";
    private final String        brokerUrl = "tcp://localhost:61616";

    @Bean
    public AmqServices amqService()
    {
        return new AmqServices(pooledFactory(), jmsTemplate());
    }

    //
    // @Autowired
    // private UserListRequestListener userListRequestListener;
    //
    // @Autowired
    // private OrdersAddRequestListener ordersAddRequestListener;
    //
    @Bean
    public BrokerFactoryBean getBroker()
    {
        final BrokerFactoryBean brokerFactoryBean = new BrokerFactoryBean();
        final Resource resource = new ClassPathResource(config);
        brokerFactoryBean.setConfig(resource);
        brokerFactoryBean.setStart(true);

        return brokerFactoryBean;
    }

    //
    @Bean
    public JmsTemplate jmsTemplate()
    {
        return new JmsTemplate(pooledFactory());
    }

    //
    // @Bean
    // public DefaultMessageListenerContainer ordersAddRequestContainer()
    // {
    // final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
    // container.setConnectionFactory(pooledFactory());
    // container.setDestination(new ActiveMQQueue(ORDERS_ADD_REQUEST));
    // container.setMessageListener(ordersAddRequestListener);
    // return container;
    // }
    //
    @Bean
    public PooledConnectionFactory pooledFactory()
    {
        try
        {
            PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(
                    new ActiveMQConnectionFactory(brokerUrl));
            return pooledConnectionFactory;
        }
        catch (Exception e)
        {
            logger.error("Failed to Setup ConnectionFactory:", e);
            throw e;
        }
    }
    //
    // @Bean
    // public DefaultMessageListenerContainer usersListContainer()
    // {
    // DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
    // container.setConnectionFactory(pooledFactory());
    // container.setDestination(new ActiveMQQueue(USERS_LIST_REQUEST));
    // container.setMessageListener(userListRequestListener);
    // return container;
    // }

}
