

import java.sql.*;
import java.util.*;

public class SaveGame {
	private Connection connection;

	private void connectToDatabase() {
		try {
			String instanceConnectionName = "midyear-grid-421206:asia-east1:pikachu";
			String databaseName = "savegamedb";
			String username = "root";
			String password = "root";

			String jdbcUrl = String.format(
				"jdbc:mysql://google/%s?cloudSqlInstance=%s&"
				+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
				databaseName, instanceConnectionName);

			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connected to database successfully.");
		} catch (SQLException e) {
			System.out.println("Error: Unable to connect to MySQL database.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println(System.getProperty("java.class.path"));

        SaveGame game = new SaveGame();
        game.connectToDatabase();
    }

}
