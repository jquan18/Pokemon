
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PokemonList{

    ArrayList<Pokemon> list;
    public PokemonList() {
        list = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            list.add(null);
        }
    }
    /*
    Need to test the catch pokemon. I think remove() will have some problem!
     */
     public void add(int index, Pokemon pokemon) {
         if (index<0 || index> 6)
             throw new NoSuchElementException("Please enter a correct places...");
         else {
             list.set(index, pokemon);
             System.out.printf("%s is added into your bag!\n", pokemon.getName());
         }

    }
    public void remove(int index) {
         if (index < 0 || index > 6) {
            throw new NoSuchElementException("Please enter a correct places...");
         }
         else {
             Pokemon pokemon = list.get(index);
             list.set(index, null);
             System.out.println(pokemon.getName() + "had been transfer!");
         }

    }
    public boolean checkStatus() {
        int totalHP = 0;
        for (int i=0; i<list.size(); i++) {
            if (list.get(i) == null )
                break;
            totalHP += list.get(i).HP;
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
        return list.get(index);
    }
}
