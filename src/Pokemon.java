import java.util.ArrayList;

public class Pokemon{
    private int maxHP, HP, level;
    private String name;
    private Type type;
    private ArrayList<Moves> moves = new ArrayList<>();
    public Pokemon() {
        this(null, null, 0, 0, null, 0, null, 0 );
    }
    public Pokemon(String name, String[] type, int HP, int level, String QMName, int QMDamage, String MMName, int MMDamage) {
        this.name = name;
        this.type = new Type(type);
        this.maxHP = HP;
        this.HP = HP;
        this.level = level;
        this.moves.add(new Moves(QMName, QMDamage));
        this.moves.add(new Moves(MMName, MMDamage));
    }

    public int getHP() {
        return HP;
    }
    public int getMaxHP() {
        return maxHP;}
    public int getLevel() {
        return level;
    }
    public Type getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String printActionInfo(int index) {
        return getName() + " use " + moves.get(index).getMovesName() + " and dealt " + moves.get(index).getDPR() + "!";
    }
}
