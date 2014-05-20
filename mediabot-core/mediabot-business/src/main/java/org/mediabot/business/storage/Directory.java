package org.mediabot.business.storage;

import java.util.Map;

import org.mediabot.model.storage.INode;

public interface Directory {
	public Map<String, INode> findNodes();
}
