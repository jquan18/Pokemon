import java.sql.*;
import java.util.*;

public class SaveGame {

	private static final String DB_URL = "jdbc:mysql://pokemon-jquan-pokemon.h.aivencloud.com:20245/pokemon_game";
	private static final String DB_USERNAME = "avnadmin";
	private static final String DB_PASSWORD = "AVNS_DZSw_zX_KzacaJnoOj7";

	private Scanner sc = new Scanner(System.in);
	private String username;
	private String password;
	private boolean isLoggedIn;
	private int slot;

	public static void main(String[] args) {
		SaveGame saveGame = new SaveGame();
		saveGame.saveGameMain();
	}

	public void saveGameMain() {
		System.out.println("Welcome to Pokemon Game");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.print("Choose an option: ");
		int choice = sc.nextInt();
		sc.nextLine();
		saveGameMainChoice(choice);
	}

	public void saveGameMainChoice(int choice) {
		switch (choice) {
			case 1:
				if (firstTimeRegister()) {
					login(this.username, this.password);
					System.out.println("Done login");
				}
				break;
			case 2:
				login();
				if (!this.isLoggedIn) {
					System.out.println("Do you want to register a new account? (Y/N)");
					String response = sc.next();
					if (response.equalsIgnoreCase("Y")) {
						saveGameMainChoice(1);
					} else {
						saveGameMainChoice(2);
					}
				}
				break;
			default:
				System.out.println("Invalid choice.");
				break;
		}
	}

	private boolean firstTimeRegister() {
		Script.prologue();
		Script.getTrainerName();
		return register();
	}

	private boolean register() {
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			 PreparedStatement checkUserStmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			 PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

			checkUserStmt.setString(1, username);
			ResultSet resultSet = checkUserStmt.executeQuery();
			if (resultSet.next()) {
				System.out.println("Username already exists.");
				register();
			}

			statement.setString(1, username);
			statement.setString(2, password);
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				this.username = username;
				this.password = password;
				System.out.println("Register successful.");
				return true;
			} else {
				System.out.println("Register failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to register.");
		}
		return false;
	}

	private void login(String username, String password) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			 PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				this.username = username;
				System.out.println("Login successful.");
				this.isLoggedIn = true;
				gameMenu(username);
			} else {
				System.out.println("Login failed: Invalid username or password.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to login.");
		}
		this.isLoggedIn = false;
	}

	private void login() {
		System.out.println("Welcome Back Trainer!");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		login(username, password);
	}

	private void gameMenu(String username) {
		System.out.println("1. Start New Game");
		System.out.println("2. Load Game");
		System.out.print("Choose an option: ");
		int choice = sc.nextInt();
		sc.nextLine();

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
		int slot = sc.nextInt();
		this.slot = slot;
		sc.nextLine();

		Trainer trainer = new Trainer(username);
		Pokemon partner = Script.choosePartner();
		trainer.choosePartnerPokemon(partner);

		PokemonList pokemonList = new PokemonList();
		pokemonList.add(0, partner);

		String location = "Pallet Town";
		saveGame(location, pokemonList, trainer.trainerBag.badgeList);

		CityController controlPanel = new CityController(trainer, this, location, pokemonList);
		controlPanel.runCity();
	}

	public void saveGame(String location, PokemonList pokemonList, List<String> badges) {
		if (location == null || pokemonList == null || badges == null) {
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

			String saveGameQuery = "INSERT INTO game_saves (username, slot, location) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE location = VALUES(location)";
			try (PreparedStatement saveGameStmt = connection.prepareStatement(saveGameQuery, Statement.RETURN_GENERATED_KEYS)) {
				saveGameStmt.setString(1, this.username);
				saveGameStmt.setInt(2, this.slot);
				saveGameStmt.setString(3, location);

				saveGameStmt.executeUpdate();

				ResultSet generatedKeys = saveGameStmt.getGeneratedKeys();
				int saveId;
				if (generatedKeys.next()) {
					saveId = generatedKeys.getInt(1);
				} else {
					try (PreparedStatement getSaveIdStmt = connection.prepareStatement("SELECT id FROM game_saves WHERE username = ? AND slot = ?")) {
						getSaveIdStmt.setString(1, this.username);
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

				String savePokemonQuery = "REPLACE INTO pokemon (save_id, slot, name, type, hp, maxHP, attack, defense, speed, " +
						"quickMoveName, quickMoveType, quickMoveDamage, quickMovePoints, mainMoveName, mainMoveType, mainMoveDamage, mainMovePoints) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				try (PreparedStatement savePokemonStmt = connection.prepareStatement(savePokemonQuery)) {
					for (int i = 0; i < pokemonList.getAllPokemon().size(); i++) {
						Pokemon pokemon = pokemonList.getAllPokemon().get(i);
						if (pokemon == null) {
							continue;
						}
						savePokemonStmt.setInt(1, saveId);
						savePokemonStmt.setInt(2, i + 1);
						savePokemonStmt.setString(3, pokemon.getName());
						savePokemonStmt.setString(4, String.join(",", pokemon.getType().getMovesTypeString()));
						savePokemonStmt.setInt(5, pokemon.getHP());
						savePokemonStmt.setInt(6, pokemon.getMaxHP());
						savePokemonStmt.setInt(7, pokemon.getAttack());
						savePokemonStmt.setInt(8, pokemon.getDefense());
						savePokemonStmt.setInt(9, pokemon.getSpeed());
						savePokemonStmt.setString(10, pokemon.quickMove.getMovesName());
						savePokemonStmt.setString(11, String.join(",", pokemon.quickMove.getMovesType().getMovesTypeString()));
						savePokemonStmt.setInt(12, pokemon.quickMove.getDPR());
						savePokemonStmt.setInt(13, pokemon.quickMove.QMPoint);
						savePokemonStmt.setString(14, pokemon.mainMove.getMovesName());
						savePokemonStmt.setString(15, String.join(",", pokemon.mainMove.getMovesType().getMovesTypeString()));
						savePokemonStmt.setInt(16, pokemon.mainMove.getDPR());
						savePokemonStmt.setInt(17, pokemon.mainMove.MMPoint);

						savePokemonStmt.addBatch();
					}
					savePokemonStmt.executeBatch();
				}

				String saveBadgeQuery = "REPLACE INTO badges (save_id, badge_name) VALUES (?, ?)";
				try (PreparedStatement saveBadgeStmt = connection.prepareStatement(saveBadgeQuery)) {
					for (String badge : badges) {
						saveBadgeStmt.setInt(1, saveId);
						saveBadgeStmt.setString(2, badge);
						saveBadgeStmt.addBatch();
					}
					saveBadgeStmt.executeBatch();
				}

				System.out.println("Game saved successfully.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to save game.");
		}
	}

	private void loadGame(String username) {
		System.out.print("Choose a save slot (1, 2, 3): ");
		int slot = sc.nextInt();
		sc.nextLine();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

			String loadGameQuery = "SELECT id, location FROM game_saves WHERE username = ? AND slot = ?";
			try (PreparedStatement loadGameStmt = connection.prepareStatement(loadGameQuery)) {
				loadGameStmt.setString(1, username);
				loadGameStmt.setInt(2, slot);
				ResultSet gameResultSet = loadGameStmt.executeQuery();

				if (gameResultSet.next()) {
					int saveId = gameResultSet.getInt("id");
					String location = gameResultSet.getString("location");

					String loadPokemonQuery = "SELECT * FROM pokemon WHERE save_id = ? ORDER BY slot";
					try (PreparedStatement loadPokemonStmt = connection.prepareStatement(loadPokemonQuery)) {
						loadPokemonStmt.setInt(1, saveId);
						ResultSet pokemonResultSet = loadPokemonStmt.executeQuery();

						PokemonList pokemonList = new PokemonList();
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

							pokemonList.add(pokemonList.getAllPokemon().size(), pokemon);
						}

						String loadBadgeQuery = "SELECT badge_name FROM badges WHERE save_id = ?";
						try (PreparedStatement loadBadgeStmt = connection.prepareStatement(loadBadgeQuery)) {
							loadBadgeStmt.setInt(1, saveId);
							ResultSet badgeResultSet = loadBadgeStmt.executeQuery();

							List<String> badges = new ArrayList<>();
							while (badgeResultSet.next()) {
								badges.add(badgeResultSet.getString("badge_name"));
							}

							System.out.println("Load successful.");

							Trainer trainer = new Trainer(username);
							trainer.trainerBag.badgeList.addAll(badges);
							CityController controlPanel = new CityController(trainer, this, location, pokemonList);
							controlPanel.runCity();
						}
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
