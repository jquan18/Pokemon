import java.util.LinkedList;

public class Enemy {
    private final String name = "Gary";
    BagSystem enemyBag = new BagSystem();
    public Enemy() {
        enemyBag.pokemonList.add(0, new Pokemon("Pikachu", new String[]{"Electric"}, 35, 5, "Quick Attack","Normal", 19, "Wild Charge","Electric", 40));
        enemyBag.pokemonList.add(1, new Pokemon("Butterfree", new String[]{"Bug", "Flying"}, 60, 5, "String shot","Bug", 15, "Air Slash","Flying", 60));
        enemyBag.pokemonList.add(2, new Pokemon("Pidgeotto", new String[]{"Normal", "Flying"}, 63, 5, "Sand Attack","Ground", 8, "Gust","Flying", 100));
        enemyBag.pokemonList.add(3, new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 5, "Growth","Normal", 11, "Poison Powder", "Poison",90));
        enemyBag.pokemonList.add(4, new Pokemon("Charizard", new String[]{"Fire", "Flyinh"}, 78, 10, "Fire Spin","Fire", 20, "Dragon Claw","Dragon", 120));
        enemyBag.pokemonList.add(5, new Pokemon("Squirtle", new String[]{"Water"}, 44, 5, "Tackle","Normal", 10, "Water Gum","Water", 80));
    }
    public String getName() {
        return name;
    }

}
