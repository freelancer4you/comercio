package de.goldmann.comercio.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.comercio.domain.SimpleConfiguration;
import de.goldmann.comercio.domain.order.LeadingIndex;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleConfiguration.class)
public class IndexRepositoryTest {

	@Autowired
	private IndexRepository indexRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		List<LeadingIndex> all = indexRepository.findAll();
		assertNotNull(all);
		assertEquals(2, all.size());
		LeadingIndex dax = indexRepository.findByName("DAX");
		assertNotNull(dax);
		assertEquals(30, dax.getStocks().size());
		LeadingIndex dow = indexRepository.findByName("DOW JONES");
		assertNotNull(dow);
		assertEquals(30, dow.getStocks().size());
	}

}
