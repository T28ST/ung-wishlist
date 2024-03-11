package ung_wishlist;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
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
            String createTableGiftSQL = "CREATE TABLE Gift (Gift_ID INT NOT NULL, Gift_Title VARCHAR(50) NOT NULL, Gift_Quantity INT NOT NULL, Gift_Link VARCHAR(300) NOT NULL, Purchased CHAR(1) NOT NULL, PRIMARY KEY (Gift_ID))";
            stmt.executeUpdate(createTableGiftSQL);
            System.out.println("Gift table created successfully");
            
         // Create List table
            String createTableListSQL = "CREATE TABLE List (List_ID INT NOT NULL, List_Name VARCHAR(50) NOT NULL, Gift_ID INT NOT NULL, PRIMARY KEY (List_ID), FOREIGN KEY (Gift_ID) REFERENCES Gift(Gift_ID))";
            stmt.executeUpdate(createTableListSQL);
            System.out.println("List table created successfully");

            // Create Account table
            String createTableAccountSQL = "CREATE TABLE Account (Account_ID INT NOT NULL, Password VARCHAR(30) NOT NULL, F_Name VARCHAR(30) NOT NULL, L_Name VARCHAR(30) NOT NULL, DOB DATE NOT NULL, Email VARCHAR(40) NOT NULL, List_ID INT NOT NULL, PRIMARY KEY (Account_ID), FOREIGN KEY (List_ID) REFERENCES List(List_ID))";
            stmt.executeUpdate(createTableAccountSQL);
            System.out.println("Account table created successfully");
            

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
