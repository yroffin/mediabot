package org.mediabot.business.analyze.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.mediabot.model.storage.INode;

public class ProducerConsumerPattern {
	public void analyze(INode start) throws InterruptedException {
		// Creating shared object
		BlockingQueue<INode> sharedQueue = new LinkedBlockingQueue<INode>();

		// Creating Producer and Consumer Thread
		Thread prodThread = new Thread(new ProducerNode(sharedQueue, start));
		
		Thread consThread[] = new Thread[10];
		for(int i = 0;i<consThread.length;i++) {
			consThread[i] = new Thread(new ConsumerNode(sharedQueue));
		}

		// Starting producer and Consumer thread
		prodThread.start();
		for(int i = 0;i<consThread.length;i++) {
			consThread[i].start();
		}
		prodThread.join();
		for(int i = 0;i<consThread.length;i++) {
			consThread[i].join();
		}
	}
}
