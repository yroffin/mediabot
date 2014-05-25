package org.mediabot.model.storage.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mediabot.model.storage.INode;

/**
 * node implementation
 * @author yannick
 *
 */
public class NodeStorage implements INode {

	private List<INode> childrens = new ArrayList<INode>();
	private File file = null;
	private boolean loaded = false;
	private String md5;
	private Calendar lastModified = null;

	/**
	 * constructor
	 * @param file
	 */
	public NodeStorage(File file) {
		this.file = file;
	}

	public Calendar getLastModified() {
		if(lastModified == null) {
			lastModified = Calendar.getInstance();
			lastModified.setTime(new Date(getFile().lastModified()));
		}
		return lastModified;
	}

	@Override
	public List<INode> getChildrens() {
		if (!loaded) {
			childrens = load();
		}
		loaded = true;
		return childrens;
	}

	/**
	 * refresh children
	 * 
	 * @return
	 */
	private List<INode> load() {
		childrens.clear();
		for (File child : file.listFiles()) {
			childrens.add(new NodeStorage(child));
		}
		return childrens;
	}

	@Override
	public boolean hasChildren() {
		if(file == null) return false;
		File[] files = file.listFiles();
		if(files == null) return false;
		return file.listFiles().length > 0;
	}

	@Override
	public boolean isLoaded() {
		return loaded;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public String getName() {
		if(file != null) return file.getName();
		return "N/A";
	}

	@Override
	public boolean isDirectory() {
		return file.isDirectory();
	}

	@Override
	public boolean isFile() {
		return file.isFile();
	}

	@Override
	synchronized public String getMd5() {
		return md5;
	}

	@Override
	synchronized public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public String getWeek() {
		return getLastModified().get(Calendar.WEEK_OF_YEAR)+"";
	}

	@Override
	public String getYear() {
		return getLastModified().get(Calendar.YEAR)+"";
	}

	@Override
	public void renameTo(String dest) {
		File destFile = new File(dest);
		destFile.getParentFile().mkdirs();
		file.renameTo(destFile);
	}

	@Override
	public String toString() {
		return "NodeStorage [file=" + file + ", loaded=" + loaded + "]";
	}
}
