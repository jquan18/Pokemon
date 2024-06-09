

public class Enemy {
    String name;
    BagSystem enemyBag;
    public Enemy(String name) {
        this.name = name;
        enemyBag = new BagSystem();
    }

    // Method to load Pokémon from a file into the enemy's bag
    public void loadPokemon(String file, Enemy enemy) {
        String currentWorkingDir = System.getProperty("user.dir");
        // Construct the relative path to the Pokémon data file
        String relativePath = currentWorkingDir + file;
        // Read Pokémon data from the file and populate the enemy's bag
        inputReader.readPokemon(relativePath, enemy);
        // Remove null entries from the Pokémon list
        for (int i=enemyBag.pokemonList.list.size()-1; i>0; i--) {
            if (enemy.enemyBag.pokemonList.list.get(i) == null){
                enemy.enemyBag.pokemonList.list.remove(i);
            }
        }

    }
    // Getter method for the enemy's name
    public String getName() {
        return this.name;
    }
}
class GymLeader extends Enemy {
    // Constructor to initialize a GymLeader with a given name and load their Pokémon
        public GymLeader(String name) {
            super(name);
            loadPokemon("/src/res/Pokemon_database/GymLeader_Pokemon.txt", this);
        }
}
class Wild_Pokemon extends Enemy {
    // Constructor to initialize a Wild_Pokemon with a given name and load their Pokémon
    public Wild_Pokemon(String name) {
        super(name);
        loadPokemon("/src/res/Pokemon_database/Wild_Pokemon_Database.txt", this);
    }
    // Method to display the list of Pokémon in the enemy's bag
    public void showPokemon() {
        System.out.print("[ ");
        for (int i=0; i<enemyBag.pokemonList.list.size(); i++) {
            if (enemyBag.pokemonList.list.get(i) != null) {
                // Print the name of each Pokémon, separated by commas
                System.out.print(enemyBag.pokemonList.list.get(i).getName());
                System.out.print(enemyBag.pokemonList.list.size() == ( i+1 )? " ]\n" : ", ");
            }
        }

    }
    // random select the Pokemon
}
class Gary extends Enemy {
    // Constructor to initialize Gary with a given name and load his Pokémon
    public Gary(String name) {
        super(name);
        loadPokemon("/src/res/Pokemon_database/Gary_Pokemon.txt", this);
    }
}

