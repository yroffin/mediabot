package org.mediabot.business.analyze.impl;

import java.util.concurrent.BlockingQueue;

public abstract class Consumer<T> implements Runnable {
	protected final BlockingQueue<T> sharedQueue;

    public Consumer (BlockingQueue<T> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
  
}
