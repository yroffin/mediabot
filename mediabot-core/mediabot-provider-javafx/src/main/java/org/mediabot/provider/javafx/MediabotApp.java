package org.mediabot.provider.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

import org.mediabot.provider.javafx.presenter.PresenterFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MediabotApp extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        @SuppressWarnings("resource")
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("ApplicationContext.xml");
        PresenterFactory presenterFactory = (PresenterFactory) context.getBean("presenterFactory");
        stage.setScene(presenterFactory.start());
        stage.setTitle("Mediabot - JavaFX Media management");
        stage.show();

    }
}
