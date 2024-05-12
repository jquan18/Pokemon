import java.util.*;
public class battleSystem {
    private Trainer trainer;
    private Enemy enemy;
    private Pokemon trainerCurrentPokemon;
    private Pokemon enemyCurrentPokemon;
    private String[] enemyType = {"Wild_Pokemon", "Gym_Leader", "Gary"};
    private HandingAbnormalInput inputchecker = new HandingAbnormalInput();

    public battleSystem(Trainer trainer , Enemy enemy) {
        this.trainer = new Trainer();
        this.enemy = new Enemy();
    }

    public void startBattle() {
        System.out.println("Battle Start: " + trainer.getName() + " and " + enemy.getName());
        chooseEnemyPokemon("Gary");
        chooseDefaultPokemon();

        while (trainer.trainerBag.pokemonList.checkStatus() && enemy.enemyBag.pokemonList.checkStatus() && trainer.isKeepStay()) {
            displayBattleStatus();
            trainerTurn();
//            if (!monsters.isAlive()) {
//                System.out.println("You defeated " + monsters.getName());
//                break;
//            }
//
//            monstersTurn();
//            if (!player.isAlive()) {
//                System.out.println("You are defeated by " + monsters.getName());
//                break;
//            }
        }
    }
    public void displayBattleStatus() {
        System.out.println(trainerCurrentPokemon.getName() + " " + trainerCurrentPokemon.getHpBar());
        System.out.println(enemyCurrentPokemon.getName() + " " + enemyCurrentPokemon.getHpBar());

    }
    public void chooseEnemyPokemon(String enemeyType) {
        switch (enemeyType) {
            /*
            For the "Wild_Pokemon", need to check current city first
             */
//            case "Wild_Pokemon" :
//                enemyCurrentPokemon =
            default :
                int num = new Random().nextInt(6);
                enemyCurrentPokemon = enemy.enemyBag.pokemonList.get(num);
                System.out.printf("%s sends out %s [Level %d] !\n", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());

        }
    }
    public void chooseDefaultPokemon() {
        trainerCurrentPokemon = trainer.trainerBag.pokemonList.get(0);
    }
    public void chooseTrainerPokemon(int index) {
        trainerCurrentPokemon = trainer.trainerBag.pokemonList.get(index - 1);
        System.out.printf("%s is sent out!\n", trainerCurrentPokemon.getName());
        trainerCurrentPokemon.getType().typeCounter(trainerCurrentPokemon, enemyCurrentPokemon);
    }
    public void trainerTurn() {
        System.out.println("It's your round!");
        System.out.printf("%s's Moves: \n", trainerCurrentPokemon.getName());
        System.out.printf("1. %s [%s]", trainerCurrentPokemon.getMoveName(0), trainerCurrentPokemon.getMoveType(0));
        System.out.printf("2. %s [%s]", trainerCurrentPokemon.getMoveName(1), trainerCurrentPokemon.getMoveType(1));

        Scanner sc = new Scanner(System.in);
        String input = "5070";
        while (true) {
            System.out.printf("Which move will %s use?", trainerCurrentPokemon.getName());
            input = sc.nextLine();
            if (inputchecker.checkAbnormalInput(input, "1", "2")) {
                switch (input) {
                    case "1":
//                        trainerCurrentPokemon.useQuickMove()
                    case "2":
                    default:
                        System.out.println("Error on battleSystem player turn");
                }
                break;
            }
        }

    }

    public void checkTypeCounter() {
        double multipliers;
//        if ()
    }

}