package org.mediabot.provider.javafx.node;

import javafx.beans.property.SimpleStringProperty;

import org.mediabot.model.storage.INode;

public class TableNodeView {
	
	 private final SimpleStringProperty name = new SimpleStringProperty();
	 
	public TableNodeView(INode node) {
		name.set(node.getName());
	}

	public String getName() {
		return name.get();
	}
}
