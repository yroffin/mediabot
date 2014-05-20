package org.mediabot.provider.javafx.node;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

import org.mediabot.model.storage.INode;

public class TableNodeView {

	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty type = new SimpleStringProperty();
	private final SimpleStringProperty lastModified = new SimpleStringProperty();
	private final SimpleStringProperty length = new SimpleStringProperty();

	// Create an instance of SimpleDateFormat used for formatting 
	// the string representation of date (month/day/year)
	private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public TableNodeView(INode node) {
		name.set(node.getName());
		int lastIndexOfPoint = node.getName().lastIndexOf('.');
		if(lastIndexOfPoint > 0) {
			type.set(node.getName().substring(lastIndexOfPoint));
		} else {
			type.set("");
		}
		lastModified.set(df.format(new Date(node.getFile().lastModified())));
		length.set(node.getFile().length()+"");
	}

	public String getName() {
		return name.get();
	}

	public String getType() {
		return type.get();
	}

	public String getLastModified() {
		return lastModified.get();
	}

	public String getLength() {
		return length.get();
	}
}
