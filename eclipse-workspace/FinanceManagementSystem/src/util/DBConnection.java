package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/case_study"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "kavya@2212"; 
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connection successful!");
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
        return conn;
    }
}