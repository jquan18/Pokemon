public class battleSystemTester {
    public static void main(String[] args) {
        Trainer trainer = new Trainer();
        GymLeader leader = new GymLeader("Brock");
        battleSystem bt = new battleSystem(trainer, leader);
        bt.startBattle();

    }
}
