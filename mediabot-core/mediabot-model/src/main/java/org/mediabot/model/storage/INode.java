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
	 * get name
	 * @return
	 */
	public String getName();

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

	/**
	 * has children
	 * @return
	 */
	public boolean hasChildren();

	/**
	 * is node a directory
	 * @return
	 */
	public boolean isDirectory();

	/**
	 * is node a file
	 * @return
	 */
	public boolean isFile();
}
