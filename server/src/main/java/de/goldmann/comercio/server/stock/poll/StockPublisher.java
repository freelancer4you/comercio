package de.goldmann.comercio.server.stock.poll;

import java.util.concurrent.BlockingQueue;


public class StockPublisher {

	private BlockingQueue<StockInfo> queue;

	public StockPublisher(BlockingQueue<StockInfo> queue) {
		this.queue = queue;
	}

	public void publish(StockInfo info) {
		try {
			this.queue.put(info);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public StockInfo take() {
		// TODO Auto-generated method stub
		try {
			return this.queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
