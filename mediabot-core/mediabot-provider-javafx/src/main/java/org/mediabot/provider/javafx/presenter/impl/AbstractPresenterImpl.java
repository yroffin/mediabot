package org.mediabot.provider.javafx.presenter.impl;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

import org.mediabot.model.storage.INode;
import org.mediabot.provider.javafx.presenter.Presenter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

public abstract class AbstractPresenterImpl implements Presenter {

	/**
	 * In order to access the fields of your view within your presenter (i.e. to
	 * show data on the screen), you have to expose the fields directly to the
	 * presenter using @FXML annotations. This means your presenter ends up
	 * knowing far too much intimate detail about your view.
	 */
	@FXML
	protected Node root;

	@Autowired
	ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		applicationContext.getAutowireCapableBeanFactory()
				.autowireBeanProperties(this,
						AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		postConstruct();
	}

	public void postConstruct() {
	}

	public Node getView() {
		return root;
	}

	@Override
	public void fireAddNodeItem(TreeItem<String> target, INode node) {
		// implement it		
	}

	@Override
	public void fireSelectNodeItem(TreeItem<String> target, INode node) {
		// implement it		
	}
}
