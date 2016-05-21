package de.goldmann.comercio.domain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.comercio.domain.SimpleConfiguration;
import de.goldmann.comercio.domain.order.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleConfiguration.class)
public class StockRepositoryTest
{
    @Autowired
    private StockRepository repo;

    @Test
    public void testPersist()
    {
        Stock stockOne = buildOrder("Gold", "Gold");
        Stock storedOrderOne = repo.save(stockOne);
        System.out.println(storedOrderOne.getId());

        Stock stockTwo = buildOrder("Silber", "Silber");
        Stock storedOrderTwo = repo.save(stockTwo);
        System.out.println(storedOrderTwo.getId());
    }

    private Stock buildOrder(String name, String yahooSearchKey)
    {
        Stock stock = new Stock();
        stock.setName(name);
        stock.setCurrency("USD");
        stock.setYahooSearchKey(yahooSearchKey);
        return stock;
    }

}
