import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class battleSystem {
    private Trainer trainer;
    private Enemy enemy;
    private Pokemon trainerCurrentPokemon = trainer.trainerBag.pokemonList.get(0);
    private Pokemon enemyCurrentPokemon;
    private String[] enemyType = {"Wild_Pokemon", "Gym_Leader", "Gary"};

    public battleSystem(Trainer trainer , Enemy enemy) {
        this.trainer = new Trainer();
        this.enemy = new Enemy();
    }

    public void startBattle() {
        System.out.println("Battle Start: " + trainer.getName() + " and " + enemy.getName());

        while (trainer.trainerBag.pokemonList.checkStatus() && enemy.enemyBag.pokemonList.checkStatus() && trainer.isKeepStay()) {
            displayBattleStatus();

            playerTurn();
            if (!monsters.isAlive()) {
                System.out.println("You defeated " + monsters.getName());
                break;
            }

            monstersTurn();
            if (!player.isAlive()) {
                System.out.println("You are defeated by " + monsters.getName());
                break;
            }
        }
    }
    public void displayBattleStatus() {

    }
    public void chooseEnemyPokemon(String enemeyType) {
        switch (enemeyType) {
            /*
            For the "Wild_Pokemon", need to check current city first
             */
            case "Wild_Pokemon" :
                enemyCurrentPokemon =
            default :
                int num = new Random().nextInt(6);
                enemyCurrentPokemon = enemy.enemyBag.pokemonList.get(num);
                System.out.printf("%s sends out %s [Level %d]", enemy.getName(), enemyCurrentPokemon.getName(), enemyCurrentPokemon.getLevel());

        }

        int num = new Random().nextInt();

    }

}