package org.mediabot.business.analyze.impl;

import org.mediabot.business.analyze.FileSummer;
import org.mediabot.model.storage.INode;

public class FileSummerImpl implements FileSummer {

	@Override
	public void analyze(INode root) throws InterruptedException {
		new ProducerConsumerPattern().analyze(root);
	}

}
