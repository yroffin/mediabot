package org.mediabot.application.summer;

import org.mediabot.model.storage.INode;

public interface FileSummerService {

	void analyze(INode root) throws InterruptedException;

}
