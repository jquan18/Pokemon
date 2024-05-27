import org.w3c.dom.Node;

import java.util.LinkedList;

public class BagSystem {
    PokemonList pokemonList;
    LinkedList<String> badgeList;

    public BagSystem() {
        this.pokemonList = new PokemonList();
        this.badgeList = new LinkedList<>();
    }

    public void healPokemon() {
        for (Pokemon pokemon : pokemonList.list) {
            if (pokemon != null) {
                pokemon.HP = pokemon.maxHP;
                pokemon.quickMove.QMPoint = 35;
                pokemon.mainMove.MMPoint = 15;
            }
            else
                break;
        }
    }

}
