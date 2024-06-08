import org.w3c.dom.Node;

import java.util.LinkedList;
/*
This BagSystem is not a necessity. Just  reserve spcae if want to add item system (potion and something like that
)*/
public class BagSystem {
    PokemonList pokemonList;
    LinkedList<String> badgeList;

    // Trainer and Enemy will have their own bag, the different is Enemy cant called out their badgeList
    public BagSystem() {
        this.pokemonList = new PokemonList();
        this.badgeList = new LinkedList<>();
    }

    // Use for health all Pokemon inside the bag
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
