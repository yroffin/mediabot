package org.mediabot.business.analyze.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mediabot.business.analyze.FileSummer;
import org.mediabot.business.template.TemplateRender;
import org.mediabot.model.storage.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSummerImpl implements FileSummer {
	protected static final Logger log = LoggerFactory.getLogger(FileSummerImpl.class);
	
	@Override
	public Map<String, List<INode>> analyze(INode root) throws InterruptedException {
		log.info("Analyze {} with {} thread(s)", root.getName(), 100);
		ProducerConsumerPattern engine = new ProducerConsumerPattern();
		/**
		 * first analyze, then rename
		 */
		return engine.analyze(100, root);
	}

	@Override
	public void rename(Map<String, List<INode>> index, String data, TemplateRender template) throws Exception {
		log.info("Rename {} index(es)", index.size());
		for(Entry<String, List<INode>> entry : index.entrySet()) {
			int i = 0;
			for(INode node : entry.getValue()) {
				Map<String, Object> ctx = new HashMap<String,Object>();
				ctx.put("node", node);
				if(i == 0) {
					if(log.isInfoEnabled()) {
						log.info("Rename {} to {}", node, template.render(data, ctx));
					}
					node.renameTo(template.render(data, ctx ));
				} else {
					log.info("Reject {}", node);
				}
				i++;
			}
		}		
	}

}
