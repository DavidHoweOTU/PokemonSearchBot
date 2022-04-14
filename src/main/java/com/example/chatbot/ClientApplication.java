package com.example.chatbot;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {

    private final int windowHeight = 600;
    private final int windowWidth  = 600;

    private final ObservableList<String> types = FXCollections.observableArrayList(
            "Any Type",
            "Normal",
            "Fire",
            "Water",
            "Electric",
            "Grass",
            "Ice",
            "Fighting",
            "Poison",
            "Ground",
            "Flying",
            "Psychic",
            "Bug",
            "Rock",
            "Ghost",
            "Dragon",
            "Dark",
            "Steel",
            "Fairy"
    );



    public Scene generateSearchScene() {
        HBox root = new HBox();

        ListView<String> listOfPokemon = new ListView<>();
        listOfPokemon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO - Switch to new scene when ListItem clicked
                // listView.getSelectionModel().getSelectedItem()
            }
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        TextField pokemonName = new TextField();
        pokemonName.setPromptText("Pokémon Name");

        ChoiceBox<String> type1 = new ChoiceBox<>(types);
        ChoiceBox<String> type2 = new ChoiceBox<>(types);
        ChoiceBox<String> generation = new ChoiceBox<>(
                FXCollections.observableArrayList("Any Generation", "1", "2", "3", "4", "5", "6")
        );
        type1.setTooltip(new Tooltip("First type filter"));
        type2.setTooltip(new Tooltip("Second type filter"));
        generation.setTooltip(new Tooltip("Generation filter"));

        CheckBox legendsOnly = new CheckBox("Legendaries Only");

        Button searchButton = new Button("Search");
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO - Update ListView with filtered entries
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO - Safely close client socket and end program
            }
        });

        // Arrange items in scene
        root.getChildren().add(listOfPokemon);
        root.getChildren().add(vBox);
        vBox.getChildren().add(pokemonName);
        vBox.getChildren().add(type1);
        vBox.getChildren().add(type2);
        vBox.getChildren().add(generation);
        vBox.getChildren().add(legendsOnly);
        vBox.getChildren().add(searchButton);
        vBox.getChildren().add(exitButton);

        return new Scene(root, windowWidth, windowHeight);
    }

    public Scene generateDataScene() {
        // TODO - Format data scene
        return new Scene(new VBox(), windowWidth, windowHeight);
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