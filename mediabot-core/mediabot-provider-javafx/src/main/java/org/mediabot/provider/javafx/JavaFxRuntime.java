package org.mediabot.provider.javafx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.mediabot.business.storage.impl.NodeStorageHelper;
import org.mediabot.model.storage.INode;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
 
public class JavaFxRuntime extends Application implements Initializable {
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML protected TreeView treeFilesystems;
 
    @Override
    public void start(Stage stage) throws IOException {
    	URL url = getClass().getResource(getClass().getSimpleName()+".fxml");
    	Parent root = FXMLLoader.load(url);
        
        Scene scene = new Scene(root, root.getLayoutX(), root.getLayoutY());
    
       stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
        Map<String, INode> nodes = NodeStorageHelper.instance().findNodes();

        TreeItem<String> root = new TreeItem<String>("Root Node");
        root.setExpanded(true);
        root.getChildren().add(new TreeItem<String>("Root Node"));
        root.getChildren().add(new TreeItem<String>("Root Node"));
        root.getChildren().add(new TreeItem<String>("Root Node"));
		treeFilesystems.setRoot(root);
		
	}
}