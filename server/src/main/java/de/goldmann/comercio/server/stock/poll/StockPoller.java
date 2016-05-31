package de.goldmann.comercio.server.stock.poll;

public interface StockPoller {
	public static final int POLL_INTERVALL = 140000;
	public static final int CONNECT_TIMEOUT = 30000;
	public static final int SOCKET_TIMEOUT = 30000;
	public static final int MAX_OBJECT_SIZE = 8192;
	public static final int MAX_CACHE_ENTRIES = 1000;

	void poll() throws Exception;
}
