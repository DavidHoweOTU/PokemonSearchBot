package com.example.chatbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatbotClientApplication extends Application {

    public Scene generateScene() {
        return new Scene(new VBox());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = generateScene();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}