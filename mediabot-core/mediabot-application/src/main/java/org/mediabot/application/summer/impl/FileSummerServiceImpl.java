package org.mediabot.application.summer.impl;

import org.mediabot.application.summer.FileSummerService;
import org.mediabot.business.analyze.FileSummer;
import org.mediabot.model.storage.INode;
import org.springframework.beans.factory.annotation.Autowired;

public class FileSummerServiceImpl implements FileSummerService {

	@Autowired private FileSummer fileSummer;

	@Override
	public void analyze(INode root) throws InterruptedException {
		fileSummer.analyze(root);
	}
}
