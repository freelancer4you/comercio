package de.goldmann.comercio.client.jms;

import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.client.AppJob;
import de.goldmann.comercio.client.UserOrderJobImpl;
import de.goldmann.comercio.transfer.down.UserRow;

@Component
public class UserListResponseListener implements MessageListener
{
    private Scheduler   scheduler;

    private JmsTemplate jmsTemplate;

    @Autowired
    public UserListResponseListener(Scheduler scheduler, JmsTemplate jmsTemplate)
    {
        this.scheduler = scheduler;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                final String usersMessage = ((TextMessage) message).getText();
                final ObjectMapper mapper = new ObjectMapper();
                final List<UserRow> users = mapper.readValue(usersMessage, new TypeReference<List<UserRow>>()
                {});

                for (UserRow userRow : users)
                {
                    // Register a Job for every User
                    scheduleUserJob(new UserOrderJobImpl(userRow, jmsTemplate));
                }
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    private void scheduleUserJob(AppJob job) throws ClassNotFoundException, NoSuchMethodException,
            SchedulerException
    {
        final MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(job);
        jobDetail.setTargetMethod("execute");
        jobDetail.setName(job.getName());
        jobDetail.setConcurrent(false);
        jobDetail.afterPropertiesSet();

        final SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setBeanName(job.getTriggerName());
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setRepeatInterval(job.getInterval());
        trigger.setStartDelay(job.getStartAfter());

        trigger.afterPropertiesSet();
        scheduler.scheduleJob(jobDetail.getObject(), trigger.getObject());

    }

}
