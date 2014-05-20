package org.mediabot.application.storage;

import java.util.Map;

import org.mediabot.model.storage.INode;

public interface DirectoryService {

	public abstract Map<String, INode> findNodes();

}