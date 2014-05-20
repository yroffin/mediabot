package org.mediabot.provider.javafx.node;

import org.mediabot.model.storage.INode;
import org.mediabot.provider.javafx.presenter.Presenter;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;

/**
 * LazyNodeItem mapper, only load child when needed
 */
public class LazyTreeItemView extends TreeItem<String> {
	private String name;
	private INode node;
	private TreeItem<String> view = this;
	private Presenter presenter;

	/**
	 * constructor
	 * 
	 * @param name
	 * @param node
	 */
	public LazyTreeItemView(Presenter presenter, String name, INode node) {
		super(name);
		this.presenter = presenter;
		this.node = node;
		this.name = name;

		/**
		 * add handler
		 */
		addEventHandler(TreeItem.<Object> expandedItemCountChangeEvent(),
				itemListener);
		addEventHandler(TreeItem.<Object> treeNotificationEvent(),
				treeNotificationHandler);
	}

	@Override
	public boolean isLeaf() {
		return node != null && node.hasChildren() == false;
	}

	@Override
	public ObservableList<TreeItem<String>> getChildren() {
		return super.getChildren();
	}

	private final EventHandler<TreeModificationEvent<Object>> treeNotificationHandler = new EventHandler<TreeModificationEvent<Object>>() {
		@Override
		public void handle(TreeModificationEvent<Object> event) {
			
		}
	};

	private final EventHandler<TreeModificationEvent<Object>> itemListener = new EventHandler<TreeModificationEvent<Object>>() {
		@Override
		public void handle(TreeModificationEvent<Object> event) {
			if (event.wasExpanded() && getChildren().size() == 0) {
				presenter.fireAddNodeItem(view, node);
			}
		}
	};

	public INode getNode() {
		return node;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "LazyNodeItem [name=" + name + ", node=" + node + "]";
	}
}
