package org.mediabot.business.template.impl;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.mediabot.business.template.TemplateRender;

public class VelocityTemplateRenderImpl implements TemplateRender {
	
	VelocityEngine ve;

	public VelocityTemplateRenderImpl() {
		/**
		 * first, get and initialize an engine
		 **/
		ve = new VelocityEngine();
		ve.init();
	}

	@Override
	public String render(String instring, Map <String,Object> ctx) throws Exception {
		/**
		 * create a context and add data 
		 **/
		VelocityContext context = new VelocityContext();
		for(Entry<String, Object> entry : ctx.entrySet()) {
			context.put(entry.getKey(),entry.getValue());
		}
		/**
		 * now render the template into a StringWriter
		 **/
		StringWriter writer = new StringWriter();
		ve.evaluate(context, writer, "internal render", instring);
		/**
		 * show the World 
		 **/
		return writer.toString().toLowerCase();
	}
}
