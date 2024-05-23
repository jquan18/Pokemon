import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityController {
    battleSystem bt;
    Trainer trainer;
    static Stack<City> currentCityStack;
    public CityController(Trainer trainer) {
        this.trainer = trainer;
        currentCityStack = new Stack<>();
    }

    public void runCity(){
        ArrayList<City> city = new ArrayList<>();
        String[] cityList = new String[]{"Pallet Town", "Viridian City", "Pewter City", "Cerulean City", "Vermilion City", "Lavender Town", "Celadon City", "Fuchsia City", "Saffron City", "Cinnabar Island"};
        int[][][] adjacent = new int[][][]{{{1, 5}, {9, 7}}, {{0, 5}, {2, 8}}, {{1, 8}, {3, 12}}, {{2, 12}, {8, 6}, {5, 9}}, {{8, 4}, {5, 5}, {7, 7}}, {{3, 9}, {8, 3}, {4, 5}, {7, 11}}, {{7, 10}, {8, 4}}, {{4, 7}, {6, 10}, {5, 11}, {9, 5}}, {{3, 6}, {5, 3}, {4, 3}, {6, 4}}, {{0, 7}, {7, 5}}};

        //add cities to ArrayList
        for (int i = 0; i < 10; i++) {
            City newCity = new City(cityList[i]); // Create new City object
            newCity.setAdjacent(adjacent[i]); // Set adjacent array
            city.add(newCity);
        }

        //((City) city.get(0)).displayCity();
        currentCityStack.push(city.getFirst());

        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while (!end) {
            currentCityStack.peek().displayOptions( currentCityStack.peek().cityName);
            String currentCity = currentCityStack.peek().cityName;
            String input = sc.nextLine();

            int[] nextCityList = currentCityStack.peek().getAdjacent() ;// get the adjacent list of current city

            switch (input) {
                case "1a": {
                    currentCityStack.pop();
                    currentCityStack.push(city.get(nextCityList[0]));
                    break;
                }
                case "1b":
                    currentCityStack.pop();
                    currentCityStack.push(city.get(nextCityList[1]));
                    break;

                case "1c":
                    if (nextCityList.length < 3) {
                        System.out.println("No such city!");
                    } else {
                        currentCityStack.pop();
                        currentCityStack.push(city.get(nextCityList[2]));
                    }
                    break;
                case "1d":
                    if (nextCityList.length < 3) {
                        System.out.println("No such city!");
                    } else {
                        currentCityStack.pop();
                        currentCityStack.push(city.get(nextCityList[3]));
                    }
                    break;
                case "2":{
                    if (currentCity.equalsIgnoreCase("Pallet Town")){
                        mumNagging();
                    }
                    else if (currentCity.equalsIgnoreCase("Lavender Town")){
                        System.out.println("Welcome to the pokemon maze!");
                        pokemaze();
                    }
                    else {
                        System.out.println("Challenging Gym Leader");
                        if (trainer.trainerBag.badgeList.contains(currentCityStack.peek().cityBadge)) {
                            System.out.println("You have defeated thisG幽默Leader.");
                        }
                        else
                            bt = new battleSystem(this.trainer, currentCityStack.peek().leader);
                    }
                    break;
                }
                case "3":{
                    System.out.println("Fighting Wild Pokemon!");
                    bt =  new battleSystem(this.trainer, currentCityStack.peek().wildPokemon);
                    break;
                }
                case "4a":{
                    System.out.println("Showing Map!");
                    showMap();
                    break;
                }
                case "4b":{
                    System.out.println("Showing Pokemon!");
                     trainer.checkAvailablePokemon(null);
                    break;
                }
                case "4c":{
                    System.out.println("Showing badges");
                    System.out.println("Bag: " + trainer.trainerBag.badgeList);
                    break;
                }
                case "4d":{
                    System.out.println("Saving progress......");
                    end = true;
                    break;
                }
                case "5":{
                    if(currentCity.equalsIgnoreCase("Final")){
                        System.out.println("Run Rivalry Race!");
                    }
                    else if (currentCity.equalsIgnoreCase("Fuchsia City")){
                        System.out.println("Welcome to the Safari Zone!");
                    }
                    else {
                        System.out.println("Invalid move!");
                    }
                    break;
                }
                case "UUDDLRLRBA": {
                    System.out.println("Cheating is nothing to be proud of. - Mark Hunt");
                    trainer.trainerBag.pokemonList.useCheatCode();
                    break;
                }
                default: {
                    System.out.println("Invalid move!");
                    break;
                }
            }
            System.out.println();
        }
    }

    public static void pokemaze(){
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '.', '#', '.', '#', '#', '#', '.', '#', '#', '#', '.', '#'},
                {'#', '.', '.', '.', '#', '.', '#', '.', '#', '.', ' ', '.', '.', '.', '#', '.', '#'},
                {'#', '#', '#', '.', '#', '.', '#', '#', '#', 'G', '#', '.', '#', '.', '#', '#', '#'},
                {'#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', 'G', '#', '.', '#', '.', '#', '.', '#', '#', '#', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'E', '#'}
        };


                // Display initial maze
                displayMaze(maze);

                // Player position
                int playerRow = 1;
                int playerCol = 1;

                // Stack to keep track of the path
                Stack<String> path = new Stack<>();

                char hold = 'S';

                // Game loop
                Scanner scanner = new Scanner(System.in);
                while (maze[playerRow][playerCol] != 'E') {
                    System.out.print("Enter direction (W(up), S(Down), A(left), D(Right)): ");
                    String direction = scanner.nextLine();

                    // Validate input
                    if (isValidDirection(direction)) {
                        // Update player position
                        switch (direction) {
                            case "W":
                                if (maze[playerRow - 1][playerCol] != '#') {
                                    maze[playerRow][playerCol] = hold;
                                    playerRow--;
                                    path.push("W");
                                }
                                else {
                                    maze[playerRow][playerCol] = hold;
                                    System.out.println("You've hit a wall!");
                                }
                                break;
                            case "S":
                                if (maze[playerRow + 1][playerCol] != '#') {
                                    maze[playerRow][playerCol] = hold;
                                    playerRow++;
                                    path.push("S");
                                }
                                else {
                                    maze[playerRow][playerCol] = hold;
                                    System.out.println("You've hit a wall!");
                                }
                                break;
                            case "A":
                                if (maze[playerRow][playerCol - 1] != '#') {
                                    maze[playerRow][playerCol] = hold;
                                    playerCol--;
                                    path.push("A");
                                }
                                else {
                                    maze[playerRow][playerCol] = hold;
                                    System.out.println("You've hit a wall!");
                                }
                                break;
                            case "D":
                                if (maze[playerRow][playerCol + 1] != '#') {
                                    maze[playerRow][playerCol] = hold;
                                    playerCol++;
                                    path.push("D");
                                }
                                else {
                                    maze[playerRow][playerCol] = hold;
                                    System.out.println("You've hit a wall!");
                                }
                                break;
                        }

                        // Check if player touched Ghastly
                        if (maze[playerRow][playerCol] == 'G') {
                            System.out.println("You got caught by Ghastly! Game over.");
                            return;
                        }

                        if(maze[playerRow][playerCol] == 'E'){
                            maze[playerRow][playerCol] = 'Y';
                            displayMaze(maze);
                            break;
                        }

                        // Update maze and display
                        hold = maze[playerRow][playerCol];
                        maze[playerRow][playerCol] = 'Y';
                        displayMaze(maze);
                    } else {
                        System.out.println("Invalid direction! Please enter W, S, A, D only!");
                    }
                }

                // Player reached the end point
                System.out.println("Congratulations! You reached the end point 'E'!");
                System.out.println("Path taken: " + path);
            }
    public static void displayMaze(char[][] maze) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void mumNagging(){
        System.out.println("""
                MOM: "Oh, Amaan! You're leaving on your adventure with Pokémon? How
                exciting! I know you've always dreamed of this day. Remember, the bond
                you share with your Pokémon is the most important thing. Take care of
                them, and they'll take care of you. Don't worry about me; I'll be just
                fine here. I can't wait to hear all about your adventures and the new
                friends you're going to make. Remember, no matter how far you go, I'm
                always here for you. Be brave, be kind, and everything will turn out
                just fine. I'm so proud of you already! Now, go on, your adventure
                awaits! Oh, and don’t forget to change your underwear every day! Safe
                travels, my dear!"
                """);
    }
    public static void showMap() {
        String redColorCode = "\u001B[31m";
        String resetColorCode = "\u001B[0m";

        String[] patterns = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Celadon City", "Saffron City", "Cerulean City", "Lavender Town", "Vermillion City", "Fuchsia City"};
        for (int i=0; i<patterns.length; i++) {
            if (currentCityStack.peek().cityName.equalsIgnoreCase(patterns[i])) {
                Pattern pattern = Pattern.compile(patterns[i]);
                try {
                    Scanner reader = new Scanner(new FileInputStream("src/res/Map.txt"));
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            StringBuffer sb = new StringBuffer();
                            matcher.appendReplacement(sb, redColorCode + patterns[i] + resetColorCode);
                            matcher.appendTail(sb);
                            System.out.println(sb);
                        }
                        else
                            System.out.println(line);

                    }
                    reader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static boolean isValidDirection(String direction) {
        return direction.equals("w") || direction.equals("S") || direction.equals("A") || direction.equals("D");
    }


}
