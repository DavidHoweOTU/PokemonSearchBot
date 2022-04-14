package com.example.chatbot;

import java.util.*;
import java.io.*;

public class PokemonHandler {
    public final ArrayList<String> pokemon_name         = new ArrayList<>();
    public final ArrayList<String> pokemon_type         = new ArrayList<>();
    public final ArrayList<String> pokemon_type2        = new ArrayList<>();
    public final ArrayList<String> pokemon_total        = new ArrayList<>();
    public final ArrayList<String> pokemon_HP           = new ArrayList<>();
    public final ArrayList<String> pokemon_Attack       = new ArrayList<>();
    public final ArrayList<String> pokemon_Defense      = new ArrayList<>();
    public final ArrayList<String> pokemon_spAtk        = new ArrayList<>();
    public final ArrayList<String> pokemon_spDef        = new ArrayList<>();
    public final ArrayList<String> pokemon_speed        = new ArrayList<>();
    public final ArrayList<String> pokemon_generation   = new ArrayList<>();
    public final ArrayList<String> pokemon_legendary    = new ArrayList<>();

    public PokemonHandler() {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("pokemon.csv"));

            // initializing lists for values in csv
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pokemon_name.add(values[1]);
                pokemon_type.add(values[2]);
                pokemon_type2.add(values[3]);
                pokemon_total.add(values[4]);
                pokemon_HP.add(values[5]);
                pokemon_Attack.add(values[6]);
                pokemon_Defense.add(values[7]);
                pokemon_spAtk.add(values[8]);
                pokemon_spDef.add(values[9]);
                pokemon_speed.add(values[10]);
                pokemon_generation.add(values[11]);
                pokemon_legendary.add(values[12]);
            }

            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }


    public static ArrayList<Integer> pokemon_legendary_finder(ArrayList<String> arr, String legendary){
        ArrayList<Integer> newls = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(legendary)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> pokemon_type_finder(ArrayList<String> arr, String type){
        ArrayList<Integer> newls = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(type)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> pokemon_generation_finder(ArrayList<String> arr, String generation){
        ArrayList<Integer> newls = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(generation)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> pokemon_name_finder(ArrayList<String> arr, String pokemon) {
        ArrayList<Integer> newls = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).contains(pokemon)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> index_compare(ArrayList<Integer> index1, ArrayList<Integer> index2){
        ArrayList<Integer> newls = new ArrayList<>();
        for (int i = 0; i < index1.size(); i++ ){
            for (int j = 0; j < index2.size(); j++ ){
                if(index1.get(i).equals(index2.get(j))){
                    int x = index1.get(i);
                    newls.add(x);
                }
            }
        }
        return newls;
    }

    public ArrayList<Integer> getArrayIndices(String user_type, String user_generation, String user_legendary, String user_name) {
        // indexes of the pokemon that match what the user is looking for
        ArrayList<Integer> pokemon_indexes = pokemon_type_finder(pokemon_type, user_type);
        ArrayList<Integer> pokemon_indexes2 = pokemon_generation_finder(pokemon_generation, user_generation);
        ArrayList<Integer> pokemon_indexes3 = pokemon_legendary_finder(pokemon_legendary, user_legendary);
        ArrayList<Integer> pokemon_indexes4 = pokemon_name_finder(pokemon_name, user_name);

        // compares lists of pokemon and returns matches
        ArrayList<Integer> index_1_2 = index_compare(pokemon_indexes,pokemon_indexes2);
        ArrayList<Integer> index_3_4 = index_compare(pokemon_indexes3, pokemon_indexes4);

        // refines list to match the final list and returns all that match requirements
        return index_compare(index_1_2, index_3_4);
    }

    public Pokemon getPokemon(int index) {
        return new Pokemon(
                pokemon_name.get(index),
                pokemon_type.get(index),
                pokemon_type2.get(index),
                pokemon_generation.get(index),
                pokemon_legendary.get(index),
                pokemon_HP.get(index),
                pokemon_Attack.get(index),
                pokemon_Defense.get(index),
                pokemon_spAtk.get(index),
                pokemon_spDef.get(index),
                pokemon_speed.get(index),
                pokemon_total.get(index)
        );
    }
}
