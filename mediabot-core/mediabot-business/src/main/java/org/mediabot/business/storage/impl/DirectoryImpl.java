package org.mediabot.business.storage.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.mediabot.business.storage.Directory;
import org.mediabot.business.storage.FileSystem;
import org.mediabot.model.storage.INode;
import org.mediabot.model.storage.impl.NodeStorage;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectoryImpl implements Directory {

	@Autowired FileSystem fileSystem;

	private Map<String,INode> nodes = new HashMap<String,INode>();
    
    public Map<String, INode> findNodes() {
        File[] roots = fileSystem.listRoots();
        for (int i = 0; i < roots.length; i++) {
        	nodes.put(roots[i].getAbsolutePath(), new NodeStorage(roots[i]));
        }
        return nodes;
    }

}
