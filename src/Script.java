import java.util.Scanner;

public class Script {
    static Scanner sc = new Scanner(System.in);
    static String name;
    public  static void prologue() {
        System.out.println("""
                Oak       : Hello there! Welcome to the world of POKEMON! My name is OAK!
                            People call me the POKEMON PROF!
                """);
        System.out.println("[ Press Any Key tp continue...]");
        String str = sc.next();

        System.out.println("""
                Oak       : This world is inhabited by creatures called POKEMON! For some
                            people, POKEMON are pets. Others use them for fights. Myself...
                            I study POKEMON as a profession.
                """);
        System.out.println("[ Press Any Key tp continue...]");
        str = sc.next();
    }
    public static String getTrainerName() {
        while (true) {
            System.out.println("""
                Oak       : First, what is your name?
                """);
            name = sc.next();

            System.out.println("Are you sure? (Y/N)");
            String response = sc.next();
            if (response.equalsIgnoreCase("Y"))
                break;
            else if (response.equalsIgnoreCase("N"))
                ;
            else
                System.out.println("Invalid Input");
        }
        return name;
    }

    public static void continueScript() {
        System.out.print("""
               Oak       : Right! So your name is """);
        System.out.println(name +"!");

    }
}
