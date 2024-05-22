import java.io.*;
import java.util.*;

public class inputReader {
    private String inputReader;
    public inputReader() {
        inputReader = "";
    }
    public static void readGymPokemon(String file, GymLeader leader) {
        try {
            Scanner reader = new Scanner(new File(file));
            String line = reader.nextLine();
            while (true) {
                if (line.equalsIgnoreCase(leader.name)) {
                    int i=0;
                    while (reader.hasNextLine()) {
                        String[] str = line.split(",");
                        String[] arr;
                        if (str[2] != null) {
                            arr = new String[] {str[1], str[2]};
                        }
                        else {
                            arr = new String[] {str[1]};
                        }
                        leader.enemyBag.pokemonList.list.add(i, new Pokemon(str[0], arr, Integer.parseInt(str[3]),
                            Integer.parseInt(str[4]), Integer.parseInt(str[5]), Integer.parseInt(str[6]), str[7], str[8],
                        Integer.parseInt(str[9]), str[10], str[11], Integer.parseInt(str[12])
                        ));
                        i++;
                        if (i==6) break;
                        if (line.trim().isEmpty())
                            break;
                    }
                }
                if (line.equalsIgnoreCase("===")) {
                    break;
                }
                line = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
