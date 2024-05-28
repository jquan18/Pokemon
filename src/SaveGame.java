import java.sql.*;
import java.util.Scanner;
import org.json.*;

public class SaveGame {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12709702";
    private static final String DB_USERNAME = "sql12709702";
    private static final String DB_PASSWORD = "dMq5bLJnEt";

    private Scanner scanner = new Scanner(System.in);
    private Script script = new Script();
    public String username;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SaveGame saveGame = new SaveGame();
        System.out.println("Welcome to Pokémon Game!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                saveGame.register();
                break;
            case 2:
                saveGame.login();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement checkUserStmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {


            checkUserStmt.setString(1, username);
            ResultSet resultSet = checkUserStmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username already exists.");
                return;
            }


            statement.setString(1, username);
            statement.setString(2, password);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Register successful.");
            } else {
                System.out.println("Register failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to register.");
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful.");
                this.username = username;
                gameMenu(username);
            } else {
                System.out.println("Login failed: Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to login.");
        }
    }

    private void gameMenu(String username) {
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                startNewGame(username);
                break;
            case 2:
                loadGame(username);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void startNewGame(String username) {
        System.out.print("Choose a save slot (1, 2, 3): ");
        int slot = scanner.nextInt();
        scanner.nextLine();

        Trainer trainer = new Trainer(username);


        Pokemon partner = script.choosePartner();
        trainer.choosePartnerPokemon(partner);


        PokemonList pokemonList = new PokemonList();
        pokemonList.add(0, partner);


        String location = "Pallet Town";


        saveGame(username, slot, location, pokemonList);


        CityController controlPanel = new CityController(trainer, this, username, slot, location, pokemonList);
        controlPanel.runCity();

    }

	public void saveGame(String username, int slot, String location, PokemonList pokemonList) {
		if (username == null || location == null || pokemonList == null) {
			System.out.println("One of the required parameters is null.");
			return;
		}

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			 PreparedStatement checkUserStmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {


			checkUserStmt.setString(1, this.username);
			ResultSet resultSet = checkUserStmt.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Username does not exist.");
				return;
			}


			String saveGameQuery = "INSERT INTO game_saves (username, slot, location) " +
								   "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE location = VALUES(location)";
			try (PreparedStatement saveGameStmt = connection.prepareStatement(saveGameQuery, Statement.RETURN_GENERATED_KEYS)) {
				saveGameStmt.setString(1, username);
				saveGameStmt.setInt(2, slot);
				saveGameStmt.setString(3, location);

				saveGameStmt.executeUpdate();


				ResultSet generatedKeys = saveGameStmt.getGeneratedKeys();
				int saveId;
				if (generatedKeys.next()) {
					saveId = generatedKeys.getInt(1);
				} else {

					try (PreparedStatement getSaveIdStmt = connection.prepareStatement("SELECT id FROM game_saves WHERE username = ? AND slot = ?")) {
						getSaveIdStmt.setString(1, username);
						getSaveIdStmt.setInt(2, slot);
						ResultSet rs = getSaveIdStmt.executeQuery();
						if (rs.next()) {
							saveId = rs.getInt("id");
						} else {
							System.out.println("Failed to retrieve the save ID.");
							return;
						}
					}
				}


				String savePokemonQuery = "REPLACE INTO pokemon (save_id, name, type, hp, maxHP, attack, defense, speed, " +
										  "quickMoveName, quickMoveType, quickMoveDamage, quickMovePoints, mainMoveName, mainMoveType, mainMoveDamage, mainMovePoints) " +
										  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				try (PreparedStatement savePokemonStmt = connection.prepareStatement(savePokemonQuery)) {
					for (Pokemon pokemon : pokemonList.getAllPokemon()) {
						if (pokemon == null) {
							continue;
						}
						savePokemonStmt.setInt(1, saveId);
						savePokemonStmt.setString(2, pokemon.getName());
						savePokemonStmt.setString(3, String.join(",", pokemon.getType().getMovesTypeString()));
						savePokemonStmt.setInt(4, pokemon.getHP());
						savePokemonStmt.setInt(5, pokemon.getMaxHP());
						savePokemonStmt.setInt(6, pokemon.getAttack());
						savePokemonStmt.setInt(7, pokemon.getDefense());
						savePokemonStmt.setInt(8, pokemon.getSpeed());
						savePokemonStmt.setString(9, pokemon.quickMove.getMovesName());
						savePokemonStmt.setString(10, String.join(",", pokemon.quickMove.getMovesType().getMovesTypeString()));
						savePokemonStmt.setInt(11, pokemon.quickMove.getDPR());
						savePokemonStmt.setInt(12, pokemon.quickMove.QMPoint);
						savePokemonStmt.setString(13, pokemon.mainMove.getMovesName());
						savePokemonStmt.setString(14, String.join(",", pokemon.mainMove.getMovesType().getMovesTypeString()));
						savePokemonStmt.setInt(15, pokemon.mainMove.getDPR());
						savePokemonStmt.setInt(16, pokemon.mainMove.MMPoint);

						savePokemonStmt.addBatch();
					}
					savePokemonStmt.executeBatch();
					System.out.println("Game saved successfully.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to save game.");
		}
	}




    private void loadGame(String username) {
        System.out.print("Choose a save slot (1, 2, 3): ");
        int slot = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            String loadGameQuery = "SELECT id, location FROM game_saves WHERE username = ? AND slot = ?";
            try (PreparedStatement loadGameStmt = connection.prepareStatement(loadGameQuery)) {
                loadGameStmt.setString(1, username);
                loadGameStmt.setInt(2, slot);
                ResultSet gameResultSet = loadGameStmt.executeQuery();

                if (gameResultSet.next()) {
                    int saveId = gameResultSet.getInt("id");
                    String location = gameResultSet.getString("location");


                    String loadPokemonQuery = "SELECT * FROM pokemon WHERE save_id = ?";
                    try (PreparedStatement loadPokemonStmt = connection.prepareStatement(loadPokemonQuery)) {
                        loadPokemonStmt.setInt(1, saveId);
                        ResultSet pokemonResultSet = loadPokemonStmt.executeQuery();

                        PokemonList pokemonList = new PokemonList();
                        int index = 0;
                        while (pokemonResultSet.next()) {
                            String name = pokemonResultSet.getString("name");
                            String[] type = pokemonResultSet.getString("type").split(",");
                            int hp = pokemonResultSet.getInt("hp");
                            int maxHP = pokemonResultSet.getInt("maxHP");
                            int attack = pokemonResultSet.getInt("attack");
                            int defense = pokemonResultSet.getInt("defense");
                            int speed = pokemonResultSet.getInt("speed");

                            String quickMoveName = pokemonResultSet.getString("quickMoveName");
                            String quickMoveType = pokemonResultSet.getString("quickMoveType");
                            int quickMoveDamage = pokemonResultSet.getInt("quickMoveDamage");
                            int quickMovePoints = pokemonResultSet.getInt("quickMovePoints");

                            String mainMoveName = pokemonResultSet.getString("mainMoveName");
                            String mainMoveType = pokemonResultSet.getString("mainMoveType");
                            int mainMoveDamage = pokemonResultSet.getInt("mainMoveDamage");
                            int mainMovePoints = pokemonResultSet.getInt("mainMovePoints");

                            Pokemon pokemon = new Pokemon(name, type, maxHP, hp, attack, defense, speed, quickMoveName, quickMoveType, quickMoveDamage, mainMoveName, mainMoveType, mainMoveDamage);
                            pokemon.quickMove.QMPoint = quickMovePoints;
                            pokemon.mainMove.MMPoint = mainMovePoints;

                            pokemonList.add(index, pokemon);
                            index++;
                        }

                        System.out.println("Load successful.");


                        Trainer trainer = new Trainer(username);
                        CityController controlPanel = new CityController(trainer, this, username, slot, location, pokemonList);
                        controlPanel.runCity();
                    }
                } else {
                    System.out.println("Load failed: No save data found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to load game.");
        }
    }

}
