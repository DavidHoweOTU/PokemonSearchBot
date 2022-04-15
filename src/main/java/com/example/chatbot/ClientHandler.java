package com.example.chatbot;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
                case 2 -> getPokemonFromIndex();
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void sendMessage() {
        try { dos.writeUTF(this.message); }
        catch (IOException e) {e.printStackTrace();}
    }

    private static void getPokemonNameList() {
        // TODO - Handle pokemon list
    }

    private static void getFilteredIndices() {
        // TODO - Handle filtered list of indices
    }

    private static void getPokemonFromIndex() {
        // TODO - Handle Pokemon object
    }
}
