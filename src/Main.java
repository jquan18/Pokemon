import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        CityController pokemon = new CityController();
//        pokemon.runCity();
        Script.prologue();
        String name = Script.getTrainerName();
        Trainer trainer = new Trainer(name);



    }
}