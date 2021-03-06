package de.goldmann.comercio.server.ui;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Broadcaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static ExecutorService executorService = Executors.newSingleThreadExecutor();

	// Java 8
	private static LinkedList<Consumer<String>> listeners = new LinkedList<Consumer<String>>();

	public static synchronized void register(Consumer<String> listener) {
		listeners.add(listener);
	}

	public static synchronized void unregister(Consumer<String> listener) {
		listeners.remove(listener);
	}

	public static synchronized void broadcast(final String message) {
		for (final Consumer<String> listener : listeners)
			executorService.execute(() -> listener.accept(message));
	}
}
