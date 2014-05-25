package org.mediabot.business.analyze.impl;

import java.util.concurrent.BlockingQueue;

import org.mediabot.model.storage.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerNode extends Producer<INode> {
	protected static final Logger log = LoggerFactory.getLogger(ProducerNode.class);

	public ProducerNode(BlockingQueue<INode> sharedQueue, INode start) {
		super(sharedQueue, start);
	}

	private void post(INode start) throws InterruptedException {
		if (start.hasChildren()) {
			for (INode item : start.getChildrens()) {
				post(item);
			}
		} else {
			log.info(start.toString());
			sharedQueue.put(start);
		}
	}

	@Override
	public void doRun() {
		try {
			post(start);
		} catch (InterruptedException ex) {
			log.error(null, ex);
		}
	}
}
