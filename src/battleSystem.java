import java.util.*;
public class battleSystem {
    private final Trainer trainer;
    private final Enemy enemy;
    private Pokemon trainerCurrentPokemon;
    private Pokemon enemyCurrentPokemon;
//    private final String[] enemyType = {"Wild_Pokemon", "Gym_Leader", "Gary"};
    private final HandingAbnormalInput inputChecker = new HandingAbnormalInput();

    public battleSystem(Trainer trainer , Enemy enemy) {
        this.trainer = trainer;
        this.enemy = enemy;
        startBattle();
    }

    public void startBattle() {
        System.out.println("Battle Start: " + trainer.getName() + " and " + enemy.getName());
        chooseEnemyPokemon(enemy);
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
                    getBadge();
                    break;
                }
            }
            else {
                trainerTurn();
                if (!trainer.isKeepStay())
                    break;
                if (!enemy.enemyBag.pokemonList.checkStatus()) {
                    System.out.println("You are Win!");
                    getBadge();
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
    public void chooseEnemyPokemon(Enemy enemy) {
        String enemyClass = enemy.getClass().getSimpleName();
        switch (enemyClass) {
            case "GymLeader", "Gary": {
                enemyCurrentPokemon = enemy.enemyBag.pokemonList.get(0);
                System.out.printf("%s sends out %s [Level %d] !\n", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());
                break;
            }
            case "Wild_Pokemon": {
                int index = new Random().nextInt(enemy.enemyBag.pokemonList.list.size());
                enemyCurrentPokemon = enemy.enemyBag.pokemonList.list.get(index);
                System.out.printf("%s is exploring the wild\n",trainer.getName());
                System.out.printf("Suddenly, the %s jumps out!\n",enemyCurrentPokemon.getName());
                break;
            }
            default :
                enemyCurrentPokemon = enemy.enemyBag.pokemonList.get(0);
                System.out.printf("%s sends out %s [Level %d] !\n", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());

        }
    }
    public void enemyChangePokemon() {

         for (int i=0; i<enemy.enemyBag.pokemonList.list.size(); i++) {
             Pokemon current = enemy.enemyBag.pokemonList.list.get(i);
             if (current.getName().equalsIgnoreCase(enemyCurrentPokemon.getName())) {
                 enemyCurrentPokemon = enemy.enemyBag.pokemonList.list.get(i + 1);
                 break;
             }
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
                if (trainer.trainerBag.pokemonList.list.get(position-1) == null)
                    System.out.println("This pokemon is not available now.");
                else if (trainer.trainerBag.pokemonList.get(position-1).HP == 0 )
                    System.out.println("That is no pokemon on this place");
                else {
                    trainerCurrentPokemon = trainer.trainerBag.pokemonList.get(position - 1);
                    System.out.printf("%s is sent out!\n", trainerCurrentPokemon.getName());
                    trainerCurrentPokemon.getType().typeCounter(trainerCurrentPokemon, enemyCurrentPokemon);
                    break;
                }
            }
            else {
                System.out.println("Developer: Huh...? I remember I just set 6 position only....");
            }
        }
    }
    public void trainerTurn() {
        if (trainerCurrentPokemon.HP <= 0) {
            trainer.checkAvailablePokemon(trainerCurrentPokemon);
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
                            trainer.checkAvailablePokemon(trainerCurrentPokemon);
                            chooseTrainerPokemon();
                            break;
                        case "4":
                            trainer.tryEscaped();
                            break;
                        case "5":
                            if (enemyCurrentPokemon.master.isEmpty())
                                trainer.tryCatch(trainerCurrentPokemon, enemyCurrentPokemon);
                            else
                                System.out.printf("%s: Don't underestimate the bond between me and my Pokémon! \n", enemy.getName());
                            break;
                        default:
                            System.out.println("Error on battleSystem player turn");
                    }
                    if (enemyCurrentPokemon.HP <= 0) {
                        trainerCurrentPokemon.gainEXP(enemyCurrentPokemon);
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

    public void getBadge() {
        String[] leader  = {"Brock", "Misty", "Lt. Surge", "Erika", "Koga", "Sabrina", "Blaine", "Giovanni"};
        String[] badgeList = {"The Boulder Badge", "The Cascade Badge", "The Thunder Badge", "The Rainbow Badge", "The Soul Badge", "The Marsh Badge", "The Volcano Badge", "The Earth Badge"};
        if (enemy.getClass() == GymLeader.class) {
            for (int i=0; i< leader.length; i++) {
                if (enemy.name.equalsIgnoreCase(leader[i])) {
                    trainer.trainerBag.badgeList.add(badgeList[i]);
                    System.out.printf("%s get %s!", trainer.getName(), badgeList[i]);
                }
            }
        }
    }


}