package ung_wishlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CockroachDBExample {
    // JDBC URL for connecting to the database, replace 'your-database-url' with the actual URL
    static final String DB_URL = "jdbc:postgresql://ung-swe-7079.g8z.gcp-us-east1.cockroachlabs.cloud:26257/ung_swe";

    // Database credentials
    static final String USER = "Team_2";
    static final String PASS = "lxPD1YxuiuMKaxUiNglMCw";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // Verify connection
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }

            // Create a table
            stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255), age INT)";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully");

            // Execute a query
            System.out.println("Executing query...");
            String insertDataSQL = "INSERT INTO users (name, age) VALUES ('John', 30), ('Alice', 25), ('Bob', 35)";
            stmt.executeUpdate(insertDataSQL);
            System.out.println("Data inserted successfully");

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }
}
