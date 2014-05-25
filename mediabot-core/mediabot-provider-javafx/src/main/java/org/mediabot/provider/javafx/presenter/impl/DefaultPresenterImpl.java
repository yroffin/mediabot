package org.mediabot.provider.javafx.presenter.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.mediabot.application.storage.DirectoryService;
import org.mediabot.application.summer.FileSummerService;
import org.mediabot.model.storage.INode;
import org.mediabot.provider.javafx.node.LazyTreeItemView;
import org.mediabot.provider.javafx.node.TableNodeView;
import org.mediabot.provider.javafx.presenter.Presenter;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPresenterImpl extends AbstractPresenterImpl implements
		Presenter {

	private static final Logger log = LoggerFactory.getLogger(DefaultPresenterImpl.class);

	@FXML
	TreeView<String> treeView;
	@FXML
	TableView<TableNodeView> tableView;
	@FXML
	TableColumn<TableNodeView, String> nameTableView;
	@FXML
	Label labelSelection;
	@FXML
	TextField fileRenamer;

	@Autowired
	private DirectoryService directoryService;
	@Autowired
	private FileSummerService fileSummerService;

	/**
	 * internal fields
	 */
	private INode nodeSelection;

	@SuppressWarnings("unchecked")
	@Override
	public void postConstruct() {
		/**
		 * nodes
		 */
		Map<String, INode> nodes = directoryService.findNodes();

		TreeItem<String> root = new LazyTreeItemView(this, "root", null);
		root.setExpanded(true);
		for (Entry<String, INode> item : nodes.entrySet()) {
			LazyTreeItemView element = new LazyTreeItemView(this, item.getKey(),
					item.getValue());
			root.getChildren().add(element);
		}
		treeView.setRoot(root);

		/**
		 * select handler
		 */
		treeView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<TreeItem<String>>() {

					@Override
					public void changed(
							ObservableValue<? extends TreeItem<String>> observable,
							TreeItem<String> oldValue, TreeItem<String> newValue) {
						if (newValue != null) {
							fireSelectNodeItem(newValue,
									((LazyTreeItemView) newValue).getNode());
						} else {
							fireSelectNodeItem(newValue, null);
						}
					}

				});

		/**
		 * table view
		 */
		tableView.getColumns().addAll(
				buildColumn("Nom","name"),
				buildColumn("Modifié le","lastModified"),
				buildColumn("Type","type"),
				buildColumn("Taille","length"));
		/**
		 * Textfield
		 */
		fileRenamer.setText("$node.getYear()/$node.getWeek()/$node.getName()");
	}

	private TableColumn<TableNodeView, String> buildColumn(String libelle, String field) {
		TableColumn<TableNodeView, String> columnName = new TableColumn<TableNodeView, String>(libelle);
		columnName.setCellValueFactory(new PropertyValueFactory<TableNodeView, String>(field));
		return columnName;
	}

	@Override
	public void fireSelectNodeItem(TreeItem<String> target, INode node) {
		ObservableList<TableNodeView> data = FXCollections.observableArrayList();
		if (target == null) {
			log.info("Target: - no select node item");
			tableView.setItems(data);
		} else {
			if (node.isDirectory()) {
				log.info("Target: {} - select node item", target);
				for (INode item : node.getChildrens()) {
					if(item.isFile()) {
						data.add(new TableNodeView(item));
					}
				}
				tableView.setItems(data);
				/**
				 * set current selection
				 */
				labelSelection.setText(node.getFile().getAbsolutePath());
				nodeSelection = node;
			}
		}
	}

	@Override
	public void fireAddNodeItem(TreeItem<String> target, INode node) {
		/**
		 * add children to this node
		 */
		if (node != null && node.hasChildren()) {
			log.info("Target: {} - add node item", target);
			for (INode child : node.getChildrens()) {
				if (child.isDirectory()) {
					target.getChildren().add(
							new LazyTreeItemView(this, child.getFile().getName(),
									child));
				} else {
					log.info("Child ignored: {} - add node item", child);
				}
			}
		}
	}
	
	@FXML
	public void onFindDuplicate() throws Exception {
		String output = nodeSelection.getFile().getAbsolutePath() + ".sort";
		fileSummerService.analyze(nodeSelection, output, fileRenamer.getText());
	}
}
