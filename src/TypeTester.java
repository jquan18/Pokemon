import java.sql.SQLOutput;

public class TypeTester {
    public static void main(String[] args) {
        String[] trainer = {"Electric", "Dragon"};
        String[] enemy1 = {"Dragon"}; // 0.5*2 = 1
        String[] enemy2 = {"Rock", "Ghost"};  // 1*1*1*1= 1
        String[] enemy3 = {"Poison", "Steel"}; // 1*1*0.5*1 = 0.5
        String[] enemy4 = {"Water", "Fairy"}; // 2*1*1*0 = 0

        Type typeTrainer = new Type(trainer);
        Type typeE1 = new Type(enemy1);
        Type typeE2 = new Type(enemy2);
        Type typeE3 = new Type(enemy3);
        Type typeE4 = new Type(enemy4);
        System.out.println(typeTrainer.counter(enemy3));
    }
}
