import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityController {
    private battleSystem bt;
    private Trainer trainer;
    private SaveGame saveGame;
    private String currentLocation;
    static ArrayList<City> city;
    static Stack<City> currentCityStack;
    static Stack<City> DJKCityStack;

    public CityController() {

    }

    public CityController(Trainer trainer) {
        this.trainer = trainer;
        currentCityStack = new Stack<>();
		this.currentLocation = "Pallet Town";
    }


    public CityController(Trainer trainer, SaveGame saveGame, String currentLocation) {
        this.trainer = trainer;
        this.saveGame = saveGame;
        this.currentLocation = currentLocation;
        currentCityStack = new Stack<>();
    }

    public void runCity(){
        currentCityStack = new Stack<>();
        city = new ArrayList<>();
        String[] cityList = new String[]{"Pallet Town", "Viridian City", "Pewter City", "Cerulean City", "Vermilion City", "Lavender Town", "Celadon City", "Fuchsia City", "Saffron City", "Cinnabar Island"};
        int[][][] adjacent = new int[][][]{{{1, 5}, {9, 7}}, {{0, 5}, {2, 8}}, {{1, 8}, {3, 12}}, {{2, 12}, {8, 6}, {5, 9}}, {{8, 4}, {5, 5}, {7, 7}}, {{3, 9}, {8, 3}, {4, 5}, {7, 11}}, {{7, 10}, {8, 4}}, {{4, 7}, {6, 10}, {5, 11}, {9, 5}}, {{3, 6}, {5, 3}, {4, 3}, {6, 4}}, {{0, 7}, {7, 5}}};

        //add cities to ArrayList
        for (int i = 0; i < 10; i++) {
            City newCity = new City(cityList[i]); // Create new City object
            newCity.setAdjacent(adjacent[i]); // Set adjacent array
            city.add(newCity);
        }

        // Set initial city
        for (City c : city) {
            if (c.cityName.equalsIgnoreCase(currentLocation)) {
                currentCityStack.push(c);
                break;
            }
        }

        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while (!end) {
            currentCityStack.peek().displayOptions(currentCityStack.peek().cityName);
            String currentCity = currentCityStack.peek().cityName;
            String input = sc.nextLine();

            int[] nextCityList = currentCityStack.peek().getAdjacent(); // get the adjacent list of current city

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
                    if (nextCityList.length < 4) {
                        System.out.println("No such city!");
                    } else {
                        currentCityStack.pop();
                        currentCityStack.push(city.get(nextCityList[3]));
                    }
                    break;
                case "2": {
                    if (currentCity.equalsIgnoreCase("Pallet Town")) {
                        mumNagging();
                    } else if (currentCity.equalsIgnoreCase("Lavender Town")) {
                        System.out.println("Welcome to the pokemon maze!");
                        pokemaze();
                    } else {
                        System.out.println("Challenging Gym Leader");
                        if (trainer.trainerBag.badgeList.contains(currentCityStack.peek().cityBadge)) {
                            System.out.println("You have defeated this Gym Leader.");
                        } else
                            bt = new battleSystem(this.trainer, currentCityStack.peek().leader);
                    }
                    break;
                }
                case "3": {
                    currentCityStack.peek().wildPokemon.enemyBag.healPokemon();
                    System.out.println("Fighting Wild Pokemon!");
                    bt = new battleSystem(this.trainer, currentCityStack.peek().wildPokemon);
                    break;
                }
                case "4a": {
                    System.out.println("Showing Map!");
                    showMap();
                    break;
                }
                case "4b": {
                    System.out.println("Showing Pokemon!");
                    trainer.checkAvailablePokemon(trainer.trainerBag.pokemonList.get(0));
                    break;
                }
                case "4c": {
                    System.out.println("Showing badges");
                    System.out.println("Bag: " + trainer.trainerBag.badgeList);
                    break;
                }
                case "4d": {
                    System.out.println("Saving progress......");
                    saveGame.saveGame(currentCityStack.peek().cityName, trainer.trainerBag.pokemonList, trainer.trainerBag.badgeList);
                    end = true;
                    break;
                }
                case "5": {
                    Script.healthCareScript();
                    trainer.trainerBag.healPokemon();
                    break;
                }
                case "6": {
                    if (currentCity.equalsIgnoreCase("Saffron City")) {
                        Script.GaryDescription();
                        if (trainer.trainerBag.badgeList.size() == 8) {
                            int[] index = {0, 1, 2, 7, 9};
                            int pos = index[new Random().nextInt(5)];
                            City.setFateBattle(cityList[pos]);
                            dijkstra(pos);
                        }
                        else
                            System.out.println("Gary   : You have no enough strength to challenge me!");
                    }
                    else if (currentCity.equalsIgnoreCase("Fuchsia City")) {
                        SafariZone safari = new SafariZone();
						safari.playSafariZone();;
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
                case "0": {
                    if (currentCity.equalsIgnoreCase(City.fateBattle)) {
                        bt = new battleSystem(this.trainer, new Gary("Gary"));
                    }
                    else {
                        System.out.println("Invalid move!");
                    }
                }
                default: {
                    System.out.println("Invalid move!");
                    break;
                }
            }
            System.out.println();
        }
    }

    public static void pokemaze() {
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
                        } else {
                            maze[playerRow][playerCol] = hold;
                            System.out.println("You've hit a wall!");
                        }
                        break;
                    case "S":
                        if (maze[playerRow + 1][playerCol] != '#') {
                            maze[playerRow][playerCol] = hold;
                            playerRow++;
                            path.push("S");
                        } else {
                            maze[playerRow][playerCol] = hold;
                            System.out.println("You've hit a wall!");
                        }
                        break;
                    case "A":
                        if (maze[playerRow][playerCol - 1] != '#') {
                            maze[playerRow][playerCol] = hold;
                            playerCol--;
                            path.push("A");
                        } else {
                            maze[playerRow][playerCol] = hold;
                            System.out.println("You've hit a wall!");
                        }
                        break;
                    case "D":
                        if (maze[playerRow][playerCol + 1] != '#') {
                            maze[playerRow][playerCol] = hold;
                            playerCol++;
                            path.push("D");
                        } else {
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

                if (maze[playerRow][playerCol] == 'E') {
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

    public static void mumNagging() {
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
        for (int i = 0; i < patterns.length; i++) {
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
                        } else
                            System.out.println(line);

                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static boolean isValidDirection(String direction) {
        return direction.equals("W") || direction.equals("S") || direction.equals("A") || direction.equals("D");
    }




    public void rivalRace(){
        System.out.println("Welcome to the race you Punkhead! Let's see who reaches the ending first HAHAHAHA!");

        //picking a random destination (must be city not adjacent to Saffron city
        int [] availableCities = new int[]{0,1,2,7,9};
        int destination = availableCities[new Random().nextInt(5)];


        System.out.println("The battle has begun! Your rival Gary has challenged you to a race to  " + currentCityStack.peek().numToString(destination));

        dijkstra(destination);
    }

    //Dijkstra's algorithm (find shortest path)
    public void dijkstra(int destinationIndex) {
        DJKCityStack = new Stack<>();
        int sourceIndex = 8;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[1]));
        int[] distances = new int[10];
        boolean[] visited = new boolean[10];
        int[] previous = new int[10];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(previous, -1);

        distances[sourceIndex] = 0;
        pq.offer(new int[]{sourceIndex, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            DJKCityStack.push(city.get(u));

            if (visited[u]) continue;
            visited[u] = true;

            if (u == destinationIndex) break;


            for (int[] neighbor : DJKCityStack.peek().adjacent) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.offer(new int[]{v, distances[v]});
                    previous[v] = u;
                }
            }
        }

        printShortestPath(sourceIndex, destinationIndex, distances, previous);
    }

    public void printShortestPath(int sourceIndex, int destinationIndex, int[] distances, int[] previous) {
        if (distances[destinationIndex] == Integer.MAX_VALUE) {
            System.out.println("No path exists from " + City.cityList[sourceIndex] + " to " + City.cityList[destinationIndex]);
            return;
        }

        System.out.println("Shortest path from " + City.cityList[sourceIndex] + " to " + City.cityList[destinationIndex] + " is " + distances[destinationIndex] + " units");

        List<Integer> path = new ArrayList<>();
        for (int at = destinationIndex; at != -1; at = previous[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.print("Shortest Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(City.cityList[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

// tester for shortest path
//    public static void main(String[] args) {
//        CityController hi = new CityController();
//        city = new ArrayList<>();
//        currentCityStack = new Stack<>();
//        String[] cityList = new String[]{"Pallet Town", "Viridian City", "Pewter City", "Cerulean City", "Vermilion City", "Lavender Town", "Celadon City", "Fuchsia City", "Saffron City", "Cinnabar Island"};
//        int[][][] adjacent = new int[][][]{{{1, 5}, {9, 7}}, {{0, 5}, {2, 8}}, {{1, 8}, {3, 12}}, {{2, 12}, {8, 6}, {5, 9}}, {{8, 4}, {5, 5}, {7, 7}}, {{3, 9}, {8, 3}, {4, 5}, {7, 11}}, {{7, 10}, {8, 4}}, {{4, 7}, {6, 10}, {5, 11}, {9, 5}}, {{3, 6}, {5, 3}, {4, 3}, {6, 4}}, {{0, 7}, {7, 5}}};
//
//        //add cities to ArrayList
//        for (int i = 0; i < 10; i++) {
//            City newCity = new City(cityList[i]); // Create new City object
//            newCity.setAdjacent(adjacent[i]); // Set adjacent array
//            city.add(newCity);
//        }
//
//        //((City) city.get(0)).displayCity();
//        currentCityStack.push(city.getFirst());
//
//        hi.dijkstra();
//
//    }

    //Safari Zone tester class
//    public static void main(String[] args) {
//        CityController hi = new CityController();
//
//        hi.safariZone();
//    }

}
