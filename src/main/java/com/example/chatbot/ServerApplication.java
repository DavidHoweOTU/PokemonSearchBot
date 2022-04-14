package com.example.chatbot;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServerApplication extends Application {
    private ServerHandler sh = null;

    private Scene loadScene() {
        Button button = new Button("End Communications");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sh.endCommunications();
            }
        });

        return new Scene(button);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Start server
        sh = new ServerHandler();
        new Thread(sh).start();

        // Load scene
        stage.setTitle("Pokémon Search Bot (Server)");
        stage.setScene(loadScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
