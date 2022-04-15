package com.example.chatbot;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class WorkerRunnable implements Runnable {

    protected Socket s;
    public WorkerRunnable(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String input = dis.readUTF();

            // Get new Pokemon from index given
            try {
                int index = Integer.parseInt(input);
                Pokemon newPokemon = ServerApplication.ph.getPokemon(index);
                // TODO - Send message with Pokemon corresponding to index number

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
