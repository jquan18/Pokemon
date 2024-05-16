public class Attack {
    private String moveName;
    private int damage;

    public Attack(String moveName, int damage) {
        this.moveName = moveName;
        this.damage = damage;
    }

    public String getMoveName() {
        return moveName;
    }

    public int getDamage() {
        return damage;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "[" + moveName + "/" + damage + "]";
    }
}
