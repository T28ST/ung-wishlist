package ung_wishlist;

import java.sql.*;

public class Authentication {
	static final String DB_URL = "jdbc:postgresql://ung-swe-7079.g8z.gcp-us-east1.cockroachlabs.cloud:26257/ung_swe";
	static final String DB_USER = "Team_2";
	static final String DB_PASS = "lxPD1YxuiuMKaxUiNglMCw";
		
	public static boolean checkUsernameExists (String username) {
		
		boolean exists = false;
		
		//When creating new account, check if username is already taken
		// Connect to DB
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create SQL string to inject
			
			String sql = "SELECT COUNT(*) AS count FROM users WHERE username = ?"; // Count the number of results that have that username
			// Prepare the sql statement and add username to string
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, username);
				
				// Execute SQL
				try (ResultSet resultSet = statement.executeQuery()) {
					// if there is a result table, look at the first row.
					if (resultSet.next()) {
						// Get the results from the SQL statement in the count column
						int count = resultSet.getInt("count");
						// if there are more than 0, set exists to TRUE
						exists = count > 0;
						
					}
				}
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return exists;
	}

	public static boolean checkEmailExists(String email){
		boolean exists = false;
		
		//When creating new account, check if email  is already taken
		// Connect to DB
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create SQL string to inject
			
			String sql = "SELECT COUNT(*) AS count FROM users WHERE email = ?"; // Count the number of results that have that email
			// Prepare the sql statement and add email to string
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, email);
				
				// Execute SQL
				try (ResultSet resultSet = statement.executeQuery()) {
					// if there is a result table, look at the first row.
					if (resultSet.next()) {
						// Get the results from the SQL statement in the count column
						int count = resultSet.getInt("count");
						// if there are more than 0, set exists to TRUE
						exists = count > 0;
						
					}
				}
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return exists;
	}

	public static boolean isPasswordCorrect(String username, String password) {
		boolean check = false;
		
		// Connect to DB and search for users and check against password
		// hashing algorithm for password optional
		
		return check;
	}
	
	public static boolean checkLogin() {
		return false;
	}
	
	public static boolean createAccount(String username, String password, String Dob, String Fname, String Lname, String email) {
		return false;
	}
	
}
