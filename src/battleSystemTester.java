public class battleSystemTester {
    public static void main(String[] args) {
//        Trainer trainer = new Trainer();
//        GymLeader leader = new GymLeader("Brock");
//        battleSystem bt = new battleSystem(trainer, leader);

        try {
            String s = "\033[0;30mThe year is \033[0;97m\u001B[1m2024 AC\033[0m\033[0;30m(After \033[0;31mCatacylsm\033[0;30m).\r\n"
                    + //
                    "Ever since \033[0;31mthe Catacylsm\033[0;30m tore the planet asunder, \033[0;32mthe Earth\033[0;30m has revealed what hid underground: countless mazes and labyrinths, all interconnected with each other with no end in sight.\r\n"
                    + //
                    "These mazes are filled with many different kinds of monsters, all with a taste for humans.\r\n" + //
                    "They had never once left the mazes, but \033[0;31mthe Cataclysm\033[0;30m gave way to multiple holes that lead to the civilization on the surface.\r\n"
                    + //
                    "One by one, these monsters came out from their dark undergound hallways and waged war on humans.\r\n"
                    + //
                    "But monsters weren't the only things to come out of the mazes.\r\n" + //
                    "Magic flowed out from these mystic paths, giving birth to a different breed of humans: \033[1;93m\033[0;100mthe Archetypes.\033[0m\033[0;30m\r\n"
                    + //
                    "So strong were \033[1;97mthe Archetypes\033[0;30m that not only did they defend themselves from the monsters, they were able to venture within the mysterious mazes and create underground human colonies. \r\n"
                    + //
                    "However, 1 part of the maze has never been touched before: \033[1;91m\u001B[4mFOP Valley\033[0m\033[0;30m, the centre of \033[0;32mthe Earth\033[0;30m.\r\n"
                    + //
                    "Strong and primal magic flowed from within, as if \033[0;31mthe Earth\033[0;30m was ready to give its secrets to anyone who dared to come, yet every party that ventured to \033[1;91m\u001B[4mFOP Valley\033[0m\033[0;30m would never be seen or heard from again.\r\n"
                    + //
                    "In the year \033[0;97m\u001B[1m2019 AC\033[0m\033[0;30m, the leader of \033[1;97mthe Archetypes\033[0;30m gathered every single \033[1;97mArchetype\033[0;30m human in the world in a final quest to explore the centre of \033[0;32mthe Earth\033[0;30m.\r\n"
                    + //
                    "They would all disappear without a trace. \r\n" + //
                    "But one day, you wake up in the middle of these dark halls.\r\n" + //
                    "You don't remember who you are or how you got there, but you know that you are within the fabled halls of the \033[1;91m\u001B[4mFOP Valley\033[0m\033[0;30m.\r\n"
                    + //
                    "You can feel that whatever caused \033[0;31mthe Cataclysm\033[0;30m is offering you an opportunity, not just to escape, but to know the truth.\r\n"
                    + //
                    "The question is, are you ready?";
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                System.out.print(c[i]);
                Thread.sleep(20);
            }
        } catch (Exception a) {
            a.printStackTrace();
        }

    }
}
