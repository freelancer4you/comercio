package de.goldmann.comercio.server.stock.poll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.goldmann.comercio.domain.order.LeadingIndex;
import de.goldmann.comercio.domain.service.IndexRepository;

@Component
public class StockUrlController {

	private IndexRepository indexRepository;

	@Autowired
	public StockUrlController(IndexRepository indexRepository) {
		this.indexRepository = indexRepository;
	}

	public String getUrl() {
		final List<LeadingIndex> indices = indexRepository.findAll();
		final StringBuffer ticker = new StringBuffer();
		for (LeadingIndex leadingIndex : indices) {
			ticker.append(leadingIndex.getSearchKey() + ",");
			for (de.goldmann.comercio.domain.order.Stock stock : leadingIndex.getStocks()) {
				ticker.append(stock.getSearchKey() + ",");
			}
		}
		// String ticker = "BIT:BASF,NASDAQ:AAPL,INDEXDB:DAX,INDEXDJX:.DJI";
		return "http://finance.google.com/finance/info?client=ig&q=" + ticker;
	}

	public String[] getSymbols() {
		final List<LeadingIndex> indices = indexRepository.findAll();
		final List<String> symbols = new ArrayList<>();
		for (LeadingIndex leadingIndex : indices) {
			symbols.add(leadingIndex.getSearchKey());
			for (de.goldmann.comercio.domain.order.Stock stock : leadingIndex.getStocks()) {
				symbols.add(stock.getSearchKey());
			}
		}
		return symbols.toArray(new String[symbols.size()]);
	}

}
