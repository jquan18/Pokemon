import java.util.*;

/*
This Battle System will is creating a new condition for Trainer and his enemy to fight
 */
public class battleSystem {
    private final Trainer trainer;
    private final Enemy enemy;
    private Pokemon trainerCurrentPokemon;
    private Pokemon enemyCurrentPokemon;
    private boolean isWildPokemon, catched, keepBattle;
    private final HandingAbnormalInput inputChecker = new HandingAbnormalInput();

    // Everytime when trainer match will an enemy, new battle start ( all the boolean condition use for ensure the battle is continued will be reset)
    public battleSystem(Trainer trainer, Enemy enemy) {
        this.trainer = trainer;
        this.enemy = enemy;
        this.trainerCurrentPokemon = trainer.trainerBag.pokemonList.list.get(0);
        this.isWildPokemon = enemy instanceof Wild_Pokemon;     // Check the enemy is wild pokemon or not, Wild pokemon will have diff battle logic with GymLeader and Gary
        this.catched = false;
        this.keepBattle = true;
        this.startBattle();
    }

    private void startBattle() {
        if (!isWildPokemon) {
            System.out.println("Battle Start: " + trainer.getName() + " vs. " + enemy.getName());
        }
        chooseEnemyPokemon();
        trainer.keepStay = true;
        while (keepBattle && trainer.trainerBag.pokemonList.checkStatus(trainerCurrentPokemon) && enemy.enemyBag.pokemonList.checkStatus(enemyCurrentPokemon) && trainer.isKeepStay() && !catched) {
            displayBattleStatus();
            if (enemyCurrentPokemon.speed > trainerCurrentPokemon.speed) {
                if (isWildPokemon && enemyCurrentPokemon.HP <= 0) {
                    break;
                }
                enemyTurn();
                if (!trainer.trainerBag.pokemonList.checkStatus(trainerCurrentPokemon)) {
                    System.out.println("You are defeated by " + enemy.getName());
                    break;
                }
                trainerTurn();
                if (!trainer.isKeepStay() && catched)
                    break;
                if (!enemy.enemyBag.pokemonList.checkStatus(enemyCurrentPokemon)) {
                    System.out.println("You are Win!");
                    getBadge();
                    break;
                }
            } else {
                trainerTurn();
                if (!trainer.isKeepStay() && catched) break;
                if (!enemy.enemyBag.pokemonList.checkStatus(enemyCurrentPokemon)) {
                    System.out.println("You Win!");
                    getBadge();
                    break;
                }
                enemyTurn();
            }

            if (!trainer.trainerBag.pokemonList.checkStatus(enemyCurrentPokemon)) {
                System.out.println("You are defeated by " + enemy.getName());
                break;
            }
        }
    }

    private void displayBattleStatus() {
        if (isWildPokemon && enemyCurrentPokemon.HP ==0)
            return;

        System.out.println("Battle Status:");
        trainerCurrentPokemon.displayPokemonStatus();
        enemyCurrentPokemon.displayPokemonStatus();
        System.out.println();
    }

    private void chooseEnemyPokemon() {
        if (isWildPokemon) {
            int index = new Random().nextInt(enemy.enemyBag.pokemonList.list.size());
            enemyCurrentPokemon = enemy.enemyBag.pokemonList.list.get(index);
            System.out.printf("A wild %s appeared!\n", enemyCurrentPokemon.getName());
        } else {
            enemyCurrentPokemon = enemy.enemyBag.pokemonList.list.get(0);
            System.out.printf("%s sends out %s [Level %d]!\n", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());
        }
    }

    private void enemyChangePokemon() {
        if (isWildPokemon) {
            return;  // Wild Pokémon do not switch out.
        } else {
            for (int i = 0; i < enemy.enemyBag.pokemonList.list.size(); i++) {
                Pokemon current = enemy.enemyBag.pokemonList.list.get(i);
                if (current.getName().equalsIgnoreCase(enemyCurrentPokemon.getName())) {
                    if (i + 1 < enemy.enemyBag.pokemonList.list.size()) {
                        enemyCurrentPokemon = enemy.enemyBag.pokemonList.list.get(i + 1);
                    }
                    break;
                }
            }
            System.out.printf("%s sends out %s [Level %d]!\n", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());
        }
    }

    private void chooseTrainerPokemon() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Choose a Pokémon: ");
            String input = sc.nextLine();
            if (inputChecker.checkAbnormalInput(input, "1", "6")) {
                int position = Integer.parseInt(input) - 1;
                if (position >= 0 && position < trainer.trainerBag.pokemonList.list.size()) {
                    Pokemon chosenPokemon = trainer.trainerBag.pokemonList.list.get(position);
                    if (chosenPokemon.HP > 0) {
                        trainerCurrentPokemon = chosenPokemon;
                        System.out.printf("%s, I choose you!\n", trainerCurrentPokemon.getName());
                        break;
                    } else {
                        System.out.println("That Pokémon has fainted.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private void trainerTurn() {
        if (trainerCurrentPokemon.HP <= 0) {
            System.out.println(trainerCurrentPokemon.getName() + " has fainted!");
            chooseTrainerPokemon();
        } else {
            System.out.printf("It's your turn! %s's moves:\n", trainerCurrentPokemon.getName());
            System.out.printf("1. %s [%s] (%d/35)\n", trainerCurrentPokemon.getMoveName(0), trainerCurrentPokemon.getMoveType(0), trainerCurrentPokemon.quickMove.QMPoint);
            System.out.printf("2. %s [%s] (%d/15)\n", trainerCurrentPokemon.getMoveName(1), trainerCurrentPokemon.getMoveType(1), trainerCurrentPokemon.mainMove.MMPoint);
            System.out.println("3. Bag");
            System.out.println("4. Escape");
            System.out.println("5. Catch");

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print(">>> ");
                String input = sc.nextLine();
                if (inputChecker.checkAbnormalInput(input, "1", "5")) {
                    switch (input) {
                        case "1":
                            if (trainerCurrentPokemon.quickMove.QMPoint <=0 ) {
                                System.out.println("It doesn't have enough power to use this move!");
                            }
                            else
                                trainerCurrentPokemon.useMove(1, enemyCurrentPokemon);
                            break;
                        case "2":
                            if (trainerCurrentPokemon.mainMove.MMPoint <= 0) {
                                System.out.println("It doesn't have enough power to use this move!");
                            }
                            else
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
                            if (enemyCurrentPokemon.master.isEmpty()) {
                                catched = trainer.tryCatch(trainerCurrentPokemon, enemyCurrentPokemon);
                            } else {
                                System.out.println("Fled!");
                            }
                            break;
                        default:
                            System.out.println("Error: Invalid option.");
                    }
                    if (enemyCurrentPokemon.HP <= 0) {
                        trainerCurrentPokemon.gainEXP(enemyCurrentPokemon);
                    }
                    break;
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    private void enemyTurn() {
        if (enemyCurrentPokemon.HP <= 0) {
            if (isWildPokemon) {
                System.out.println("The wild " + enemyCurrentPokemon.getName() + " fainted!");
                keepBattle = false;
            } else {
                System.out.println(enemyCurrentPokemon.getName() + " fainted!");
                enemyChangePokemon();
            }
        } else {
            if (!isWildPokemon)
                System.out.printf("%s's turn now!\n", enemy.getName());
            else
                System.out.printf("%s's turn now!\n", enemyCurrentPokemon.getName());

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

    private void getBadge() {
        String[] leaders = {"Brock", "Misty", "Lt. Surge", "Erika", "Koga", "Sabrina", "Blaine", "Giovanni"};
        String[] badgeList = {"Boulder Badge", "Cascade Badge", "Thunder Badge", "Rainbow Badge", "Soul Badge", "Marsh Badge", "Volcano Badge", "Earth Badge"};
        if (enemy instanceof GymLeader) {
            for (int i = 0; i < leaders.length; i++) {
                if (enemy.getName().equalsIgnoreCase(leaders[i])) {
                    trainer.trainerBag.badgeList.add(badgeList[i]);
                    System.out.printf("%s received the %s!\n", trainer.getName(), badgeList[i]);
                }
            }
        }
    }
}