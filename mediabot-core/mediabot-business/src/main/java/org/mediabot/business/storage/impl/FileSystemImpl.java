package org.mediabot.business.storage.impl;

import java.io.File;

import org.mediabot.business.storage.FileSystem;

public class FileSystemImpl implements FileSystem {

	@Override
	public File[] listRoots() {
		return File.listRoots();
	}

}
