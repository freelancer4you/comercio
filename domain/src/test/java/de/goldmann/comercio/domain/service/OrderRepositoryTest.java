package de.goldmann.comercio.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.comercio.domain.SimpleConfiguration;
import de.goldmann.comercio.domain.User;
import de.goldmann.comercio.domain.order.Order;
import de.goldmann.comercio.domain.order.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleConfiguration.class)
public class OrderRepositoryTest
{
    @Autowired
    private OrderRepository repo;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private UserRepository  userRepo;

    @Test
    public void testPersist()
    {
        final Stock stock = stockRepo.findOne(1L);
        assertNotNull(stock);
        assertEquals("DAX", stock.getName());

        User user = userRepo.findOne(1L);
        assertNotNull(user);
        assertEquals("Frodo", user.getFirstName());

        Order orderOne = buildOrder(stock, user);
        Order orderTwo = buildOrder(stock, user);

        repo.save(orderOne);
        repo.save(orderTwo);
        user.getOrders().add(orderOne);
        user.getOrders().add(orderTwo);

        // assertEquals(2, repo.findByUserId(user.getId()));
    }

    private Order buildOrder(final Stock stock, User user)
    {
        Order order = new Order();
        order.setUser(user);
        order.setStock(stock);
        return order;
    }

}
