package de.goldmann.comercio.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.comercio.ComercioServerApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComercioServerApplication.class)
public class ComercioServerApplicationTests
{

    @Test
    public void test()
    {
        System.out.println("Testing..");
    }
}