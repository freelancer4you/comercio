package de.goldmann.comercio.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import de.goldmann.comercio.client.jms.UsersListRequestSender;

@SpringBootApplication
public class TestClientApp implements CommandLineRunner
{

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception
    {
        UsersListRequestSender sender = context.getBean(UsersListRequestSender.class);

        // public message to receive all users from server
        sender.sendMessage();

    }

    public static void main(String[] args)
    {
        SpringApplication.run(TestClientApp.class, args);
    }

}
