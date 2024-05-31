import java.util.Scanner;

@SuppressWarnings("ALL")
public class Script {
    static Scanner sc = new Scanner(System.in);
    static String name;
    static String greenColorCode = "\u001B[32m";
    static String redColorCode = "\u001B[31m";
    static String blueColorCode = "\u001B[34m";
    static String resetColorCode = "\u001B[0m";
    static HandingAbnormalInput inputChecker = new HandingAbnormalInput();

    public static void prologue() {
        String s1 = """
                BRICH   : Hi! Sorry to keep you waiting!
                BRICH   : Welcome to the world of POKEMON!
                BRICH   : My name is BRICH! But everyone calls me the PPKEMON PROG!\n
                """;
        typeWriterEffect1(s1);

        String s2 = """
                BRICH  : This world is inhabited by creatures known as POKEMON! We
                         humans live alongside POKEMON. at times as friendly playmates,
                         and at times as cooperative workmates\n\n"""
                ;
        typeWriterEffect1(s2);

        String s3 = """
                BRICH  : And sometimes, we band together and battle others like us.
                BRICH  : But despite our closeness, we don't know everything about POKEMON.
                BRICH  : In fact, there are many, many secret surrouding POKEMON. To unravel
                         POKEMON mysteries, I've been undertaking research. That's what I do."""
                ;
        typeWriterEffect1(s3);
    }

    public static String getTrainerName() {
        while (true) {
            String s = "BRICH  : First, what is your name? \n";
            typeWriterEffect2(s);
            name = sc.next();

            String s2 = "\nAre you sure? (Y/N)\n";
            typeWriterEffect2(s2);
            String response = sc.next();
            if (response.equalsIgnoreCase("Y"))
                break;
            else if (response.equalsIgnoreCase("N"))
                ;
            else {
                String ss = "Developer: I know you want to try an anomalous input.\n";
                typeWriterEffect2(ss);
            }
        }
        return name;
    }

    public static void continueScript() {
        String s1 =
                "BRICH   : Right! So your're " + name + "who's moving to my hometown of LITTLEROOT.\n" + " I get it now!\n";
        typeWriterEffect2(s1);

        String s2 =
                "BRICH   : All right, are you ready?\n";
        typeWriterEffect1(s2);
    }
    public static Pokemon choosePartner() {
        String s1 = "BRICH  : You can choose one of the POKEMON over there. \n\n" +
                "1. " + greenColorCode + "Bulbasaur" + resetColorCode + ", the Grass-type POKEMON!\n" +
                "2. " + redColorCode + "Charmander" + resetColorCode + ", the Fire-type POKEMON!\n" +
                "3. " + blueColorCode + "Squirtle" + resetColorCode + ", the Water-type POKEMON!\n" +
                "\nEnter your choice: ";
        typeWriterEffect2(s1);
        while (true) {
            String sel = sc.next();
            if (inputChecker.checkAbnormalInput(sel, "1", "3")) {
                String s2 = "Are you sure? (Y/N)\n";
                typeWriterEffect2(s2);
                String response = sc.next();
                if (response.equalsIgnoreCase("Y")) {
                    switch (sel) {
                        case "1": {
                            return new Pokemon("Bulbasaur", new String[]{"Grass", "Poison"}, 45, 49, 49, 45, "Vine Whip", "Grass", 100, "Venoshock", "Poison", 60);
                        }
                        case "2": {
                            return new Pokemon("Charmander", new String[]{"Fire"},39, 52, 43, 65, "Scratch", "Fire Fang", 40, "Ember", "Fire", 65);
                        }
                        case "3": {
                            return new Pokemon("Squirtle", new String[]{"Water"}, 44, 48, 65, 43, "Tackle", "Normal", 40, "Water Pulse", "Water", 65);
                        }
                    }
                }
                else if (response.equalsIgnoreCase("N"))
                    ;
                else {
                    String ss = "Developer: I know you want to try an anomalous input.\n";
                    typeWriterEffect2(ss);
                }
            }
            else {
                String ss = "Developer: I know you want to try an anomalous input.\n";
                typeWriterEffect2(ss);
            }
        }
    }
    public static void continueScript2() {
        String s1 =
                "BRICH  : Your very own adventure is about to unfold.\n\n";
        typeWriterEffect1(s1);

        String s2 = """
                 BRICH  : Take courage, and leap into the world of POKEMON where
                          dreams, adventure, and friendships await!\n\n""";
        typeWriterEffect1(s2);
    }
    public static void healthCareScript(){
        String s1 = """
                Nurse Joy   : Welcome to our POKEMON CENTER!\n
                Nurse Joy   : We can heal your POKEMON to perfect health.\n
                Nurse Joy   : Okay, I'll take your POKEMON for a few seconds.\n
                .
                ..
                ...
                ....!
                Nurse Joy   : Thank you for waiting. We hope to see you again!
                """;
        typeWriterEffect2(s1);
    }
    public static void GaryDescription() {
        String s1 = """
                In the heart of Saffron City, an epic rivarly unfolds as Gary,
                your lifelong rival, the day he started out on his Pokémon journey, 
                he came off as an arrogant Trainer. He want to challenges you to race. 
                The stakes are high, and victory is within reach! 
                .
                ..
                ....
                For now, are you get ready to challenges your lifelong rival?
                """;
        typeWriterEffect2(s1);
    }
	
    public static void typeWriterEffect1(String str ) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[ Press ENTER to continue...]");
        sc.nextLine();
    }

    public static void typeWriterEffect2(String str ) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
