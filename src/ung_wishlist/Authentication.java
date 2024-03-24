package ung_wishlist;

import java.io.Reader;
import java.sql.*;

public class Authentication {
	static final String DB_URL = "jdbc:postgresql://ung-swe-7079.g8z.gcp-us-east1.cockroachlabs.cloud:26257/ung_swe";
	static final String DB_USER = "Team_2";
	static final String DB_PASS = "lxPD1YxuiuMKaxUiNglMCw";
		
	public static boolean checkUsernameExists (String username) { //for account creation
		
		boolean exists = false;
		
		//When creating new account, check if username is already taken
		// Connect to DB
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create SQL string to inject
			
			String sql = "SELECT COUNT(*) AS count FROM account WHERE username = ?"; // Count the number of results that have that username
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

	public static boolean checkEmailExists(String email){ //for account creation
		boolean exists = false;
		
		//When creating new account, check if email  is already taken
		// Connect to DB
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create SQL string to inject
			
			String sql = "SELECT COUNT(*) AS count FROM account WHERE email = ?"; // Count the number of results that have that email
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
						System.out.println(exists);
						
					}
				}
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return exists;
	}


	public static boolean isPasswordValid(String password){
		// Only checks for length
		// Needs Caps, Character and Digit Check.
		if(password.length() <= 30) return true;
		else return false;
		
	}

	public static boolean CheckLogin(String username, String password) {
		boolean check = false;
		
		// Connect to DB and search for users and check against password
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			// Create SQL string
			String sql = "SELECT password FROM account WHERE username = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, username);
				try(ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						String storedPassword = resultSet.getString("password");
						// Optional password decryption here.
						
						// Compare passwords directly
						return storedPassword.equals(password);
					}
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// hashing algorithm for password optional
		
		return check;
	}
	

	public static void createAccount(String firstName, String lastName, String emailAddress, /*String dateOfBirth,*/ String username, String password) {
		// Saves the given information to the account table 
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			// SQL string to execute.
			String sql = "INSERT INTO account (f_name, l_name, email, dob, username, password) VALUES (?, ?, ?, ?, ?, ?)";
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				// Set VALUES to entered strings
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				statement.setString(3, emailAddress);
				statement.setString(4, "2000-01-01");
				statement.setString(5, username);
				statement.setString(6, password);
				
				// variable for verification
				int rowsInserted = statement.executeUpdate();
				
				if (rowsInserted > 0) {
					System.out.println("Account created successfully.");
				} else {
					System.out.println("Failed to create account.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
