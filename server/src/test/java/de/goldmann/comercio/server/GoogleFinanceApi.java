package de.goldmann.comercio.server;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.cache.HttpCacheContext;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GoogleFinanceApi {


	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String url = "https://www.google.com/finance/getprices?q=DAX&x=INDEXDB&i=120&p=15m";
		String index = "DAX";
		String stockId = "AAPL";
		// Close Price / Pre-Market
		//url = "http://finance.google.com/finance/info?client=" + index + "&q=" + stockId;
		url = "http://finance.google.com/finance/info?client=" + index + "&q=" + stockId;
		String ticker = "NASDAQ:AAPL";// BIT:BASF
		//url = "http://finance.google.com/finance/info?client=ig&q=" + ticker;
		// http://finance.google.com/finance/info?client=ig&q=BIT:BASF,NASDAQ:AAPL
		url = "http://finance.google.com/finance/info?client=ig&q=BIT:BASF,NASDAQ:AAPL,INDEXDB:DAX,INDEXDJX:.DJI";

		// HttpGet get = new HttpGet(url);
		// CloseableHttpClient httpclient = HttpClients.createDefault();

		CacheConfig cacheConfig = CacheConfig.custom().setMaxCacheEntries(1000).setMaxObjectSize(8192).build();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
		CloseableHttpClient cachingClient = CachingHttpClients.custom().setCacheConfig(cacheConfig)
				.setDefaultRequestConfig(requestConfig).build();

		HttpCacheContext context = HttpCacheContext.create();
		HttpGet httpclient = new HttpGet(url);
		CloseableHttpResponse response = null;// cachingClient.execute(httpclient,
												// context);
		
		// CloseableHttpResponse response = null;
		final ObjectMapper mapper = new ObjectMapper();

		try {

			while (true) {
				// response = httpclient.execute(get);
				response = cachingClient.execute(httpclient, context);
				final HttpEntity entity = response.getEntity();
				final String jsonResponse = EntityUtils.toString(entity).replace("// ", "").replace("\n", "");
				// System.out.println(jsonResponse);

				// Close Price / Pre-Market
				// Map<String, String> jsonMap = mapper.readValue(jsonResponse,
				// new TypeReference<Map<String, String>>() {
				// });
				// Close Price / Pre-Market
				// String t = jsonMap.get("elt");// # time stamp


				// float l = Float.parseFloat(jsonMap.get("l"));// # close price
																// (previous
																// trading day)
				// Close Price / Pre-Market
				// float p = Float.parseFloat(jsonMap.get("el"));// # stock
				// pricein
																// pre-market
																// (after-hours)
				// System.out.printf("Date: %s, Close Price: %.6f, Pre
				// Market:%.6f", t, l, 0.0);
				final List<Stock> jsonMap = mapper.readValue(jsonResponse, new TypeReference<List<Stock>>() {
				});
				for (Stock stock : jsonMap) {
					// Put every entry to a topic
					System.out.println(stock);
				}

				response.close();
				response = null;
				Thread.sleep(18000);
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
