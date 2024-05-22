import org.w3c.dom.Node;

import java.util.LinkedList;

public class BagSystem {
    PokemonList pokemonList;
    LinkedList<String> badgeList;

    public BagSystem() {
        this.pokemonList = new PokemonList();
        this.badgeList = new LinkedList<>();
    }

}
