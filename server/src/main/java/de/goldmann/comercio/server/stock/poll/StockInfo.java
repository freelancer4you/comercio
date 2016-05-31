package de.goldmann.comercio.server.stock.poll;

public interface StockInfo {

	void setIndexId(long id);

	void setIndex(boolean b);

	long getIndexId();

	double getPrice();

	String getKey();

	String getName();

	boolean isIndex();

}
