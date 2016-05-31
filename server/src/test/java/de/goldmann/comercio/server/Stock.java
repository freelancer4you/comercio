package de.goldmann.comercio.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
	String t;
	String l;

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getT() {
		return t;
	}

	public String getL() {
		return l;
	}

	@Override
	public String toString() {
		return "Stock [" + (t != null ? "t=" + t + ", " : "") + (l != null ? "l=" + l : "") + "]";
	}

}
