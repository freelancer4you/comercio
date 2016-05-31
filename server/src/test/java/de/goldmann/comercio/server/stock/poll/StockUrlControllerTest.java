package de.goldmann.comercio.server.stock.poll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.comercio.ComercioServerApplication;
import de.goldmann.comercio.server.jms.JmsCleanUp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComercioServerApplication.class)
public class StockUrlControllerTest extends JmsCleanUp {

	@Autowired
	private StockUrlController controller;


	@Test
	public void testGetUrl() {
		System.out.println(controller.getUrl());
	}

	@Test
	public void testGetSymbols() {
		System.out.println(controller.getSymbols());
	}

}
