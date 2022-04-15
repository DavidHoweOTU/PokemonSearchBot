package com.example.chatbot;

import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

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
            ArrayList<String> pokemonNames = (ArrayList<String>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        // TODO - Save the pokemon names somewhere
    }

    private static void getFilteredIndices() {
        try {
            ArrayList<Integer> newIndices = (ArrayList<Integer>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        // TODO - Filter pokemon name list to only include filtered names, then update scene
    }

    private static void getPokemonFromName() {
        try {
            Pokemon newPokemon = (Pokemon) ois.readObject();
            Scene newScene = ClientApplication.generateDataScene(newPokemon);
            // TODO - Update scene with new Pokemon
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
