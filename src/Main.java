import java.util.Scanner;

public class Main {
    public static void main(String[] args){

//        Script.prologue();
        String name = Script.getTrainerName();
        Trainer trainer = new Trainer(name);

        Pokemon partner = Script.choosePartner();
        trainer.choosePartnerPokemon(partner);

//        Script.continueScript2();

        CityController controlPanel = new CityController(trainer);
        controlPanel.runCity();



    }
}