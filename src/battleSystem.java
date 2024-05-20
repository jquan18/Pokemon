import java.util.*;
public class battleSystem {
    private final Trainer trainer;
    private final Enemy enemy;
    private Pokemon trainerCurrentPokemon;
    private Pokemon enemyCurrentPokemon;
    private final String[] enemyType = {"Wild_Pokemon", "Gym_Leader", "Gary"};
    private final HandingAbnormalInput inputChecker = new HandingAbnormalInput();

    public battleSystem(Trainer trainer , Enemy enemy) {
        this.trainer = trainer;
        this.enemy = enemy;
    }

    public void startBattle() {
        System.out.println("Battle Start: " + trainer.getName() + " and " + enemy.getName());
        chooseEnemyPokemon("Gary");
        chooseDefaultPokemon();

        while (trainer.trainerBag.pokemonList.checkStatus() && enemy.enemyBag.pokemonList.checkStatus() && trainer.isKeepStay()) {
            displayBattleStatus();
            if (enemyCurrentPokemon.speed > trainerCurrentPokemon.speed) {
                enemyTurn();
                if (!trainer.trainerBag.pokemonList.checkStatus()) {
                    System.out.println("You are defeated by " + enemy.getName());
                    break;
                }
                trainerTurn();
                if (!trainer.isKeepStay())
                    break;
                if (!enemy.enemyBag.pokemonList.checkStatus()) {
                    System.out.println("You are Win!");
                    break;
                }
            }
            else {
                trainerTurn();
                if (!trainer.isKeepStay())
                    break;
                if (!enemy.enemyBag.pokemonList.checkStatus()) {
                    System.out.println("You are Win!");
                    break;
                }
                enemyTurn();
                if (!trainer.trainerBag.pokemonList.checkStatus()) {
                    System.out.println("You are defeated by " + enemy.getName());
                    break;
                }
            }
        }
    }
    public void displayBattleStatus() {
        trainerCurrentPokemon.displayPokemonStatus();
        enemyCurrentPokemon.displayPokemonStatus();
    }
    public void chooseEnemyPokemon(String enemyType) {
        switch (enemyType) {
            /*
            For the "Wild_Pokemon", need to check current city first
             */
//            case "Wild_Pokemon" :
//                enemyCurrentPokemon =
            default :
                enemyCurrentPokemon = enemy.enemyBag.pokemonList.get(0);
                System.out.printf("%s sends out %s [Level %d] !\n", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());

        }
    }
    public void enemyChangePokemon() {
         PokemonNode current = enemy.enemyBag.pokemonList.head;
         while (current != null) {
             if (current.pokemon.getName().equalsIgnoreCase(enemyCurrentPokemon.getName())){
                 enemyCurrentPokemon = current.next.pokemon;
                 break;
             }
             current = current.next;
         }
        System.out.printf("%s sends out %s[Level %d] !", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());
    }
    public void chooseDefaultPokemon() {
        trainerCurrentPokemon = trainer.trainerBag.pokemonList.get(0);
    }
    public void chooseTrainerPokemon() {
        Scanner sc = new Scanner(System.in);
        String index = "5070";
        while (true) {
            System.out.print("Choose Pokemon: ");
            index = sc.nextLine();
            if (inputChecker.checkAbnormalInput(index, "1", "6")) {
                int position = Integer.parseInt(index);
                if (trainer.trainerBag.pokemonList.get(position-1).HP == 0)
                    System.out.println("This pokemon is not available now.");
                else {
                    trainerCurrentPokemon = trainer.trainerBag.pokemonList.get(position - 1);
                    System.out.printf("%s is sent out!\n", trainerCurrentPokemon.getName());
                    trainerCurrentPokemon.getType().typeCounter(trainerCurrentPokemon, enemyCurrentPokemon);
                    break;
                }
            }
        }
    }
    public void trainerTurn() {
        if (trainerCurrentPokemon.HP <= 0) {
            checkAvailablePokemon();
            chooseTrainerPokemon();
        }
        else {
            System.out.print("It's your round! ");
            System.out.printf("%s's Moves: \n", trainerCurrentPokemon.getName());
            System.out.printf("1. %s [%s] (%d/100)\n", trainerCurrentPokemon.getMoveName(0), trainerCurrentPokemon.getMoveType(0), trainerCurrentPokemon.quickMove.QMPoint);
            System.out.printf("2. %s [%s] (%d/8)\n", trainerCurrentPokemon.getMoveName(1), trainerCurrentPokemon.getMoveType(1), trainerCurrentPokemon.mainMove.MMPoint);
            System.out.println("3. Bag");
            System.out.println("4. Escape");
            System.out.println("5. Catch");

            Scanner sc = new Scanner(System.in);
            String input = "5070";
            System.out.printf("Which move will %s use?\n", trainerCurrentPokemon.getName());
            while (true) {
                System.out.print(">>> ");
                input = sc.nextLine();
                if (inputChecker.checkAbnormalInput(input, "1", "5")) {
                    switch (input) {
                        case "1":
                            trainerCurrentPokemon.useMove(1, enemyCurrentPokemon);
                            break;
                        case "2":
                            trainerCurrentPokemon.useMove(2, enemyCurrentPokemon);
                            break;
                        case "3":
                            checkAvailablePokemon();
                            chooseTrainerPokemon();
                            break;
                        case "4":
                            trainer.tryEscaped();
                            break;
                        case "5":
                            trainer.tryCatch();
                            break;
                        default:
                            System.out.println("Error on battleSystem player turn");
                    }
                    if (enemyCurrentPokemon.HP <= 0) {
                        trainerCurrentPokemon.
                    }
                    break;
                }
            }
        }

    }
    public void enemyTurn() {
        System.out.printf("%s Turn now!", enemy.getName());
        if (enemyCurrentPokemon.HP <= 0) {
            enemyChangePokemon();
        }
        else {
            int action = new Random().nextInt(3);
            switch (action) {
                case 0, 2:
                    enemyCurrentPokemon.useMove(1, trainerCurrentPokemon);
                    break;
                case 1:
                    enemyCurrentPokemon.useMove(2, trainerCurrentPokemon);
                    break;
            }
        }

    }

    public void checkAvailablePokemon() {
        PokemonNode pokemon = trainer.trainerBag.pokemonList.head;
        int i=1;
        System.out.println();
        while (pokemon != null) {
            if (pokemon.pokemon.getName().equalsIgnoreCase(trainerCurrentPokemon.getName())) {
                System.out.printf("%d. %s Lv.%d",i, pokemon.pokemon.getName(), pokemon.pokemon.getLevel());
                System.out.printf("[ %s ] (%d/%d)", Arrays.toString(pokemon.pokemon.getType().getTypeName()), pokemon.pokemon.HP, pokemon.pokemon.maxHP);
                System.out.println("[Current Use]");
            }
            else if (pokemon.pokemon.HP == 0){
                System.out.printf("%d. %s Lv.%d",i, pokemon.pokemon.getName(), pokemon.pokemon.getLevel());
                System.out.printf("[ %s ] (%d/%d)", Arrays.toString(pokemon.pokemon.getType().getTypeName()), pokemon.pokemon.HP, pokemon.pokemon.maxHP);
                System.out.println("[Cannot Use]");
            }
            else {
                System.out.printf("%d. %s Lv.%d",i, pokemon.pokemon.getName(), pokemon.pokemon.getLevel());
                System.out.printf("[ %s ] (%d/%d)\n", Arrays.toString(pokemon.pokemon.getType().getTypeName()), pokemon.pokemon.HP, pokemon.pokemon.maxHP);

            }
            pokemon = pokemon.next;
            i++;
        }

    }
}