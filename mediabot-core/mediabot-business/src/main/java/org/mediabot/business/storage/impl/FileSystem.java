package org.mediabot.business.storage.impl;

import java.io.File;

import org.mediabot.business.storage.IFileSystem;

public class FileSystem implements IFileSystem {

	@Override
	public File[] listRoots() {
		return File.listRoots();
	}

}
