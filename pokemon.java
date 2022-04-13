import java.util.*;
import java.io.*;

public class pokemon {
    public static ArrayList<Integer> pokemon_legendary_finder(ArrayList<String> arr, String legendary){
        ArrayList<Integer> newls = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(legendary)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> pokemon_type_finder(ArrayList<String> arr, String type){
        ArrayList<Integer> newls = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(type)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> pokemon_generation_finder(ArrayList<String> arr, String generation){
        ArrayList<Integer> newls = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(generation)){
                newls.add(i);
            }
        }
        return newls;
    }
    public static ArrayList<Integer> index_compare(ArrayList<Integer> index1, ArrayList<Integer> index2){
        ArrayList<Integer> newls = new ArrayList<Integer>();
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


    public static void main(String[] args) throws Exception {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("pokemon.csv"));
        ArrayList<String> pokemon_name = new ArrayList<String>();
        ArrayList<String> pokemon_type = new ArrayList<String>();
        ArrayList<String> pokemon_type2 = new ArrayList<String>();
        ArrayList<String> pokemon_total = new ArrayList<String>();
        ArrayList<String> pokemon_HP = new ArrayList<String>();
        ArrayList<String> pokemon_Attack = new ArrayList<String>();
        ArrayList<String> pokemon_Defense = new ArrayList<String>();
        ArrayList<String> pokemon_spAtk = new ArrayList<String>();
        ArrayList<String> pokemon_spDef = new ArrayList<String>();
        ArrayList<String> pokemon_speed = new ArrayList<String>();
        ArrayList<String> pokemon_generation = new ArrayList<String>();
        ArrayList<String> pokemon_legendary = new ArrayList<String>();

        //initializing lists for values in csv
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



        //stuff the user will select on the UI
        //using for testing right now
        String user_type = "Fire";
        String user_generation = "1";
        String user_legendary = "False";
        String user_name = "";
        //indexes of the pokemon that match what the user is looking for
        ArrayList<Integer> pokemon_indexes = new ArrayList<Integer>();
        ArrayList<Integer> pokemon_indexes2 = new ArrayList<Integer>();
        ArrayList<Integer> pokemon_indexes3 = new ArrayList<Integer>();

        pokemon_indexes = pokemon_type_finder(pokemon_type, user_type);
        pokemon_indexes2 = pokemon_generation_finder(pokemon_generation, user_generation);
        pokemon_indexes3 = pokemon_legendary_finder(pokemon_legendary, user_legendary);

        ArrayList<Integer> index_1_2 = new ArrayList<Integer>();
        ArrayList<Integer> final_index = new ArrayList<Integer>();
        System.out.println(pokemon_indexes);

        //compares first 2 lists of pokemon and returns matches
        index_1_2 = index_compare(pokemon_indexes,pokemon_indexes2);

        //refines list to match the final list and returns all that match requirements
        final_index = index_compare(index_1_2, pokemon_indexes3);

        System.out.println(final_index);
        for (int i = 0; i < final_index.size(); i++){
            int x = final_index.get(i);
            System.out.println(pokemon_name.get(x));
        }
    }
}
