package org.mediabot.provider.javafx.presenter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.mediabot.application.storage.DirectoryService;
import org.mediabot.provider.javafx.presenter.impl.DefaultPresenterImpl;
import org.mediabot.provider.javafx.presenter.impl.MainPresenterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class PresenterFactory {
	
	@Autowired
	ApplicationContext context;

	@Autowired
	DirectoryService directoryService;

	public Scene start() {
		MainPresenterImpl mainPresenter = loadPresenter("/fxml/Main.fxml");
		DefaultPresenterImpl defaultPresenter = loadPresenter("/fxml/Default.fxml");
		
		mainPresenter.setApplicationContext(context);
		defaultPresenter.setApplicationContext(context);

		mainPresenter.show(defaultPresenter);

        Scene scene = new Scene((Parent) mainPresenter.getView(), 800, 600);
        scene.getStylesheets().add("styles.css");
        return scene;

	}

	@SuppressWarnings("unchecked")
	private <T> T loadPresenter(String fxmlFile) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.load(getClass().getResourceAsStream(fxmlFile));
			return (T) loader.getController();
		} catch (IOException e) {
			throw new RuntimeException(String.format(
					"Unable to load FXML file '%s'", fxmlFile), e);
		} catch (NullPointerException e) {
			throw new RuntimeException(String.format(
					"Unable to load FXML file '%s'", fxmlFile), e);
		}
	}
}
