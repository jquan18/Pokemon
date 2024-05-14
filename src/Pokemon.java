import java.util.ArrayList;
import java.util.Arrays;

/*
QM = quick move (low damage, but more change )
MM = Main move (High damage, but less change)
 */
public class Pokemon{
    int maxHP, HP, level;
    private String name;
    private Type type, QMType, MMType;
//    private ArrayList<Moves> moves = new ArrayList<>();
    final QuickMove quickMove;
    final MainMove mainMove;

    public Pokemon() {
        this(null, null, 0, 0, null, null, 0, null, null, 0 );
    }
    public Pokemon(String name, String[] type, int HP, int level, String QMName,String QMType, int QMDamage, String MMName, String MMType, int MMDamage) {
        this.name = name;
        this.type = new Type(type);
        this.maxHP = HP;
        this.HP = HP;
        this.level = level;
        this.quickMove = new QuickMove(QMName, QMType, QMDamage);
        this.mainMove = new MainMove(MMName, MMType, MMDamage);
    }
    public void useMove(int index, Pokemon enemy) {
        int value = 0;
        switch (index) {
            case 1:
                quickMove.QMPoint -= 1;
                System.out.printf("%s used %s\n", this.name, this.quickMove.getMovesName());
                //Check toString()
                value = (int)(quickMove.getDPR() * quickMove.getMovesType().moveCounter(quickMove.getMovesTypeString(), new String[]{enemy.getType().toString()} ));
                break;
            case 2:
                mainMove.MMPoint -= 1;
                System.out.printf("%s used %s", this.name, this.mainMove.getMovesName());
                value = (int) (mainMove.getDPR() * mainMove.getMovesType().moveCounter(mainMove.getMovesTypeString(), new String[]{enemy.getType().toString()}));
                break;

        }
        receiveDamage(value, enemy);
//        System.out.printf("%s's HP drops %d points!", enemy.getName(), value);
    }

    public void receiveDamage(int value, Pokemon enemy) {
        enemy.HP -= value;
        if (enemy.HP < 0)
            enemy.HP = 0;
    }
    public void displayPokemonStatus() {
        System.out.println(getName());
        System.out.printf("--HP: [ %s ](%s/%s)\n", getHpBar(), this.HP, this.maxHP);
    }
    public String getHpBar() {
        int barLength = 10;
        int bar = Math.abs((int) Math.round((double) this.HP / maxHP * barLength));
         return "=".repeat(bar) + " ".repeat(barLength - bar);
    }
    public int getLevel() {
        return level;
    }
    public Type getType() {
        return type;
    }
    public String getName() {
        return name;
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
}
