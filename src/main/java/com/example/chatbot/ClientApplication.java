package com.example.chatbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {

    public Scene generateSearchScene() {
        HBox root = new HBox();
        root.getChildren().add(new ListView<String>());

        return new Scene(new HBox());
    }

    public Scene generateDataScene() {

        return new Scene(new VBox(), 600, 600);
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = generateSearchScene();
        stage.setTitle("Pokémon Search Bot");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}