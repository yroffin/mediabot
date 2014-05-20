package org.mediabot.application.storage.impl;

import java.util.Map;

import org.mediabot.application.storage.DirectoryService;
import org.mediabot.business.storage.Directory;
import org.mediabot.model.storage.INode;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectoryServiceImpl implements DirectoryService {

	@Autowired private Directory directory;

	@Override
	public Map<String, INode> findNodes() {
		return directory.findNodes();
	}
}
