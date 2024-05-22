import java.io.*;
import java.util.*;

public class inputReader {
    private String inputReader;

    public inputReader() {
        inputReader = "";
    }

    public static void readPokemon(String file, Enemy enemy) {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equalsIgnoreCase(enemy.name)) {
                    int i = 0;
                    while (reader.hasNextLine()) {
                        line = reader.nextLine();
                        if (line.trim().isEmpty() || line.equalsIgnoreCase("===")) {
                            break;
                        }
                        String[] str = line.split(",");
                        if (str.length < 13) {
                            continue;  // Ensure there are enough elements in the split array
                        }
                        String[] arr;
                        if (str[2] != null) {
                            arr = new String[]{str[1], str[2]};
                        } else {
                            arr = new String[]{str[1]};
                        }
                        enemy.enemyBag.pokemonList.list.add(i, new Pokemon(str[0], arr, Integer.parseInt(str[3]),
                                Integer.parseInt(str[4]), Integer.parseInt(str[5]), Integer.parseInt(str[6]), str[7], str[8],
                                Integer.parseInt(str[9]), str[10], str[11], Integer.parseInt(str[12])
                        ));
                        i++;
                        if (enemy.getClass() == GymLeader.class || enemy.getClass() == Gary.class) {
                            if (i == 6) break;
                        }
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < enemy.enemyBag.pokemonList.list.size(); i++) {
            if (enemy.enemyBag.pokemonList.list.get(i) != null) {
                if (enemy.getClass() == GymLeader.class)
                    enemy.enemyBag.pokemonList.list.get(i).setMaster("Gym Leader");
                else if (enemy.getClass() == Gary.class)
                    enemy.enemyBag.pokemonList.list.get(i).setMaster("Gary");

                enemy.enemyBag.pokemonList.list.get(i).levelSystem.setDefaultLevel(enemy.name);
                //If pokemon atrributes is problem, I think will be here
                enemy.enemyBag.pokemonList.list.get(i).levelSystem.attributesIncrease(enemy.enemyBag.pokemonList.list.get(i));
            }
        }

    }
}


