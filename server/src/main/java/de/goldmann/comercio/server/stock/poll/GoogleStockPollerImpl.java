package de.goldmann.comercio.server.stock.poll;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.cache.HttpCacheContext;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.comercio.domain.order.LeadingIndex;
import de.goldmann.comercio.domain.order.Stock;
import de.goldmann.comercio.domain.service.IndexRepository;
import de.goldmann.comercio.domain.service.StockRepository;

//@Component
public class GoogleStockPollerImpl implements StockPoller {
	private static final Logger logger = LoggerFactory.getLogger(GoogleStockPollerImpl.class);

	private CloseableHttpClient httpClient;

	private HttpCacheContext context;

	private ObjectMapper mapper;

	private StockUrlController stockUrlController;

	private StockPublisher stockPublisher;

	private StockRepository stockRepo;

	private IndexRepository indexRepo;

	@Autowired
	public GoogleStockPollerImpl(StockUrlController stockUrlController, StockPublisher stockPublisher, StockRepository stockRepo,
			IndexRepository indexRepo) {

		CacheConfig cacheConfig = CacheConfig.custom().setMaxCacheEntries(MAX_CACHE_ENTRIES)
				.setMaxObjectSize(MAX_OBJECT_SIZE).build();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
				.setSocketTimeout(SOCKET_TIMEOUT).build();
		this.httpClient = CachingHttpClients.custom().setCacheConfig(cacheConfig).setDefaultRequestConfig(requestConfig)
				.build();
		this.stockUrlController = stockUrlController;
		this.stockPublisher = stockPublisher;
		this.context = HttpCacheContext.create();
		this.mapper = new ObjectMapper();
		this.stockRepo = stockRepo;
		this.indexRepo = indexRepo;
	}

	@Override
	@Scheduled(fixedRate = POLL_INTERVALL)
	public void poll() throws Exception {
		final HttpGet httpclient = new HttpGet(stockUrlController.getUrl());
		CloseableHttpResponse response = null;
		String jsonResponse = null;
		try {

			while (true) {
				response = httpClient.execute(httpclient, context);
				final HttpEntity entity = response.getEntity();
				jsonResponse = EntityUtils.toString(entity).replace("// ", "").replace("\n", "");
				final List<StockInfoGoogle> infos = mapper.readValue(jsonResponse, new TypeReference<List<StockInfoGoogle>>() {
				});
				for (StockInfoGoogle info : infos) {
					String key = info.getKey();
					final Stock stock = stockRepo.findBySearchKey(key);
					if (stock != null) {
						info.setIndexId(stock.getLeadingIndex().getId());
						info.setName(stock.getName());
						stockPublisher.publish(info);
					} else {
						final LeadingIndex index = indexRepo.findBySearchKey(key);
						if (index != null) {
							info.setIndexId(index.getId());
							info.setName(index.getName());
							info.setIndex(true);
							stockPublisher.publish(info);
						} else {
							logger.warn("Wether found Stock neither index for key '" + key + "'");
						}
					}
				}

				response.close();
				response = null;
			}

		} catch (Exception e) {
			if (jsonResponse != null && e instanceof JsonParseException) {
				logger.error("Fehler beim Lesen der Daten von " + stockUrlController.getUrl(), e);
				logger.info("JSON-Respones:" + jsonResponse);
			}
			logger.error("Fehler beim Lesen der Daten von " + stockUrlController.getUrl(), e);
			throw e;
		} finally {
			if (response != null) {
				try {
					response.close();
					response = null;
				} catch (IOException e) {
				}
			}
		}
	}
}
