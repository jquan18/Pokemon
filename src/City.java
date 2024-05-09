import java.util.*;

public class City {
    public static String[] cityList;
    public String cityName;
    public int[][] adjacent;


    public City(String name) {
        cityList = new String[]{"Pallet Town", "Viridian City", "Pewter City", "Cerulean City", "Vermilion City", "Lavender Town", "Celadon City", "Fuchsia City", "Saffron City", "Cinnabar Island"};
        this.cityName = name;
    }

    public void setAdjacent(int[][] adjacent) {
        this.adjacent = adjacent;
    }

    public int[] getAdjacent() {
        int[] localAdjacent = new int[adjacent.length];

        for (int i = 0; i < adjacent.length; i++) {
            localAdjacent[i] = adjacent[i][0];
        }

        return localAdjacent;
    }

    public String[] NumToCity(int[] localAdjacent) {
        String[] convertedCityName = new String[localAdjacent.length];

        for (int i = 0; i < localAdjacent.length; i++) {
            int j = localAdjacent[i];
            convertedCityName[i] = cityList[j];
        }
        return convertedCityName;
    }

    public void displayOptions(String currentCity) {
        //choice 1
        int[] localAdjacent = getAdjacent();
        String[] alphabet = {"a", "b", "c", "d"};
        String[] convertedCityName = NumToCity(localAdjacent);

        System.out.println("+--------------------------------------------+");
        System.out.println("You are currently in " + this.cityName);
        System.out.println("+--------------------------------------------+");
        System.out.println("[1] Move to: ");

        System.out.print("    ");
        for (int i = 0; i < localAdjacent.length; i++) {
            System.out.print(alphabet[i] + "." + convertedCityName[i] + "   ");
        }
        System.out.println();

        //choice 2
        switch(currentCity){
            case "Pallet Town":
                System.out.println("[2] Talk to Mom [Your hometown has no Gym]");
                System.out.println("[3] Fight Wild Pokemon");
                System.out.println("[4] Player Options");
                System.out.println("    a.Show map   b.Show My Pokemon   c.Show My Badges   d.Save and Exit");
                System.out.println("+--------------------------------------------+");
                System.out.println("Your choice: ");
                break;
            case "Fuchsia City":
                System.out.println("[2] Challenge Gym Leader");
                System.out.println("[3] Fight Wild Pokemon");
                System.out.println("[4] Player Options");
                System.out.println("    a.Show map   b.Show My Pokemon   c.Show My Badges   d.Save and Exit");
                System.out.println("[5] Safari Zone");
                System.out.println("+--------------------------------------------+");
                System.out.println("Your choice: ");
                break;
            case "Lavender Town":
                System.out.println("[2] Fight Wild Pokemon");
                System.out.println("[3] PokeMaze");
                System.out.println("[4] Player Options");
                System.out.println("    a.Show map   b.Show My Pokemon   c.Show My Badges   d.Save and Exit");
                System.out.println("+--------------------------------------------+");
                System.out.println("Your choice: ");
                break;
            case "Final":
                System.out.println("[2] Challenge Gym Leader");
                System.out.println("[3] Fight Wild Pokemon");
                System.out.println("[4] Player Options");
                System.out.println("    a.Show map   b.Show My Pokemon   c.Show My Badges   d.Save and Exit");
                System.out.println("[5] Rival's Race");
                System.out.println("+--------------------------------------------+");
                System.out.println("Your choice: ");
            default:
                System.out.println("[2] Challenge Gym Leader");
                System.out.println("[3] Fight Wild Pokemon");
                System.out.println("[4] Player Options");
                System.out.println("    a.Show map   b.Show My Pokemon   c.Show My Badges   d.Save and Exit");
                System.out.println("+--------------------------------------------+");
                System.out.println("Your choice: ");
                break;
        }
    }

    public int findCityIndex(Object City){
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].equals(City)){
                return i;
            }
        }
        return -1;
    }
}