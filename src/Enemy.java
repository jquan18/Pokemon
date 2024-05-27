

public class Enemy {
    String name;
    BagSystem enemyBag;
    public Enemy(String name) {
        this.name = name;
        enemyBag = new BagSystem();
    }
    public void loadPokemon(String file, Enemy enemy) {
        String currentWorkingDir = System.getProperty("user.dir");
        String relativePath = currentWorkingDir + file;
        inputReader.readPokemon(relativePath, enemy);
        for (int i=enemyBag.pokemonList.list.size()-1; i>0; i--) {
            if (enemy.enemyBag.pokemonList.list.get(i) == null){
                enemy.enemyBag.pokemonList.list.remove(i);
            }
        }

    }
    public String getName() {
        return this.name;
    }
}
class GymLeader extends Enemy {
        public GymLeader(String name) {
            super(name);
            loadPokemon("/src/res/Pokemon_database/GymLeader_Pokemon.txt", this);
        }
}
class Wild_Pokemon extends Enemy {
    public Wild_Pokemon(String name) {
        super(name);
        loadPokemon("/src/res/Pokemon_database/Wild_Pokemon_Database.txt", this);
    }
    public void showPokemon() {
        System.out.print("[ ");
        for (int i=0; i<enemyBag.pokemonList.list.size(); i++) {
            if (enemyBag.pokemonList.list.get(i) != null) {
                System.out.print(enemyBag.pokemonList.list.get(i).getName());
                System.out.print(enemyBag.pokemonList.list.size() == ( i+1 )? " ]\n" : ", ");
            }
        }

    }
    // random select the Pokemon
}
class Gary extends Enemy {
    public Gary(String name) {
        super(name);
        loadPokemon("/src/res/Pokemon_database/Gary_Pokemon.txt", this);
    }
}

