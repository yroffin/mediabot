package org.mediabot.business.analyze;

import java.util.List;
import java.util.Map;

import org.mediabot.business.template.TemplateRender;
import org.mediabot.model.storage.INode;

public interface FileSummer {

	/**
	 * analyze referential
	 * @param root
	 * @param template
	 * @return
	 * @throws InterruptedException
	 */
	Map<String, List<INode>> analyze(INode root) throws InterruptedException;

	/**
	 * rename it
	 * @param index, index to rename
	 * @param data, the template renamer
	 * @param template, the template builder
	 * @throws Exception 
	 */
	void rename(Map<String, List<INode>> index, String data, TemplateRender template) throws Exception;
}
