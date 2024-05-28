import java.util.*;

public class City {
    public static String[] cityList = {"Pallet Town", "Viridian City", "Pewter City", "Cerulean City", "Vermilion City", "Lavender Town", "Celadon City", "Fuchsia City", "Saffron City", "Cinnabar Island"};
    public static String[] leaderList = {"", "Giovanni", "Brock", "Misty", "Lt. Surge", "", "Erika", "Koga", "Sabrina", "Blaine"};
    public static String[] badge = {"", "Earth Badge", "Boulder Badge", "Cascade Badge", "Thunder Badge", "", "Rainbow Badge", "Soul Badge", "Marsh Badge", "Volcano Badge"};
    public String cityName;
    public int[][] adjacent;
    Wild_Pokemon wildPokemon;
    GymLeader leader;
    String cityBadge;


    public City(String name) {

        this.cityName = name;

        for (int i=0; i<cityList.length; i++) {
            if (this.cityName.equalsIgnoreCase(cityList[i]) && !leaderList[i].isEmpty()) {
                leader = new GymLeader(leaderList[i]);
                this.cityBadge = badge[i];
            }
        }

        wildPokemon = new Wild_Pokemon(this.cityName);


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
                displayDefaultOptions();
                break;
            case "Fuchsia City":
                System.out.println("[2] Challenge Gym Leader");
                displayDefaultOptions();
                System.out.println("[6] Safari Zone");
                break;
            case "Lavender Town":
                System.out.println("[2] PokeMaze");
                displayDefaultOptions();
                break;
            case "Final":
                System.out.println("[2] Challenge Gym Leader");
                displayDefaultOptions();
                System.out.println("[6] Rival's Race");
                break;
            default:
                System.out.println("[2] Challenge Gym Leader");
                displayDefaultOptions();
        }
        System.out.println("+--------------------------------------------+");
        System.out.println("Your choice: ");
    }
    public void displayDefaultOptions() {
        System.out.print("[3] Fight Wild Pokemon ");
        wildPokemon.showPokemon();
        System.out.println("[4] Player Options");
        System.out.println("    a.Show map   b.Show My Pokemon   c.Show My Badges   d.Save and Exit");
        System.out.println("[5] HealthCare");
    }

    public int findCityIndex(String City){
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].equals(City)){
                return i;
            }
        }
        return -1;
    }

    public String convertString(City City){
        for (int i = 0; i < cityList.length; i++) {
            if (cityList[i].equals(City.cityName)){
                return cityList[i];
            }
        }
        return "no such city";
    }

    public String numToString(int index) {
        return cityList[index];
    }

}