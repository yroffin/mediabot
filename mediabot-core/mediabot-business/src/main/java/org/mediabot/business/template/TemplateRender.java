package org.mediabot.business.template;

import java.util.Map;

public interface TemplateRender {

	/**
	 * render this template
	 * @param data
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	String render(String data, Map<String, Object> ctx) throws Exception;

}
