package org.mediabot.business.analyze.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import org.mediabot.model.storage.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class ConsumerNode extends Consumer<INode> {
	protected static final Logger log = LoggerFactory
			.getLogger(ConsumerNode.class);

	public ConsumerNode(BlockingQueue<INode> sharedQueue) {
		super(sharedQueue);
	}

	/**
	 * load file
	 * 
	 * @param fin
	 * @return
	 */
	private byte[] load(FileInputStream fin) {
		byte readBuf[] = new byte[512 * 1024];
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			int readCnt = fin.read(readBuf);
			while (0 < readCnt) {
				bout.write(readBuf, 0, readCnt);
				readCnt = fin.read(readBuf);
			}
			fin.close();
			return bout.toByteArray();
		} catch (Exception e) {
			return new byte[0];
		}
	}

	/**
	 * Spring framework also provides overloaded md5 methods. You can pass input
	 * as String or byte array and Spring can return hash or digest either as
	 * byte array or Hex String. Here we are passing String as input and getting
	 * MD5 hash as hex String.
	 * 
	 * @throws FileNotFoundException
	 */
	private String md5Spring(File filename) throws FileNotFoundException {
		return DigestUtils.md5DigestAsHex(load(new FileInputStream(filename)));
	}

	/**
	 * analyze file
	 * 
	 * @param node
	 */
	private void analyze(INode node) {
		String md5 = null;
		try {
			md5 = md5Spring(node.getFile());
			node.setMd5(md5);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.info("Analyze {} - {}", node, md5);
	}

	@Override
	public void doRun() {
		while (true) {
			try {
				INode node = sharedQueue.take();
				if (node == null)
					return;
				if (node.getFile() == null)
					return;
				analyze(node);
			} catch (InterruptedException ex) {
				log.error(null, ex);
			}
		}
	}
}
