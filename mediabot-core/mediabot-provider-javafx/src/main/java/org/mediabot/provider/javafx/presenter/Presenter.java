package org.mediabot.provider.javafx.presenter;

import org.mediabot.model.storage.INode;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

public interface Presenter {
	/**
	 * get view
	 * @return
	 */
	public abstract Node getView();

	/**
	 * fire event when adding a new node
	 * @param target
	 * @param node
	 */
	public abstract void fireAddNodeItem(TreeItem<String> target, INode node);

	/**
	 * fire event when select one node
	 * @param target
	 * @param node
	 */
	public abstract void fireSelectNodeItem(TreeItem<String> target, INode node);

}