package de.goldmann.comercio.server.stock.poll;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.goldmann.comercio.domain.order.LeadingIndex;
import de.goldmann.comercio.domain.service.IndexRepository;
import de.goldmann.comercio.domain.service.StockRepository;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Component
public class YahooStockPollerImpl implements StockPoller {
	private static final Logger logger = LoggerFactory.getLogger(GoogleStockPollerImpl.class);

	private StockUrlController stockUrlController;

	private StockPublisher stockPublisher;

	private StockRepository stockRepo;

	private IndexRepository indexRepo;

	@Autowired
	public YahooStockPollerImpl(StockUrlController stockUrlController, StockPublisher stockPublisher,
			StockRepository stockRepo, IndexRepository indexRepo) {

		this.stockUrlController = stockUrlController;
		this.stockPublisher = stockPublisher;
		this.stockRepo = stockRepo;
		this.indexRepo = indexRepo;
	}

	@Override
	@Scheduled(fixedRate = POLL_INTERVALL)
	public void poll() throws Exception {

		final String[] symbols = stockUrlController.getSymbols();
		final Map<String, Stock> stocks = YahooFinance.get(symbols); // single
																		// request
		final Iterator<Entry<String, Stock>> it = stocks.entrySet().iterator();
		while (it.hasNext()) {
			final Stock stock = it.next().getValue();
			final StockInfo info = new StockInfoYahoo(stock.getName(), stock.getSymbol(), stock.getQuote().getPrice());

			final String key = stock.getSymbol();
			final de.goldmann.comercio.domain.order.Stock storedStock = stockRepo.findBySearchKey(key);
			if (storedStock != null) {
				info.setIndexId(storedStock.getLeadingIndex().getId());
				stockPublisher.publish(info);
			} else {
				final LeadingIndex index = indexRepo.findBySearchKey(key);
				if (index != null) {
					info.setIndexId(index.getId());
					info.setIndex(true);
					stockPublisher.publish(info);
				} else {
					logger.warn("Wether found Stock neither index for key '" + key + "'");
				}
			}
		}
	}

}
