package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getPropertyString(String filename) {
        Properties prop = new Properties();
        try (FileInputStream file = new FileInputStream(filename)) {
            // Load properties file
            prop.load(file);
            // Constructing the connection string
            String hostname = prop.getProperty("hostname");
            String port = prop.getProperty("port");
            String databaseName = prop.getProperty("database_name");
            return "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // or throw a custom exception
        }
    }

    public static void main(String[] args) {
        // Specify the path to your properties file here
        String filename = "C:\\Users\\hp\\Documents\\db.properties.txt"; // Update this path
        String connectionString = getPropertyString(filename);
        if (connectionString != null) {
            System.out.println("Connection String: " + connectionString);
        } else {
            System.out.println("Failed to load properties file.");
        }
    }
}
