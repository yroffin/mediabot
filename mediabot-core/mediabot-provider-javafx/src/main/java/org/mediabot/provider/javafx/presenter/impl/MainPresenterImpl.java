package org.mediabot.provider.javafx.presenter.impl;

import org.mediabot.provider.javafx.presenter.Presenter;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainPresenterImpl extends AbstractPresenterImpl implements
		Presenter {
	@FXML
	private BorderPane contentArea;

	public void show(Presenter presenter) {
		contentArea.setCenter(presenter.getView());
	}
}
