import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
QM = quick move (low damage, but more change )
MM = Main move (High damage, but less change)
 */
public class Pokemon{
    int maxHP, HP, speed, attack, defense;
    String name;
    Type type;
//    private ArrayList<Moves> moves = new ArrayList<>();
    QuickMove quickMove;
    MainMove mainMove;
    LevelSystem levelSystem;
    String master;
    String[] typeString;


    public Pokemon() {
        this(null, null, 0, 0,0, 0, null, null, 0, null, null, 0 );
    }
    public Pokemon(String name, String[] type, int HP,int attack, int defense, int speed, String QMName,String QMType, int QMDamage, String MMName, String MMType, int MMDamage) {
        this.name = name;
        this.type = new Type(type);
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxHP = HP;
        this.HP = HP;
        this.quickMove = new QuickMove(QMName, QMType, QMDamage);
        this.mainMove = new MainMove(MMName, MMType, MMDamage);
        this.levelSystem = new LevelSystem();
        this.master = "";
        this.typeString = type;
    }
    public void useMove(int index, Pokemon enemy) {
        int value = 0;
        switch (index) {
            case 1:
                quickMove.QMPoint -= 1;
                System.out.printf("%s used %s\n", this.name, this.quickMove.getMovesName());
                //Check toString()
                value = (int)((((2*getLevel())/2 + 2) * quickMove.getDPR() * (getAttack()/enemy.getDefense())/ 50) * quickMove.getMovesType().moveCounter(quickMove.getMovesTypeString(), enemy.getType().getMovesTypeString()));
                break;
            case 2:
                mainMove.MMPoint -= 1;
                System.out.printf("%s used %s", this.name, this.mainMove.getMovesName());
                value = (int)((((2*getLevel())/2 + 2) * quickMove.getDPR() * (getAttack()/enemy.getDefense())/ 50) * quickMove.getMovesType().moveCounter(quickMove.getMovesTypeString(), enemy.getType().getMovesTypeString()));
                break;

        }
        if (value < 5) {
            value = new Random().nextInt(5)+1;
        }
        receiveDamage(value, enemy);
    }

    public void receiveDamage(int value, Pokemon enemy) {
        enemy.HP -= value;
        if (enemy.HP < 0)
            enemy.HP = 0;
    }
    public void displayPokemonStatus() {
        System.out.print(this.name);
        System.out.printf("--HP: [ %s ](%s/%s)\n", getHpBar(), this.HP, this.maxHP);
    }
    public String getHpBar() {
        int barLength = 10;
        int bar = Math.abs((int) Math.round((double) this.HP / maxHP * barLength));
         return "=".repeat(bar) + " ".repeat(barLength - bar);
    }
    public String getName() {
        return this.name;
    }
    public int getLevel() {
        return levelSystem.currentLevel;
    }
    public Type getType() {
        return type;
    }
    public String getMoveName(int index) {
        if (index == 0)
            return quickMove.getMovesName();
        else
            return mainMove.getMovesName();
    }
    public String getMoveType(int index) {
        ArrayList<String> arr = new ArrayList<>();
        if (index == 0)
            arr.addAll(Arrays.asList(quickMove.getMovesTypeString()));
        else if (index == 1)
            arr.addAll(Arrays.asList(mainMove.getMovesTypeString()));

        return arr.getFirst();
    }
    public void setLevel(String location) {
        this.levelSystem.setDefaultLevel(location, this);
    }
    public int getAttack() {
        return this.attack;
    }
    public int getDefense() { return this.defense; }
    public void gainEXP(Pokemon enemy) {
        this.levelSystem.addEXP(this, enemy.levelSystem.currentLevel * 4);
    }
    public void setMaster(String master) {
        this.master = master;
    }
}
