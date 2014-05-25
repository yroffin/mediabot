package org.mediabot.business.template.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mediabot.model.storage.impl.NodeStorage;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@SuppressWarnings("unused")
public class VelocityTemplateRenderImplTest {

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
	File file1;

	@Test
	public void testRender() throws Exception {
		VelocityTemplateRenderImpl impl = new VelocityTemplateRenderImpl();
		Map<String, Object> ctx = new HashMap<String,Object>();
		ctx.put("node", child1);
		Mockito.when(child1.getName()).thenReturn("child1.jpeg");
		String data = "$node.getName()";
		String render = impl.render(data , ctx);
		assertEquals("child1.jpeg", render);
	}

	@Test
	public void testRender001() throws Exception {
		VelocityTemplateRenderImpl impl = new VelocityTemplateRenderImpl();
		Map<String, Object> ctx = new HashMap<String,Object>();
		ctx.put("node", new NodeStorage(file1));
		Mockito.when(file1.lastModified()).thenReturn(25255545450L);
		Mockito.when(file1.getName()).thenReturn("test.GIF");
		String data = "$node.getYear()/$node.getWeek()/$node.getName()";
		String render = impl.render(data , ctx);
		assertEquals("1970/43/test.gif", render);
	}
}
