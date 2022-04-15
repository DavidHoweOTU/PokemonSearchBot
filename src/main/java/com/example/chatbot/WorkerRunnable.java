package com.example.chatbot;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class WorkerRunnable implements Runnable {

    protected Socket s;
    public WorkerRunnable(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            String input = dis.readUTF();

            System.out.println(input);
            System.out.println(input.split(",")[0]);

            // Name array
            if (!ServerApplication.started) {
                sendArrayOfPokemonNames(oos);
                ServerApplication.started = true;
            }
            // Filtered indices
            else if (!Objects.equals(input.split(",")[0], input)) {
                String[] filterContents = input.split(",", -1);
                sendFilteredIndices(oos, filterContents);
            }
            // Get new Pokemon from index given
            else { sendPokemonData(oos, input); }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPokemonData(ObjectOutputStream oos, String name) throws IOException {
        Pokemon newPokemon = ServerApplication.ph.getPokemon(name);
        String pokedata = newPokemon.name() + ","
                + newPokemon.type1() + ","
                + newPokemon.type2() + ","
                + newPokemon.generation() + ","
                + newPokemon.isLegendary() + ","
                + newPokemon.hp() + ","
                + newPokemon.atk() + ","
                + newPokemon.def() + ","
                + newPokemon.spa() + ","
                + newPokemon.spd() + ","
                + newPokemon.spe() + ","
                + newPokemon.total();
        oos.writeObject(pokedata);
    }

    private void sendArrayOfPokemonNames(ObjectOutputStream oos) throws IOException {
        ArrayList<String> pokemonNames = ServerApplication.ph.pokemon_name;
        oos.writeObject(pokemonNames);
    }

    private void sendFilteredIndices(ObjectOutputStream oos, String[] filterContents) throws IOException {
        ArrayList<Integer> filteredIndices = ServerApplication.ph.getArrayIndices(
                filterContents[0], filterContents[1], filterContents[2].toUpperCase(), filterContents[3]
        );
        oos.writeObject(filteredIndices);
    }
}
