package com.example.chatbot;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import static com.example.chatbot.ClientApplication.currentScene;

public class ClientHandler implements Runnable {
    private final int               port = 6666;
    public static Socket            s    = null;
    public static DataOutputStream  dos  = null;
    public static ObjectInputStream ois  = null;

    public int taskID;
    public String message;

    public ClientHandler(int taskID, String message) {
        this.taskID = taskID;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            s = new Socket("localhost", port);
            dos = new DataOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

            sendMessage();
            switch (taskID) {
                case 0 -> getPokemonNameList();
                case 1 -> getFilteredIndices();
                case 2 -> getPokemonFromName();
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }


    private void sendMessage() {
        try { dos.writeUTF(this.message); }
        catch (IOException e) {e.printStackTrace();}
    }

    private static void getPokemonNameList() {
        try {
            ClientApplication.originalPokemonList = (ArrayList<String>) ois.readObject();
            ClientApplication.filteredPokemonList = ClientApplication.originalPokemonList;
            ClientApplication.listOfPokemon.setItems(FXCollections.observableArrayList(
                    ClientApplication.originalPokemonList
            ));
        }
        catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
    }

    private static void getFilteredIndices() {
        try {
            ArrayList<Integer> newIndices = (ArrayList<Integer>) ois.readObject();

            ArrayList<String> filteredList = new ArrayList<>();
            for (int index : newIndices) {
                filteredList.add(ClientApplication.originalPokemonList.get(index));
            }
            ClientApplication.filteredPokemonList = filteredList;
            ClientApplication.listOfPokemon.getItems().setAll(FXCollections.observableArrayList(
                    ClientApplication.filteredPokemonList
            ));
        }
        catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
    }

    private static void getPokemonFromName() {
        try {
            String[] pokedata = ((String) ois.readObject()).split(",");
            Pokemon newPokemon = new Pokemon(
                    pokedata[0],
                    pokedata[1],
                    pokedata[2],
                    pokedata[3],
                    pokedata[4],
                    pokedata[5],
                    pokedata[6],
                    pokedata[7],
                    pokedata[8],
                    pokedata[9],
                    pokedata[10],
                    pokedata[11]
            );
            Scene newScene = ClientApplication.generateDataScene(newPokemon);
            ((Stage) currentScene.getWindow()).setScene(newScene);
            currentScene = newScene;
        }
        catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
    }


    public void endCommunications() {
        // close sockets
        try {
            dos.close();
            ois.close();
            s.close();
        }
        catch (IOException e) { e.printStackTrace(); }

        // close program
        Platform.exit();
    }
}
