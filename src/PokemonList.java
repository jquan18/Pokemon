import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PokemonList{
    private int size;
    PokemonNode head;
    PokemonNode tail;
    public PokemonList() {
        this.size = 0;
        this.head = this.tail = null;
    }
    /*
    Need to test the catch pokemon. I think remove() will have some problem!
     */
    public void add(int index, Pokemon pokemon) {
        if (size >= 6) {
            throw new IllegalStateException("You can have only 6 pokemon in Bag! Please remove one of them first!");
        }
        else {
            if (index<0)
                throw new NoSuchElementException("Please enter a correct places...");
            else {
                PokemonNode current = head;
                for (int i=0; i<index; i++) {
                    current = current.next;
                }
                PokemonNode insert = new PokemonNode(pokemon, current, current.prev);
                current.prev.next = insert;
                current.prev = insert;
            }
            size++;
            System.out.println(pokemon.getName() + " is added into Bag! :)");
        }
    }
    public void remove(int index) throws Exception {
        Pokemon pokemon = null;
        if (size == 0) {
            throw new Exception("You have no Pokemon can be transfer...");
        }
        else if (index < 0 || index > 6) {
            throw new NoSuchElementException("Please enter a correct places...");
        }
        else {
            PokemonNode temp = head;
            for (int i=0; i<index; i++) {
                temp = temp.next;
            }
            pokemon = temp.pokemon;
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
            temp.prev = temp.next = null;
            size--;
        }
        System.out.println(pokemon.getName() + "had been transfer! :(");
    }
    public boolean checkStatus() {
        PokemonNode poke = head;
        int totalHP = 0;
        for (int i=0; i<getSize(); i++) {
            totalHP += poke.pokemon.HP;
            poke = poke.next;
        }

        if (totalHP == 0) {
            System.out.println("All your Pokemon have been defeated....");
            System.out.println("Good try...");
            return false;
        }
        return true;
    }
    public Pokemon get(int index) {
        if (index < 0 || index > 6) {
            throw new NoSuchElementException("PokemonList get(): Index is out of bound.");
        }
        PokemonNode current = head;
        for (int i=0; i<index; i++) {
            current = current.next;
        }
        return current.pokemon;
    }
    public int getSize() {
        return this.size;
    }
}
