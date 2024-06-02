import java.util.*;

public class SafariZone {
    ArrayList<String> pokemonList;
    int size;
    boolean eevee = false;
    boolean jigglypuff = false;
    boolean pikachu = false;
    boolean charmander = false;
    boolean snorlax = false;
    boolean machop = false;
    boolean bulbasaur = false;
    public SafariZone() {
        pokemonList = new ArrayList<>();
    }


    public void playSafariZone(){
        boolean proceed = false;

        //reprompt them to enter again
        while(!proceed) {
            System.out.println("+--------------------------------------------+");
            System.out.println("Welcome to the Safari Zone! Today's challenge: Sort the Pokemon!");
            System.out.println("+--------------------------------------------+");
            System.out.println("Enter the Pokemon in your party (seperated by a comma)(List of Pokemon:[Pikachu, Bulbasaur, Charmander, Snorlax, Jigglypuff, Eevee, Machop] ");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            //splitting into array and
            input = input.replace(" ", "");
            String[] list = input.split(",");

            //transfer from array to ArrayList
            for (int i = 0; i < list.length; i++) {
                pokemonList.addLast(list[i]);
            }
            size = pokemonList.size();

            System.out.print("You entered: ");
            printList(pokemonList);

            fillBoolean();

            //check if there is invalid case
            if (checkInvalid()) {
                System.out.println("Invalid conmbinations!");
                proceed = false;
                pokemonList.clear();
                resetBoolean();
            }
            else {
                proceed = true;
            }
        }

        //proceeds to sorting algorithm
        //sorting eevee
        if(eevee) {
            moveEevee();
        }

        //sorting snorlax
        if (snorlax){
            moveSnorlax();
        }

        //sorting machop
        if (machop){
            moveMachop();
        }

        //sorting bulbasaur
        if (bulbasaur && charmander && checkBulbasaur()){
            moveBulbasaur();
        }

        //sorting Pikachu
        if (pikachu && !isPikachuMiddle()){
            movePikachu();
        }

        //sorting jigglypuff

        if (jigglypuff && !PikaBesideJiggly()){
            moveJigglypuff();
        }

        if (bulbasaur && charmander && checkBulbasaur()){
            moveBulbasaur();
        }

        if (pikachu && !isPikachuMiddle()){
            movePikachu();
        }

        System.out.println("Final sorted list: ");
        printList(pokemonList);
    }

    public boolean isPikachuMiddle(){
        int pikachuPosition = pokemonList.indexOf((String) "Pikachu");
        int middle = size/2;

        if (pikachuPosition == middle){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean PikaBesideJiggly(){
        int pikachuPosition = pokemonList.indexOf((String) "Pikachu");
        int jigglypuffPosition = pokemonList.indexOf((String) "Jigglypuff");

        if (pikachuPosition - jigglypuffPosition == 1 || pikachuPosition - jigglypuffPosition == -1){
            return true;
        }
        else{
            return false;
        }
    }
    public void moveJigglypuff(){
        System.out.println("Jigglypuff prefers to be surrounded by other \"cute\" Pokémon for morale purposes.");

        int pikachuPosition = pokemonList.indexOf((String) "Pikachu");
        System.out.println("Pikachu Position = " + pikachuPosition);
        int jigglypuffPosition = pokemonList.indexOf((String) "Jigglypuff");
        String remove = pokemonList.remove(jigglypuffPosition);
        pokemonList.add(pikachuPosition, remove);

        System.out.println("Partial Sort: ");
        printList(pokemonList);

    }

    public void movePikachu(){
    System.out.println("Pikachu demands to be placed at the center of the arrangement because, well, it's Pikachu!");

        int centre = size/2;
        int pikachuPosition = pokemonList.indexOf((String) "Pikachu");
        String remove = pokemonList.remove(pikachuPosition);
        pokemonList.add(centre, remove);

        System.out.println("Partial Sort: ");
        printList(pokemonList);

    }
    public void moveEevee(){
        System.out.println("Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability");

        int eeveePosition = pokemonList.indexOf((String) "Eevee");
        String remove = pokemonList.remove(eeveePosition);
        pokemonList.addFirst(remove);

        System.out.println("Partial Sort: ");
        printList(pokemonList);
    }

    public void moveSnorlax(){
        System.out.println("Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation.");

        int snorlaxPosition = pokemonList.indexOf((String) "Snorlax");
        String remove = pokemonList.remove(snorlaxPosition);
        pokemonList.addLast(remove);

        System.out.println("Partial Sort: ");
        printList(pokemonList);
    }

    //check if bulbasaur is next to Charmander
    public boolean checkBulbasaur(){
        int bulbasaurPosition = pokemonList.indexOf((String) "Bulbasaur");
        int charmanderPosition = pokemonList.indexOf((String) "Charmander");

        if (bulbasaurPosition - charmanderPosition ==1 || bulbasaurPosition - charmanderPosition ==-1){
            return true;
        } else {
            return false;
        }
    }

    public void moveBulbasaur(){
        System.out.println("Bulbasaur refuses to be placed next to Charmander, his fire burns too hot");

        int charmanderIndex = pokemonList.indexOf("Charmander");
        if((charmanderIndex < (size/2)+1)){
            if(machop){
                int machopPosition = pokemonList.indexOf((String) "Machop");
                int bulbasaurPosition = pokemonList.indexOf("Bulbasaur");
                String remove = pokemonList.remove(bulbasaurPosition);
                pokemonList.add(machopPosition-1,remove);
            }else if (snorlax){
                int snorlaxPosition = pokemonList.indexOf((String) "Machop");
                int bulbasaurPosition = pokemonList.indexOf("Bulbasaur");
                String remove = pokemonList.remove(bulbasaurPosition);
                pokemonList.add(snorlaxPosition,remove);
            }else {
                int bulbasaurPosition = pokemonList.indexOf("Bulbasaur");
                String remove = pokemonList.remove(bulbasaurPosition);
                pokemonList.addLast(remove);
            }
        } else {
            if (eevee) {
                int eeveePosition = pokemonList.indexOf((String) "Eevee");
                int bulbasaurPosition = pokemonList.indexOf("Bulbasaur");
                String remove = pokemonList.remove(bulbasaurPosition);
                pokemonList.add(eeveePosition + 1, remove);
            } else {
                int bulbasaurPosition = pokemonList.indexOf("Bulbasaur");
                String remove = pokemonList.remove(bulbasaurPosition);
                pokemonList.addFirst(remove);
            }
        }

        System.out.println("Partial Sort: ");
        printList(pokemonList);
    }

    public void moveMachop(){
        System.out.println("Machop demands to be placed next to the heaviest Pokemon in the lineup to show off its strength. ");

        if (snorlax) {
            int machopPosition = pokemonList.indexOf((String) "Machop");
            int snorlaxPosition = pokemonList.indexOf((String) "Snorlax");
            String remove = pokemonList.remove(machopPosition);
            pokemonList.add(snorlaxPosition - 1, remove);
        }
        else{
            int machopPosition = pokemonList.indexOf((String) "Machop");
            String remove = pokemonList.remove(machopPosition);
            pokemonList.addLast(remove);
        }

        System.out.println("Partial Sort: ");
        printList(pokemonList);
    }

    public void printList(ArrayList<String> sortList){
        for (int i = 0; i < sortList.size(); i++) {
            System.out.print(sortList.get(i));
            if(i < sortList.size() -1){
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }

    public void fillBoolean(){
        for (int i = 0; i < pokemonList.size(); i++) {
            switch(pokemonList.get(i)){
                case "Eevee":
                    eevee = true;
                    break;
                case "Bulbasaur":
                    bulbasaur = true;
                    break;
                case "Pikachu":
                    pikachu = true;
                    break;
                case "Snorlax":
                    snorlax = true;
                    break;
                case "Machop":
                    machop = true;
                    break;
                case "Jigglypuff":
                    jigglypuff = true;
                    break;
                case "Charmander":
                    charmander = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void resetBoolean(){
        jigglypuff = false;
        pikachu = false;
        charmander = false;
        eevee = false;
        snorlax = false;
        bulbasaur = false;
        machop = false;
    }

    public boolean checkInvalid(){
        if(size ==2 && bulbasaur && charmander){
            return true;
        }
        else if ((size ==3 && bulbasaur && charmander && snorlax)){
            return true;
        }
        else if ((size ==3 && bulbasaur && charmander && eevee )){
            return true;
        }
        else if (size ==4 && bulbasaur && charmander && eevee && snorlax){
            return true;
        }
        else if (size ==4 && bulbasaur && charmander && snorlax && machop){
            return true;
        }
        else if (size ==5 && bulbasaur && charmander && snorlax && machop && eevee){
            return true;
        }else {
            return false;
        }

    }
    public static void main(String[] args) {
        SafariZone hi = new SafariZone();
        hi.playSafariZone();
    }
}
