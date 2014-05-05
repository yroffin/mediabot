package org.mediabot.business.storage.impl;

import java.io.File;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mediabot.model.storage.INode;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NodeStorageHelperTest {

	@Mock
	FileSystem fileSystem;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindNodes() {
		File[] roots = new File[2];
		roots[0] = new File("X:/");
		roots[1] = new File("Y:/");
		
		Mockito.when(fileSystem.listRoots()).thenReturn(roots);

		Map<String, INode> nodes = (new NodeStorageHelper(fileSystem)).findNodes();
		Assert.assertEquals("", nodes.get("X:\\").getFile().getName());
		Assert.assertEquals("", nodes.get("Y:\\").getFile().getName());
	}

	@Test
	public void testFindAllNodes() {
		File[] roots = new File[2];
		roots[0] = new File("src/main/java");
		roots[1] = new File("src/test/java");
		
		Mockito.when(fileSystem.listRoots()).thenReturn(roots);

		Map<String, INode> nodes = (new NodeStorageHelper(fileSystem)).findNodes();
		Assert.assertEquals("org", ((INode) nodes.values().toArray()[0]).getChildrens().get(0).getFile().getName());
		Assert.assertEquals("mediabot", ((INode) nodes.values().toArray()[0]).getChildrens().get(0).getChildrens().get(0).getFile().getName());
	}
}
