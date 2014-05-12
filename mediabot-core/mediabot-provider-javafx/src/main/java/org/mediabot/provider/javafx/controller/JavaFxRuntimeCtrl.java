package org.mediabot.provider.javafx.controller;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.mediabot.business.storage.impl.NodeStorageHelper;
import org.mediabot.model.storage.INode;
import org.mediabot.provider.javafx.node.LazyNodeItem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;

public class JavaFxRuntimeCtrl<DynamicTreeNodeModel> implements Initializable {
	@FXML TreeView<String> treeFilesystems;

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		Map<String, INode> nodes = NodeStorageHelper.instance().findNodes();

		LazyNodeItem root = new LazyNodeItem("root",null);
		root.setExpanded(true);
		for(Entry<String, INode> item : nodes.entrySet()) {
			LazyNodeItem element = new LazyNodeItem(item.getKey(), item.getValue());
			root.getChildren().add(element);
		}
		treeFilesystems.setRoot(root);
	}
	
}
