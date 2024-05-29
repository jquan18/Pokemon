import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    private Connection connection;
	private static final String DB_URL = "jdbc:mysql://pokemon-jquan-pokemon.h.aivencloud.com:20245/pokemon_game";
	private static final String DB_USERNAME = "avnadmin";
	private static final String DB_PASSWORD = "AVNS_DZSw_zX_KzacaJnoOj7";
    public static void main(String[] args) {
        TestDBConnection testDBConnection = new TestDBConnection();
        testDBConnection.connect();
    }

    public void connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Successfully connected to MySQL database.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to connect to MySQL database.");
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error: Unable to close the database connection.");
                    e.printStackTrace();
                }
            }
        }
    }
}
