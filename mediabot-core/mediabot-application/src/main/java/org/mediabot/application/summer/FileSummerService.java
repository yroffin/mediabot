package org.mediabot.application.summer;

import org.mediabot.model.storage.INode;

public interface FileSummerService {

	/**
	 * analyze and rename directory
	 * @param root
	 * @param renamer
	 * @throws InterruptedException
	 * @throws Exception 
	 */
	void analyze(INode root, String workDir, String renamer) throws InterruptedException, Exception;

}
