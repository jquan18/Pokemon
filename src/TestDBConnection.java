import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    private Connection connection;

    public static void main(String[] args) {
        TestDBConnection testDBConnection = new TestDBConnection();
        testDBConnection.connect();
    }

    public void connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            connection = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12709702", "sql12709702", "dMq5bLJnEt");
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
