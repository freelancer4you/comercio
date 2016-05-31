package de.goldmann.comercio.server.stock.poll;

import java.math.BigDecimal;

public class StockInfoYahoo implements StockInfo {

	private String name;
	private double price;
	private long indexId;
	private boolean index;
	private String symbol;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (indexId ^ (indexId >>> 32));
		result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
		return result;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockInfoYahoo other = (StockInfoYahoo) obj;
		if (indexId != other.indexId)
			return false;
		if (getKey() == null) {
			if (other.getKey() != null)
				return false;
		} else if (!getKey().equals(other.getKey()))
			return false;
		return true;
	}

	@Override
	public long getIndexId() {
		return indexId;
	}

	public boolean isIndex() {
		return index;
	}

	@Override
	public void setIndex(boolean index) {
		this.index = index;
	}

	@Override
	public void setIndexId(long indexId) {
		this.indexId = indexId;
	}

	public StockInfoYahoo(String name, String symbol, BigDecimal priceObj) {
		this.name = name;
		this.symbol = symbol;
		this.price = priceObj != null ? priceObj.doubleValue() : 0.0;
	}

	@Override
	public String getKey() {
		return symbol;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getPrice() {
		return price;
	}


	@Override
	public String toString() {
		return "StockInfo [" + (name != null ? "name=" + name + ", " : "")
				+ (getKey() != null ? "key=" + getKey() + ", " : "")
				+ "price=" + price + ", indexId=" + indexId + "]";
	}

	public void setPriceDoubleValue(double price) {
		this.price = price;
	}

}
