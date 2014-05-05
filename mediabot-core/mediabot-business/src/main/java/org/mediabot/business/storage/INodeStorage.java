package org.mediabot.business.storage;

import java.util.Map;

import org.mediabot.model.storage.INode;

public interface INodeStorage {
	public Map<String, INode> findNodes();
}
