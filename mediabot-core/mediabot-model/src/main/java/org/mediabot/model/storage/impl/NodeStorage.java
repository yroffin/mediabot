package org.mediabot.model.storage.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mediabot.model.storage.INode;

public class NodeStorage implements INode {

	private List<INode> childrens = new ArrayList<INode>();
	private File file = null;
	private boolean loaded = false;

	public NodeStorage(File file) {
		this.file  = file;
	}

	@Override
	public List<INode> getChildrens() {
		if(!loaded) {
			childrens = load();
		}
		loaded = true;
		return childrens;
	}

	/**
	 * refresh children
	 * @return
	 */
	private List<INode> load() {
		childrens.clear();
		for(File child : file.listFiles()) {
			childrens.add(new NodeStorage(child));
		}
		return childrens;
	}

	@Override
	public boolean isLoaded() {
		return loaded;
	}

	@Override
	public File getFile() {
		return file;
	}

}
