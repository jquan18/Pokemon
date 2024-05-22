import javax.xml.namespace.QName;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Enemy {
    String name;
    BagSystem enemyBag;
    public Enemy(String name) {
        this.name = name;
        enemyBag = new BagSystem();
    }

//    private final String name = "Gary";
//    BagSystem enemyBag = new BagSystem();
//    public Enemy() {
//        try {
//            Scanner reader = new Scanner(new FileInputStream("src/res/Pokemon_dataBase/pokemon_database.txt"));
//            int i=0;
//            reader.nextLine();
//            reader.nextLine();
//            reader.nextLine();
//            reader.nextLine();
//            reader.nextLine();
//            reader.nextLine();
//            while (reader.hasNextLine()) {
//                String[] line = reader.nextLine().split(",");
//                String[] arr;
//                if (line[2] != null) {
//                    arr = new String[] {line[1], line[2]};
//                }
//                else {
//                    arr = new String[] {line[1]};
//                }
//
//                enemyBag.pokemonList.add(i, new Pokemon(line[0], arr, Integer.parseInt(line[3]),
//                        Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7], line[8],
//                        Integer.parseInt(line[9]), line[10], line[11], Integer.parseInt(line[12])));
//                i++;
//                if (i == 2) break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i=0; i<enemyBag.pokemonList.list.size(); i++) {
//            if (enemyBag.pokemonList.list.get(i) != null)
//                enemyBag.pokemonList.list.get(i).setMaster("Enemy");
//        }
//    }
//    public String getName() {
//        return name;
//    }
}
class GymLeader extends Enemy {
        public GymLeader(String name) {
            super(name);
            loadPokemon();
        }
        public void loadPokemon() {
            String currentWorkingDir = System.getProperty("user.dir");
            String relativePath = currentWorkingDir + "/src/res/Pokemon_database/GymLeader_Pokemon/GymLeader_Pokemon.txt";
            inputReader.readGymPokemon(relativePath, this);
        }
}
class Wild_Pokemon extends Enemy {
    public Wild_Pokemon(String name) {
        super(name);
    }
    //load current city wild pokemon
    // random select the pokemon


}

