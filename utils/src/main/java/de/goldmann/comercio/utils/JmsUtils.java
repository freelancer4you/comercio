package de.goldmann.comercio.utils;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.MessageCreator;

public class JmsUtils
{
    public static MessageCreator generateJmsMessage(final String message)
    {
        return new MessageCreator()
        {
            @Override
            public Message createMessage(Session session) throws JMSException
            {
                final TextMessage createTextMessage = session.createTextMessage(message);
                return createTextMessage;
            }
        };
    }

    public static MessageCreator generateJmsMessage(final String message, final Destination replyTo)
    {
        return new MessageCreator()
        {
            @Override
            public Message createMessage(Session session) throws JMSException
            {
                final TextMessage createTextMessage = session.createTextMessage(message);
                createTextMessage.setJMSReplyTo(replyTo);
                return createTextMessage;
            }
        };
    }
}
