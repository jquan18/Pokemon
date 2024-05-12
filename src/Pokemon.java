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
    private QuickMove quickMove;
    private MainMove mainMove;
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
        this.mainMove = new MainMove((MMName, MMType, MMDamage);
    }
    public void useQuickMove(Pokemon enemy) {
        quickMove.QMPoint -= 1;
        System.out.printf("%s used %s", this.name, this.quickMove.getMovesName());
        //Check the problem here
        int value = (int)(quickMove.getDPR() * quickMove.getMovesType().moveCounter(quickMove.getMovesTypeString(), new String[]{enemy.getType().toString()} ));
//        System.out.printf("%s's HP drops %d points!", enemy.getName(), value);
        receiveDamage(value, enemy);
    }

    public void receiveDamage(int value, Pokemon enemy) {
        enemy.HP -= value;
    }
    public void displayPokemonStatus() {
        System.out.println(getName());
        System.out.printf("--HP: [ %s ](%s/%s)", getHpBar(), this.HP, this.maxHP);
    }
    public String getHpBar() {
        int barLength = 10;
        int bar = (int) Math.round((double) this.HP / maxHP * barLength);
        return "[" + "=".repeat(bar) + " ".repeat(barLength - bar);
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
