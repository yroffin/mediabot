package org.mediabot.application.summer.impl;

import java.util.List;
import java.util.Map;

import org.mediabot.application.summer.FileSummerService;
import org.mediabot.business.analyze.FileSummer;
import org.mediabot.business.template.TemplateRender;
import org.mediabot.model.storage.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FileSummerServiceImpl implements FileSummerService {
	protected static final Logger log = LoggerFactory.getLogger(FileSummerServiceImpl.class);
	
	@Autowired private FileSummer fileSummer;
	@Autowired private TemplateRender template;

	@Override
	public void analyze(INode root, String workDir, String renamer) throws Exception {
		log.info("Analyze {}", root.getName());
		Map<String, List<INode>> index = fileSummer.analyze(root);
		fileSummer.rename(index, renamer, template);
	}
}
