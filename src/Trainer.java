import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Trainer {
    String name ;
    private boolean keepStay = true;
    HandingAbnormalInput inputChecker = new HandingAbnormalInput();

    BagSystem trainerBag = new BagSystem();
    public Trainer (String name) {
        this.name = name;
//        try {
//            Scanner reader = new Scanner(new FileInputStream("src/res/Pokemon_dataBase/pokemon_database.txt"));
//            int i=0;
//            reader.nextLine();
//            while (reader.hasNextLine()) {
//                String[] line = reader.nextLine().split(",");
//                String[] arr;
//                if (line[2] != null && !line[2].isEmpty()) {
//                    arr = new String[2];
//                    arr[0] = line[1];
//                    arr[1] = line[2];
//                }
//                else {
//                    arr = new String[1];
//                    arr[0] = line[1];
//                }
//
//                trainerBag.pokemonList.list.add(i, new Pokemon(line[0], arr, Integer.parseInt(line[3]),
//                        Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7], line[8],
//                                Integer.parseInt(line[9]), line[10], line[11], Integer.parseInt(line[12])));
//                i++;
//                if (i == 6) break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i=0; i<trainerBag.pokemonList.list.size(); i++) {
//            if (trainerBag.pokemonList.list.get(i) != null)
//                trainerBag.pokemonList.list.get(i).setMaster("Trainer");
//        }

    }
    public void choosePartnerPokemon(Pokemon pokemon) {
        pokemon.levelSystem.setDefaultLevel("Pallet Town");
        pokemon.setMaster("Trainer");
        trainerBag.pokemonList.list.add(pokemon);
    }
    public boolean isKeepStay() {
        return keepStay;
    }
    public void tryEscaped() {
        int chance = new Random().nextInt(2);
        if (chance == 0) {
            System.out.println("Escaped successfully");
            keepStay = false;
        }
        else
            System.out.println("Failed to escape");
    }
    public void tryCatch(Pokemon current, Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);
        int change = new Random().nextInt(11) ;

        if (change > 3) {
            System.out.println("Gotcha!");
            System.out.print("Enter the place of Pokemon(): ");
            String pos = "5040";
            checkAvailablePokemon(current);
            while (inputChecker.checkAbnormalInput(pos, "1", "6")) {
                pos = sc.next();
                if (trainerBag.pokemonList.list.get(Integer.parseInt(pos) - 1) != null) {
                    System.out.print("Pokemon on place will be transfer (YES/NO): ");
                    String str = sc.next();
                    if (str.equalsIgnoreCase("YES")) {
                        pokemon.setMaster("Trainer");
                        trainerBag.pokemonList.list.set(Integer.parseInt(pos) - 1, null);
                        trainerBag.pokemonList.list.set(Integer.parseInt(pos) - 1, pokemon);
                        break;
                    }
                }
                else {
                    pokemon.setMaster("Trainer");
                    trainerBag.pokemonList.list.set(Integer.parseInt(pos) - 1, pokemon);
                    break;
                }


            }

        }

    }

    public String getName() {
        return name;
    }
    public void checkAvailablePokemon(Pokemon trainerCurrentPokemon) {
        System.out.println();
        for (int i=0; i<6; i++) {
            Pokemon pokemon = trainerBag.pokemonList.list.get(i);
            if (pokemon == null) {
                System.out.println(i+1 +". [Empty]");
            }
            else {
                if (pokemon.getName().equalsIgnoreCase(trainerCurrentPokemon.getName())) {
                    System.out.printf("%d. %s Lv.%d",i+1, pokemon.getName(), pokemon.getLevel());
                    System.out.printf(" %s (%d/%d)", Arrays.toString(pokemon.getType().getTypeName()), pokemon.HP, pokemon.maxHP);
                    System.out.println("[Current Use]");
                }
                else if (pokemon.HP == 0){
                    System.out.printf("%d. %s Lv.%d",i+1, pokemon.getName(), pokemon.getLevel());
                    System.out.printf(" %s (%d/%d)", Arrays.toString(pokemon.getType().getTypeName()), pokemon.HP, pokemon.maxHP);
                    System.out.println("[Cannot Use]");
                }
                else {
                    System.out.printf("%d. %s Lv.%d",i+1, pokemon.getName(), pokemon.getLevel());
                    System.out.printf(" %s (%d/%d)\n", Arrays.toString(pokemon.getType().getTypeName()), pokemon.HP, pokemon.maxHP);

                }
            }
        }

    }
}
