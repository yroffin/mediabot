/**
 * 
 */
package org.mediabot.business.analyze.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mediabot.business.template.TemplateRender;
import org.mediabot.model.storage.INode;
import org.mediabot.model.storage.impl.NodeStorage;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yannick
 *
 */
public class ProducerConsumerPatternTest {
	protected static final Logger log = LoggerFactory.getLogger(FileSummerImpl.class);

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
	NodeStorage child2;
	@Mock
	NodeStorage child3;
	@Mock
	TemplateRender template;

	File child1File = new File("src/test/resources/child1.png");
	File child2File = new File("src/test/resources/child2.png");
	File child3File = new File("src/test/resources/child3.png");

	@SuppressWarnings("unchecked")
	@Test
	public void testProducerConsumerPattern() throws Exception {
		
		List<INode> childs = new ArrayList<INode>();
		childs.add(child1);
		childs.add(child2);
		childs.add(child3);
		
		Mockito.when(root.hasChildren()).thenReturn(true);
		Mockito.when(root.getChildrens()).thenReturn(childs);
		Mockito.when(child1.hasChildren()).thenReturn(false);
		Mockito.when(child1.getFile()).thenReturn(child1File);
		Mockito.when(child1.getMd5()).thenReturn("A");
		Mockito.when(child2.hasChildren()).thenReturn(false);
		Mockito.when(child2.getFile()).thenReturn(child2File);
		Mockito.when(child2.getMd5()).thenReturn("B");
		Mockito.when(child3.hasChildren()).thenReturn(false);
		Mockito.when(child3.getFile()).thenReturn(child3File);
		Mockito.when(child3.getMd5()).thenReturn("A");
		
		/**
		 * template
		 */
		Mockito.when(template.render(Mockito.anyString(), Mockito.anyMap())).thenReturn("2013/10/file.png");
		
		Map<String, List<INode>> index = (new ProducerConsumerPattern()).analyze(10, root);
		for(Entry<String, List<INode>> entry : index.entrySet()) {
			log.info("Dump {}", entry);
		}
	}

}
