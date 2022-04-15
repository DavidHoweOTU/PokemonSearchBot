package com.example.chatbot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ClientApplication extends Application {
    static ClientHandler ch = null;

    private static final int windowHeight = 600;
    private static final int windowWidth  = 600;

    private static final ObservableList<String> types = FXCollections.observableArrayList(
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

    public static ArrayList<String> originalPokemonList;
    public static ArrayList<String> filteredPokemonList;

    public static ListView<String> listOfPokemon;

    public static Scene currentScene;

    public static Scene generateSearchScene() {
        HBox root = new HBox();

        listOfPokemon = new ListView<>();

        // Load pokemon names
        ch = new ClientHandler(0, "AAAA");
        new Thread(ch).start();

        listOfPokemon.setOnMouseClicked(event -> {
            String name = listOfPokemon.getSelectionModel().getSelectedItem();
            ch = new ClientHandler(2, name);
            Platform.runLater(ch);
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        TextField pokemonName = new TextField();
        pokemonName.setPromptText("Pokémon Name");

        ChoiceBox<String> type = new ChoiceBox<>(types);
        type.getSelectionModel().selectFirst();
        ChoiceBox<String> generation = new ChoiceBox<>(
                FXCollections.observableArrayList("Any Generation", "1", "2", "3", "4", "5", "6")
        );
        generation.getSelectionModel().selectFirst();
        type.setTooltip(new Tooltip("Type filter"));
        generation.setTooltip(new Tooltip("Generation filter"));

        CheckBox legendsOnly = new CheckBox("Legendaries Only");

        Button searchButton = new Button("Search");
        searchButton.setOnMouseClicked(event -> {
            String filters = type.getValue() + ","
                            + generation.getValue() + ","
                            + legendsOnly.isSelected() + ","
                            + pokemonName.getText();
            ch = new ClientHandler(1, filters);
            Platform.runLater(ch); //new Thread(ch).start();
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(event -> {
            ch.endCommunications();
        });

        // Arrange items in scene
        root.getChildren().add(listOfPokemon);
        root.getChildren().add(vBox);
        vBox.getChildren().add(pokemonName);
        vBox.getChildren().add(type);
        vBox.getChildren().add(generation);
        vBox.getChildren().add(legendsOnly);
        vBox.getChildren().add(searchButton);
        vBox.getChildren().add(exitButton);

        return new Scene(root, windowWidth, windowHeight);
    }

    public static Scene generateDataScene(Pokemon p) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));

        Label pName = new Label(p.name());
        pName.setFont(Font.font(22));

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        Label type = new Label("Type:");
        Label pType1 = new Label(p.type1());
        Label pType2 = new Label(p.type2());
        Label gen = new Label("Generation:");
        Label pGen = new Label(p.generation());
        Label legend = new Label("Legendary:");
        Label pLegend = new Label(p.isLegendary());

        GridPane gridPane = new GridPane();
        Label hp = new Label("HP:");
        Label pHp = new Label(p.hp());
        Label atk = new Label("Atk:");
        Label pAtk = new Label(p.atk());
        Label def = new Label("Def:");
        Label pDef = new Label(p.def());
        Label spa = new Label("SpA:");
        Label pSpa = new Label(p.spa());
        Label spd = new Label("SpD:");
        Label pSpd = new Label(p.spd());
        Label spe = new Label("Spe:");
        Label pSpe = new Label(p.spe());
        Label total = new Label("Total:");
        Label pTotal = new Label(p.total());
        Button backButton = new Button("Back");

        backButton.setOnMouseClicked(event -> {
            // TODO - Return to the first scene
        });

        // add items to gridPane
        gridPane.add(hp,    0, 0);
        gridPane.add(atk,   0, 1);
        gridPane.add(def,   0, 2);
        gridPane.add(spa,   0, 3);
        gridPane.add(spd,   0, 4);
        gridPane.add(spe,   0, 5);
        gridPane.add(total, 0, 6);

        gridPane.add(backButton, 0, 7);

        gridPane.add(pHp,    1, 0);
        gridPane.add(pAtk,   1, 1);
        gridPane.add(pDef,   1, 2);
        gridPane.add(pSpa,   1, 3);
        gridPane.add(pSpd,   1, 4);
        gridPane.add(pSpe,   1, 5);
        gridPane.add(pTotal, 1, 6);

        // add items to root
        root.getChildren().add(pName);
        root.getChildren().add(hBox);
        hBox.getChildren().add(type);
        hBox.getChildren().add(pType1);
        hBox.getChildren().add(pType2);
        hBox.getChildren().add(gen);
        hBox.getChildren().add(pGen);
        hBox.getChildren().add(legend);
        hBox.getChildren().add(pLegend);
        root.getChildren().add(gridPane);

        return new Scene(root, windowWidth, windowHeight);
    }


    @Override
    public void start(Stage stage) throws IOException {

        // Create scene
        currentScene = generateSearchScene();
        stage.setTitle("Pokémon Search Bot");
        stage.setScene(currentScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}