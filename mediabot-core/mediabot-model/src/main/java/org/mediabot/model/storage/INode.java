package org.mediabot.model.storage;

import java.io.File;
import java.util.List;

public interface INode {
	/**
	 * get associated file
	 * @return
	 */
	public File getFile();
	
	/**
	 * get children
	 * @return
	 */
	public List<INode> getChildrens();
	
	/**
	 * child loaded ?
	 * @return
	 */
	public boolean isLoaded();
}
