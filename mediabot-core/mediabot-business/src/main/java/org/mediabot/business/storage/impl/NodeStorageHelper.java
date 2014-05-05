package org.mediabot.business.storage.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.mediabot.business.storage.IFileSystem;
import org.mediabot.business.storage.INodeStorage;
import org.mediabot.model.storage.INode;
import org.mediabot.model.storage.impl.NodeStorage;

public class NodeStorageHelper implements INodeStorage {

	private static NodeStorageHelper nodeStorage = new NodeStorageHelper(new FileSystem());
	private Map<String,INode> nodes = new HashMap<String,INode>();
	private IFileSystem fileSystem;
    
	public static INodeStorage instance() {
		return nodeStorage;
	}

    public NodeStorageHelper(FileSystem fileSystem) {
    	this.fileSystem = fileSystem;
    }

    public Map<String, INode> findNodes() {
        File[] roots = fileSystem.listRoots();
        for (int i = 0; i < roots.length; i++) {
        	nodes.put(roots[i].getAbsolutePath(), new NodeStorage(roots[i]));
        }
        return nodes;
    }

}
