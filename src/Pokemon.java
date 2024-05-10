import java.util.ArrayList;
/*
QM = quick move (low damage, but more change )
MM = Main move (High damage, but less change)
 */
public class Pokemon{
    private int maxHP, HP, level;
    private String name;
    private Type type, QMType, MMType;
    private ArrayList<Moves> moves = new ArrayList<>();
    public Pokemon() {
        this(null, null, 0, 0, null, null, 0, null, null, 0 );
    }
    public Pokemon(String name, String[] type, int HP, int level, String QMName,String QMType, int QMDamage, String MMName, String MMType, int MMDamage) {
        this.name = name;
        this.type = new Type(type);
        this.maxHP = HP;
        this.HP = HP;
        this.level = level;
        this.moves.add(new Moves(QMName, QMType, QMDamage));
        this.moves.add(new Moves(MMName, MMType, MMDamage));
    }
    public int useQuickMove(Pokemon enemy) {
        moves.getFirst().QMPoint -= 1;
        System.out.printf("%s used %s", this.name, this.moves.getFirst().getMovesName());
        //Check the problem here
        double value = moves.getFirst().getDPR() * moves.getFirst().getMovesType().moveCounter(moves.getFirst().getMovesTypeAString(), new String[]{enemy.getType().toString()} );
        return (int) value;
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
