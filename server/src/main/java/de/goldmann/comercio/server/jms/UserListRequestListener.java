package de.goldmann.comercio.server.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.domain.User;
import de.goldmann.comercio.domain.service.UserRepository;
import de.goldmann.comercio.transfer.down.UserRow;

/**
 * Lauscht an der Qeueu '/queue/users_list_request' lädt alle User und sendet diese an den Client.
 * 
 * @author goldmana
 *
 */
// @Component
public class UserListRequestListener implements MessageListener
{
    private JmsTemplate    jmsTemplate;
    private UserRepository userRepo;

    @Autowired
    public UserListRequestListener(UserRepository userRepo, JmsTemplate jmsTemplate)
    {
        this.userRepo = userRepo;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            final Destination jmsReplyTo = message.getJMSReplyTo();
            if (jmsReplyTo != null)
            {
                final List<User> users = userRepo.findAll();
                final ObjectMapper mapper = new ObjectMapper();

                final String usersJson = mapper.writeValueAsString(mapUsersToRows(users));

                final MessageCreator messageCreator = new MessageCreator()
                {
                    @Override
                    public Message createMessage(Session session) throws JMSException
                    {
                        final TextMessage createTextMessage = session.createTextMessage(usersJson);
                        return createTextMessage;
                    }
                };
                System.out.println("Send message to " + jmsReplyTo);
                jmsTemplate.send(jmsReplyTo, messageCreator);
            }
            else
            {
                System.out.println("Message does not have JMSReplyTo:" + message);
            }

        }
        catch (JsonProcessingException | JmsException | JMSException e)
        {
            e.printStackTrace();
        }
    }

    private List<UserRow> mapUsersToRows(List<User> users)
    {
        final List<UserRow> rows = new ArrayList<UserRow>();
        for (User user : users)
        {
            rows.add(new UserRow(user.getId(), user.getAccount().getLogin()));
        }
        return rows;
    }
}
