public class PokemonNode {
    Pokemon pokemon;
    PokemonNode next, prev;

    public PokemonNode(Pokemon pokemon, PokemonNode next, PokemonNode prev) {
        this.pokemon = pokemon;
        this.next = next;
        this.prev = prev;
    }
    public PokemonNode(Pokemon pokemon) {
        this(pokemon, null, null);
    }

}
