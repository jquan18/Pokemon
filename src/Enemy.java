import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Enemy {
    private final String name = "Gary";
    BagSystem enemyBag = new BagSystem();
    public Enemy() {
        try {
            Scanner reader = new Scanner(new FileInputStream("src/res/Pokemon_dataBase/pokemon_database.txt"));
            int i=0;
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                String[] arr;
                if (line[2] != null) {
                    arr = new String[] {line[1], line[2]};
                }
                else {
                    arr = new String[] {line[1]};
                }

                enemyBag.pokemonList.add(i, new Pokemon(line[0], arr, Integer.parseInt(line[3]),
                        Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7], line[8],
                        Integer.parseInt(line[9]), line[10], line[11], Integer.parseInt(line[12])));
                i++;
                if (i == 6) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        enemyBag.pokemonList.add(0, new Pokemon("Pikachu", new String[]{"Electric"}, 35, 5, "Quick Attack","Normal", 19, "Wild Charge","Electric", 40));
//        enemyBag.pokemonList.add(1, new Pokemon("Butterfree", new String[]{"Bug", "Flying"}, 60, 5, "String shot","Bug", 15, "Air Slash","Flying", 60));
//        enemyBag.pokemonList.add(2, new Pokemon("Pidgeotto", new String[]{"Normal", "Flying"}, 63, 5, "Sand Attack","Ground", 8, "Gust","Flying", 100));
//        enemyBag.pokemonList.add(3, new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 5, "Growth","Normal", 11, "Poison Powder", "Poison",90));
//        enemyBag.pokemonList.add(4, new Pokemon("Charizard", new String[]{"Fire", "Flyinh"}, 78, 10, "Fire Spin","Fire", 20, "Dragon Claw","Dragon", 120));
//        enemyBag.pokemonList.add(5, new Pokemon("Squirtle", new String[]{"Water"}, 44, 5, "Tackle","Normal", 10, "Water Gum","Water", 80));
    }
    public String getName() {
        return name;
    }

}
