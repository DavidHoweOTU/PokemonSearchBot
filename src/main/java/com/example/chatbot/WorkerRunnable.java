package com.example.chatbot;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class WorkerRunnable implements Runnable {

    protected Socket             s           = null;
    protected ArrayList<Integer> pokemonList = null;

    public WorkerRunnable(Socket s, ArrayList<Integer> pokemonList) {
        this.s = s;
        this.pokemonList = pokemonList;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String input = dis.readUTF();

            // Get new pokemon from index given
            try {
                int index = Integer.parseInt(input);
                // TODO - Send message with Pokemon corresponding to index number
                // ServerApplication.ph.getPokemon(index);

            }
            // Provide arrays from information
            catch (NumberFormatException e) {
                // Name array
                if (!ServerApplication.started) {
                    // TODO - Send array of Pokemon names
                    ServerApplication.started = true;
                }
                // Filtered indices
                else {
                    String[] filterContents = input.split(",");
                    ArrayList<Integer> filteredIndices = ServerApplication.ph.getArrayIndices(
                            filterContents[0], filterContents[1], filterContents[2], filterContents[3]
                    );
                    // TODO - Send message with indices
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
