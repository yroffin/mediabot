/**
 * 
 */
package org.mediabot.business.analyze.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mediabot.model.storage.INode;
import org.mediabot.model.storage.impl.NodeStorage;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author yannick
 *
 */
public class ProducerConsumerPatternTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Mock
	NodeStorage root;
	@Mock
	NodeStorage child1;
	@Mock
	File child1File;
	@Mock
	NodeStorage child2;
	@Mock
	File child2File;

	@Test
	public void testProducerConsumerPattern() throws InterruptedException {
		
		List<INode> childs = new ArrayList<INode>();
		childs.add(child1);
		childs.add(child2);
		
		Mockito.when(root.hasChildren()).thenReturn(true);
		Mockito.when(root.getChildrens()).thenReturn(childs);
		Mockito.when(child1.hasChildren()).thenReturn(false);
		Mockito.when(child1.getFile()).thenReturn(child1File);
		Mockito.when(child2.hasChildren()).thenReturn(false);
		Mockito.when(child2.getFile()).thenReturn(child2File);
		
		new ProducerConsumerPattern().analyze(root);
	}

}
