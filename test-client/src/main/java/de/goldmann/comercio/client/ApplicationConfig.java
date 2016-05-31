package de.goldmann.comercio.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import de.goldmann.comercio.client.jms.OrdersAddResponseListener;
import de.goldmann.comercio.client.jms.UserListResponseListener;

@Configuration
public class ApplicationConfig
{
    private final String              brokerUrl = "tcp://localhost:61616";

    @Autowired
    private UserListResponseListener  userListResponseListener;

    @Autowired
    private OrdersAddResponseListener ordersAddResponseListener;

	// @Bean
	// public DefaultMessageListenerContainer ordersAddRequestContainer()
	// {
	// final DefaultMessageListenerContainer container = new
	// DefaultMessageListenerContainer();
	// container.setConnectionFactory(pooledFactory());
	// container.setDestination(new
	// ActiveMQQueue(AppQeues.USERS_LIST_RESPONSE));
	// container.setMessageListener(userListResponseListener);
	// return container;
	// }

    @Bean
    public PooledConnectionFactory pooledFactory()
    {
        return new PooledConnectionFactory(new ActiveMQConnectionFactory(brokerUrl));
    }

    /**
     * SchedulerFactoryBean die von der Klasse {@link ChecksTimerHandler} verwendet wird.
     * 
     * @return eine Instanz von {@link SchedulerFactoryBean}.
     */
    @Bean
    public SchedulerFactoryBean scheduleFactory()
    {
        return new SchedulerFactoryBean();
    }

    @Bean
    public JmsTemplate template()
    {
        return new JmsTemplate(pooledFactory());
    }

	// @Bean
	// public DefaultMessageListenerContainer usersListContainer()
	// {
	// DefaultMessageListenerContainer container = new
	// DefaultMessageListenerContainer();
	// container.setConnectionFactory(pooledFactory());
	// container.setDestination(new ActiveMQQueue(ORDERS_ADD_RESPONSE));
	// container.setMessageListener(ordersAddResponseListener);
	// return container;
	// }

}