
import java.io.*;
import java.util.*;

/*
PokemonList is used for make a direct change to the list of pokemon. For example, when using cheat code, detect the whole status of the list
 */
public class PokemonList{

    ArrayList<Pokemon> list;
    public PokemonList() {
        list = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            list.add(null);
        }
    }

     public void add(int index, Pokemon pokemon) {
         if (index<0 || index> 6)
             throw new NoSuchElementException("Please enter a correct places...");
         else {
             list.set(index, pokemon);
             System.out.printf("%s is added into your bag!\n", pokemon.getName());
         }

    }
    public void remove(int index) {
         if (index < 0 || index > 6) {
            throw new NoSuchElementException("Please enter a correct places...");
         }
         else {
             Pokemon pokemon = list.get(index);
             list.set(index, null);
             System.out.print(pokemon.getName() + "had been transfer!");
         }

    }
    // Check the whole status of pokemon
    public boolean checkStatus(Pokemon pokemon) {
        int totalHP = 0;
        for (int i=0; i<list.size(); i++) {
            if (list.get(i) == null )
                break;
            totalHP += list.get(i).HP;
        }

        if (totalHP == 0 ) {
            if (pokemon.master.equalsIgnoreCase("trainer")) {
                System.out.println("All your Pokemon have been defeated....");
                System.out.println("Good try...");
            }
            else {
                System.out.println("You have defeat the enemy!");
                System.out.println("Good Job!");
            }
            return false;
        }
        return true;
    }

    // Change the trainer pokemon list to strongest! tester and developer no need to train their pokemon from zero
    public void useCheatCode() {
        for (int i = 0; i < 6; i++) {
            list.set(i, null);
        }
        try {
            Scanner reader = new Scanner(new FileInputStream("src/res/Pokemon_database/pokemon_special.txt"));
            int i = 0;
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                String[] arr;
                if (line[2] != null && !line[2].isEmpty()) {
                    arr = new String[2];
                    arr[0] = line[1];
                    arr[1] = line[2];
                } else {
                    arr = new String[1];
                    arr[0] = line[1];
                }

                list.add(i, new Pokemon(line[0], arr, Integer.parseInt(line[3]),
                        Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7], line[8],
                        Integer.parseInt(line[9]), line[10], line[11], Integer.parseInt(line[12])));
                i++;
                if (i == 6) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Pokemon pokemon : list) {
            if (pokemon != null) {
                pokemon.setMaster("Trainer");
                pokemon.setLevel("Cheating");
            }

        }
    }
    //Use for save game
	public ArrayList<Pokemon> getAllPokemon() {
        return new ArrayList<>(list);
    }
}
