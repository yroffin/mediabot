package org.mediabot.business.analyze;

import org.mediabot.model.storage.INode;

public interface FileSummer {

	void analyze(INode root) throws InterruptedException;

}
