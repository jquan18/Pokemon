import java.util.*;

public class CityController {
    public CityController() {
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

        Stack<Object> currentCityStack = new Stack<>();
        currentCityStack.push(city.getFirst());

        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while (!end) {
            ((City) currentCityStack.peek()).displayOptions(((City) currentCityStack.peek()).cityName);
            String currentCity = ((City) currentCityStack.peek()).cityName;
            String input = sc.nextLine();

            int[] nextCityList = ((City) currentCityStack.peek()).getAdjacent() ;// get the adjacent list of current city

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
                    }
                    break;
                }
                case "3":{ //Wild Pokemon
                    System.out.println("Fighting Wild Pokemon!");
                    //Search Pokemon Based on city
                    break;
                }
                case "4a":{
                    System.out.println("Showing Map!");
                    break;
                }
                case "4b":{
                    System.out.println("Showing Pokemon!");
                    break;
                }
                case "4c":{
                    System.out.println("Showing badges");
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
        System.out.println("MOM: \"Oh, Amaan! You're leaving on your adventure with Pokémon? How\n" +
                "exciting! I know you've always dreamed of this day. Remember, the bond\n" +
                "you share with your Pokémon is the most important thing. Take care of\n" +
                "them, and they'll take care of you. Don't worry about me; I'll be just\n" +
                "fine here. I can't wait to hear all about your adventures and the new\n" +
                "friends you're going to make. Remember, no matter how far you go, I'm\n" +
                "always here for you. Be brave, be kind, and everything will turn out\n" +
                "just fine. I'm so proud of you already! Now, go on, your adventure\n" +
                "awaits! Oh, and don’t forget to change your underwear every day! Safe\n" +
                "travels, my dear!\"\n");
    }

    public static boolean isValidDirection(String direction) {
        return direction.equals("w") || direction.equals("S") || direction.equals("A") || direction.equals("D");
    }


}
