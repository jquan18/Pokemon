import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class MainClassPokemon {
    public static void main(String[] args) {
        ArrayList<Pokemon> pokemonList = new ArrayList<>();

        try {
            Scanner pokemon = new Scanner(new FileInputStream("C:\\Users\\User\\IdeaProjects\\Pokemon\\src\\Pokemon_Database.txt"));
            while (pokemon.hasNextLine()) {
                String name = pokemon.nextLine();
                int HP = Integer.parseInt(pokemon.nextLine());
                int attack = Integer.parseInt(pokemon.nextLine());
                int defense = Integer.parseInt(pokemon.nextLine());
                int evLevel = Integer.parseInt(pokemon.nextLine());
                int nextEvLevel = Integer.parseInt(pokemon.nextLine());
                double catchRate = Double.parseDouble(pokemon.nextLine());
                int appearanceRate = Integer.parseInt(pokemon.nextLine());
                String type1 = pokemon.nextLine();
                String type2 = pokemon.nextLine();
                // int index = i;
                int evolution = Integer.parseInt(pokemon.nextLine());
                Attack[] attacks = new Attack[2];
                for (int j = 0; j < 2; j++) {
                    String line = pokemon.nextLine();
                    String[] parts = line.split(",");
                    String move = parts[0];
                    //    int damage = parts[1];
                    int damage = Integer.parseInt(parts[1].trim());

                    attacks[j] = new Attack(move, damage);
                }
                int size = Integer.parseInt(pokemon.nextLine());
                Attack[] learnset = new Attack[size];
                int[] thresholds = new int[size];
                for (int j = 0; j < size; j++) {
                    String line = pokemon.nextLine();
                    String[] parts = line.split(",");
                    String move = parts[0];

                    int damage = Integer.parseInt(parts[1].trim());
                    learnset[j] = new Attack(move, damage);
                }
                for (int j = 0; j < size; j++) {
                    thresholds[j] = Integer.parseInt(pokemon.nextLine());
                }

                String[] strength = pokemon.nextLine().split(",");

                String[] weakness = pokemon.nextLine().split(",");

                Pokemon pokemon1 = new Pokemon(name, HP, attack, defense, evLevel, nextEvLevel, catchRate, appearanceRate, type1, type2, evolution, attacks, learnset, thresholds, strength, weakness);

                System.out.println(pokemon1.toString());
                if (pokemon.hasNextLine()) {
                    pokemon.nextLine(); // Read the empty line between Pokémon entries
                } else {
                    break; // Exit the loop if there are no more lines
                }

            }
            pokemon.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");

        }
    }
}