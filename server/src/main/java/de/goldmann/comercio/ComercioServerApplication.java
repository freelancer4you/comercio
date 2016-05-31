package de.goldmann.comercio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ComercioServerApplication implements CommandLineRunner
{
    private static final Logger logger = LoggerFactory.getLogger(ComercioServerApplication.class);

    @Override
    public void run(String... args) throws Exception
    {
        logger.info("Starting ...");
    }

    // private List<UserRow> mapUsersToRows(List<User> users)
    // {
    // final List<UserRow> rows = new ArrayList<UserRow>();
    // for (User user : users)
    // {
    // rows.add(new UserRow(user.getId(), user.getAccount().getLogin()));
    // }
    // return rows;
    // }

    // private List<OrderRow> buildOrdersResult(final List<Order> storedOrdes)
    // {
    // final List<OrderRow> rows = new ArrayList<OrderRow>();
    // for (Order order : storedOrdes)
    // {
    // if (order.isOpen())
    // {
    // final User user = order.getUser();
    // OrderRow row = new OrderRow(order.getId(), user.getAccount().getLogin(),
    // order.getDirection(), order.getStamp().getTime());
    // rows.add(row);
    // }
    // }
    // return rows;
    // }

    public static void main(String[] args)
    {
		// Optionally remove existing handlers attached to j.u.l root logger
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // (since SLF4J 1.6.5)

		// add SLF4JBridgeHandler to j.u.l's root logger, should be done once
		// during
		// the initialization phase of your application
		SLF4JBridgeHandler.install();
        SpringApplication.run(ComercioServerApplication.class, args);
    }

}
