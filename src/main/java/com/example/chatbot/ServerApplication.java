package com.example.chatbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ServerApplication extends Application {
    private ServerHandler sh = null;
    public static PokemonHandler ph = null;
    public static boolean started = false;

    private Scene loadScene() {
        Button button = new Button("End Communications");
        button.setOnMouseClicked(event -> sh.endCommunications());
        return new Scene(button, 400, 400);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Start server
        sh = new ServerHandler();
        new Thread(sh).start();

        ph = new PokemonHandler();

        // Load scene
        stage.setTitle("Pokémon Search Bot (Server)");
        stage.setScene(loadScene());
        stage.show();

        // NOTE: The "End Communications" button can likely be
        // replaced with a client task in the future.
    }

    public static void main(String[] args) {
        launch();
    }
}
