package org.mediabot.business.analyze.impl;

import java.util.concurrent.BlockingQueue;

public abstract class Producer<T> implements Runnable {

	protected final BlockingQueue<T> sharedQueue;

	protected T start;

	public Producer(BlockingQueue<T> sharedQueue, T start) {
		this.sharedQueue = sharedQueue;
		this.start = start;
	}

}
