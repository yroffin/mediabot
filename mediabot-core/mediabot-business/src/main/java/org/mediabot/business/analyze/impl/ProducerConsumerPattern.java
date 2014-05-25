package org.mediabot.business.analyze.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.mediabot.business.analyze.ThreadCompleteListener;
import org.mediabot.model.storage.INode;
import org.mediabot.model.storage.impl.NodeStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerConsumerPattern implements ThreadCompleteListener {
	protected static final Logger log = LoggerFactory.getLogger(ProducerConsumerPattern.class);

	private BlockingQueue<INode> sharedQueue = null;
	private Thread consThread[] = null;

	/**
	 * launch analyze
	 * @param start
	 * @param template 
	 * @throws InterruptedException
	 */
	public Map<String, List<INode>> analyze(int consumers, INode start) throws InterruptedException {
		// Creating shared object
		sharedQueue = new LinkedBlockingQueue<INode>();
		consThread = new Thread[consumers];

		// Creating Producer and Consumer Thread
		ProducerNode threadProducer = new ProducerNode(sharedQueue, start);
		Thread prodThread = new Thread(threadProducer);
		threadProducer.addListener(this);
		
		for(int i = 0;i<consThread.length;i++) {
			ConsumerNode threadConsumer = new ConsumerNode(sharedQueue);
			consThread[i] = new Thread(threadConsumer);
			threadConsumer.addListener(this);
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
		
		log.info("Analyze terminée");
		
		/**
		 * search for dups
		 */
		Map<String, List<INode>> index = findDups(start, new TreeMap<String,List<INode>>());
		for(Entry<String, List<INode>> entry : index.entrySet()) {
			if(entry.getValue().size()>1) {
				log.warn("Dups for {} {}", entry.getKey(), entry.getValue());
			}
		}
		log.info("{} entrée vérifiées", index.size());
		return index;
	}

	/**
	 * find duplicate
	 * @param start
	 * @param index
	 * @return
	 */
	private Map<String,List<INode>> findDups(INode start, Map<String,List<INode>> index) {
		if (start.hasChildren()) {
			for (INode item : start.getChildrens()) {
				findDups(item, index);
			}
		} else {
			if(log.isDebugEnabled()) {
				log.debug("Find dups for {}", start);
			}
			List<INode> counts = index.get(start.getMd5());
			if(counts == null) {
				counts = new ArrayList<INode>();
				counts.add(start);
				index.put(start.getMd5(), counts);
			} else {
				index.get(start.getMd5()).add(start);
			}
		}
		return index;
	}

	/**
	 * count consumer
	 * @param threads
	 * @return
	 */
	private int countConsumerAlive(Thread[] threads) {
		int i = threads.length - 1;
		int counter = 0;
		for(;i>=0;i--) {
			if(threads[i].isAlive()) counter++;
		}
		return counter;
	}

	@Override
	public void notifyOfThreadComplete(Thread thread) {
		/**
		 * end on producer start the end of our work
		 */
		if(thread instanceof ProducerNode) {
			int counter = 0;
			while((counter = countConsumerAlive(consThread)) > 0) {
				try {
					sharedQueue.put(new NodeStorage(null));
					if(log.isDebugEnabled()) {
						log.debug("Waiting for {} consumer(s) for completion", counter);
					}
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
