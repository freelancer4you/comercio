package de.goldmann.comercio.server.services.amq;

import java.util.Collections;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.apache.activemq.advisory.DestinationEvent;
import org.apache.activemq.advisory.DestinationListener;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import de.goldmann.comercio.ComercioServerApplication;
import de.goldmann.comercio.utils.JmsUtils;

public class AmqServices
{
    private static final Logger     logger = LoggerFactory.getLogger(ComercioServerApplication.class);

    private PooledConnectionFactory factory;

    private JmsTemplate             jmsTemplate;

    public AmqServices(PooledConnectionFactory factory, JmsTemplate jmsTemplate)
    {
        this.factory = factory;
        this.jmsTemplate = jmsTemplate;
    }

    public Set<ActiveMQQueue> listQueues()
    {
        Connection connection = null;
        try
        {
            connection = factory.createConnection();
            connection.start();
            if (connection instanceof PooledConnection)
            {
                return getDestinationSources(connection).getQueues();
            }

        }
        catch (JMSException e)
        {
            logger.error("Error while loading queues:", e);
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (JMSException e)
            {}
        }
        return Collections.emptySet();
    }

    public Set<ActiveMQTopic> listTopics()
    {
        Connection connection = null;
        try
        {
            connection = factory.createConnection();
            connection.start();
            if (connection instanceof PooledConnection)
            {
                return getDestinationSources(connection).getTopics();
            }

        }
        catch (JMSException e)
        {
            logger.error("Error while loading topics:", e);
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (JMSException e)
            {}
        }
        return Collections.emptySet();
    }

	public String sendMessage(String destinationName, String destType, String msg)
    {
        // System.out.println("dest:" + dest);
        // System.out.println("destType:" + destType);
        // System.out.println("msg:" + msg);
		// String destPrefix = "/queue/";
		Destination dest = null;
		if ("Topic".equals(destType))
        {
			dest = new ActiveMQTopic(destinationName);
		} else if ("Queue".equals(destType)) {
			dest = new ActiveMQQueue(destinationName);
        }

		jmsTemplate.send(dest, JmsUtils.generateJmsMessage(msg));
		return "Message send to '" + dest + "'";
    }

    private DestinationSource getDestinationSources(Connection connection) throws JMSException
    {
        final DestinationSource destinationSource = ((PooledConnection) connection).getDestinationSource();
		destinationSource.setDestinationListener(new DestinationListener() {

			@Override
			public void onDestinationEvent(DestinationEvent arg0) {
				System.out.println(arg0);
			}
		});
        destinationSource.start();
        return destinationSource;
    }

}
