package org.mediabot.business.analyze.impl;

import java.util.concurrent.BlockingQueue;

import org.mediabot.model.storage.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerNode extends Consumer<INode> {
	protected static final Logger log = LoggerFactory.getLogger(ConsumerNode.class);

	public ConsumerNode(BlockingQueue<INode> sharedQueue) {
		super(sharedQueue);
	}

    @Override
    public void run() {
        while(true){
            try {
                INode node = sharedQueue.take();
                if(node == null) return;
                if(node.getFile() == null) return;
                analyze(node);
            } catch (InterruptedException ex) {
            	log.error(null, ex);
            }
        }
    }
    
    private void analyze(INode node) {
        log.info("Analyze {}", node);
    }
}
