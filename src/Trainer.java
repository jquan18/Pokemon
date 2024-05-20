import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Trainer {
    private final String name = "Ash";
    private boolean keepStay = true;

    BagSystem trainerBag = new BagSystem();
    public Trainer () {
        try {
            Scanner reader = new Scanner(new FileInputStream("src/res/Pokemon_dataBase/pokemon_database.txt"));
            int i=0;
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                String[] arr;
                if (line[2] != null && !line[2].isEmpty()) {
                    arr = new String[2];
                    arr[0] = line[1];
                    arr[1] = line[2];
                }
                else {
                    arr = new String[1];
                    arr[0] = line[1];
                }

                trainerBag.pokemonList.add(i, new Pokemon(line[0], arr, Integer.parseInt(line[3]),
                        Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7], line[8],
                                Integer.parseInt(line[9]), line[10], line[11], Integer.parseInt(line[12])));
                i++;
                if (i == 6) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        trainerBag.pokemonList.add(0, new Pokemon("Pikachu", new String[]{"Electric"}, 35, 5, "Quick Attack","Normal", 19, "Wild Charge","Electric", 70));
//        trainerBag.pokemonList.add(1, new Pokemon("Butterfree", new String[]{"Bug", "Flying"}, 60, 5, "String shot","Bug", 15, "Air Slash","Flying", 60));
//        trainerBag.pokemonList.add(2, new Pokemon("Pidgeotto", new String[]{"Normal", "Flying"}, 63, 5, "Sand Attack","Ground", 8, "Gust","Flying", 100));
//        trainerBag.pokemonList.add(3, new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 5, "Growth","Normal", 11, "Poison Powder", "Poison",90));
//        trainerBag.pokemonList.add(4, new Pokemon("Charizard", new String[]{"Fire", "Flyinh"}, 78, 10, "Fire Spin","Fire", 20, "Dragon Claw","Dragon", 120));
//        trainerBag.pokemonList.add(5, new Pokemon("Squirtle", new String[]{"Water"}, 44, 5, "Tackle","Normal", 10, "Water Gum","Water", 80));
    }
    public boolean isKeepStay() {
        return keepStay;
    }
    public void tryEscaped() {
        int chance = new Random().nextInt(2);
        if (chance == 0) {
            System.out.println("Escaped successfully");
            keepStay = false;
        }
        else
            System.out.println("Failed to escape");
    }
    public void tryCatch() {
        int change = new Random().nextInt(11) ;
        if (change > 3) {
            trainerBag.pokemonList.add();
        }

    }

    public String getName() {
        return name;
    }
}
