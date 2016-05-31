package de.goldmann.comercio.server.stock.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockInfoGoogle implements StockInfo {

	private String name;
	private double price;
	private long indexId;
	private String t;
	private String e;
	private boolean index;

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
		StockInfoGoogle other = (StockInfoGoogle) obj;
		if (indexId != other.indexId)
			return false;
		if (getKey() == null) {
			if (other.getKey() != null)
				return false;
		} else if (!getKey().equals(other.getKey()))
			return false;
		return true;
	}

	public long getIndexId() {
		return indexId;
	}

	public boolean isIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public void setIndexId(long indexId) {
		this.indexId = indexId;
	}

	public StockInfoGoogle() {
	}

	public String getKey() {
		return e + ":" + t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("t")
	public void setT(String t) {
		this.t = t;
	}

	@JsonProperty("e")
	public void setE(String e) {
		this.e = e;
	}

	public double getPrice() {
		return price;
	}

	@JsonProperty("l")
	public void setPrice(String priceStr) {

		if (priceStr == null || priceStr.length() == 0) {
			this.price = 0.0;
			return;
		}

		if (priceStr.contains(",")) {
			this.price = Double.parseDouble(priceStr.replace(",", ""));// origin
																	// price=10,188.91
		} else {
			this.price = Double.parseDouble(priceStr);
		}
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
