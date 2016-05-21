package de.goldmann.comercio.client.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import static de.goldmann.comercio.domain.AppQeues.*;

@Component
public class UsersListRequestSender
{

    private JmsTemplate jmsTemplate;

    @Autowired
    public UsersListRequestSender(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage()
    {
        final MessageCreator messageCreator = new MessageCreator()
        {
            @Override
            public Message createMessage(Session session) throws JMSException
            {
                final TextMessage createTextMessage = session.createTextMessage("Load users");
                createTextMessage.setJMSReplyTo(new ActiveMQQueue(USERS_LIST_RESPONSE));
                return createTextMessage;
            }
        };
        jmsTemplate.send(USERS_LIST_REQUEST, messageCreator);
    }
}
