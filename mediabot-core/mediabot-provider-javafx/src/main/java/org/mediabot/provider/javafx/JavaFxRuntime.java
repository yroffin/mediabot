package org.mediabot.provider.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxRuntime extends Application implements Initializable {
	
	/**
	 * bootstrap
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		URL url = getClass().getResource(getClass().getSimpleName() + ".fxml");
		Parent root = FXMLLoader.load(url);

		Scene scene = new Scene(root, root.getLayoutX(), root.getLayoutY());

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
	}
}