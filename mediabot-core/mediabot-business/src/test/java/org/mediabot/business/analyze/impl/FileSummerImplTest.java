package org.mediabot.business.analyze.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mediabot.business.template.TemplateRender;
import org.mediabot.business.template.impl.VelocityTemplateRenderImpl;
import org.mediabot.model.storage.INode;
import org.mediabot.model.storage.impl.NodeStorage;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@SuppressWarnings("unused")
public class FileSummerImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Mock
	NodeStorage child1;
	@Mock
	NodeStorage child2;
	@Mock
	NodeStorage child3;

	@Test
	public void testRename() throws Exception {
		FileSummerImpl fileSummer = new FileSummerImpl();
		Map<String, List<INode>> index = new HashMap<String, List<INode>>();
		index.put("x", new ArrayList<INode>());
		index.put("y", new ArrayList<INode>());
		index.get("x").add(child1);
		index.get("y").add(child2);
		index.get("y").add(child3);
		String data = "$node.getYear()/$node.getWeek()/$node.getName()";
		Mockito.when(child1.getYear()).thenReturn("1");
		Mockito.when(child2.getYear()).thenReturn("2");
		Mockito.when(child3.getYear()).thenReturn("3");
		Mockito.when(child1.getWeek()).thenReturn("11");
		Mockito.when(child2.getWeek()).thenReturn("12");
		Mockito.when(child3.getWeek()).thenReturn("13");
		Mockito.when(child1.getName()).thenReturn("1.jpg");
		Mockito.when(child2.getName()).thenReturn("2.jpg");
		Mockito.when(child3.getName()).thenReturn("3.gif");
		TemplateRender template = new VelocityTemplateRenderImpl();
		fileSummer.rename(index, data, template);
	}

}
