import java.util.LinkedList;

public class Enemy {
    private final String name = "Gary";
    BagSystem enemyBag = new BagSystem();
    public Enemy() {
        enemyBag.pokemonList.add(0, new Pokemon("Pikachu", new String[]{"Electrical"}, 35, 5, "Thunder Shock", 19, "Wild Charge", 40));
        enemyBag.pokemonList.add(1, new Pokemon("Butterfree", new String[]{"Bug", "Flying"}, 60, 5, "Harden", 15, "Bug BIte", 60));
        enemyBag.pokemonList.add(2, new Pokemon("Pidgeotto", new String[]{"Normal", "Flying"}, 63, 5, "Sand Attack", 8, "Gust", 100));
        enemyBag.pokemonList.add(3, new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 5, "Growth", 11, "Tackle", 90));
        enemyBag.pokemonList.add(4, new Pokemon("Charizard", new String[]{"Fire", "Flyinh"}, 78, 10, "Growl", 20, "Dragon Claw", 120));
        enemyBag.pokemonList.add(5, new Pokemon("Squirtle", new String[]{"Water"}, 44, 5, "Tail Whip", 10, "Water Gum", 80));
    }
    public String getName() {
        return name;
    }

}
