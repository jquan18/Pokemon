import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    private Connection connection; // Declare the connection as an instance variable

    public static void main(String[] args) {
        TestDBConnection testDBConnection = new TestDBConnection(); // Create an instance
        testDBConnection.connect(); // Call the connect method
    }

    public void connect() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Use the IP address of the Windows host
            connection = DriverManager.getConnection("jdbc:mysql://172.23.240.1:3306/pokemon_game", "root", "root");
            System.out.println("Successfully connected to MySQL database.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to connect to MySQL database.");
            e.printStackTrace();
        } finally {
            // Close the connection
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
