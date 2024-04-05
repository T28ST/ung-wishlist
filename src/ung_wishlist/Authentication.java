package ung_wishlist;

import java.io.Reader;
import java.sql.*;

public class Authentication {
	static final String DB_URL = "jdbc:postgresql://ung-swe-7079.g8z.gcp-us-east1.cockroachlabs.cloud:26257/ung_swe";
	static final String DB_USER = "Team_2";
	static final String DB_PASS = "lxPD1YxuiuMKaxUiNglMCw";
	
	
	// Checks if given username already exists.	
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

	// Checks if given email already exists
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

	// Checks if a list with the given name already exists.
	public static boolean checkListExists(String listName) {
		boolean exists = false;
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create SQL string to inject
			
			String sql = "SELECT COUNT(*) AS count FROM list WHERE list_name = ?"; // Count the number of results that have that username
			// Prepare the sql statement and add the list's name to statement
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, listName);
				
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


	// Checks login username and password against existing data to log user in.
	// Returns user object if successful.
	public static User CheckLogin(String username, String password) {
		boolean check = false;
		
		// Connect to DB and search for users and check against password
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			// Create SQL string
			// Checks if given password matches password for account with the username given.
			String sql = "SELECT * FROM account WHERE username = ?";
			
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				
				statement.setString(1, username);
				
				try(ResultSet rs = statement.executeQuery()) {
					
					if (rs.next()) {
						
						String storedPassword = rs.getString("password");
						// Optional password decryption here.
						// Could include error message for failed logins.
						// Compare passwords directly
						//
						if ( storedPassword.equals(password)) {
							return new User(rs.getLong("account_id"), rs.getString("username"), rs.getString("f_name"), rs.getString("l_name"), rs.getString("email"));
						} else {
							return null;
						}
					}
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// hashing algorithm for password optional
		
		return null;
	}
	

	// Saves all information given into database as a new account.
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

	// Creates a new list in database assigned to given user ID with the given name.
	public static void createList(long ID, String listName) {
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			String sql = "INSERT INTO list (list_name) VALUES (?);"; 
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				
				statement.setString(1, listName);
				
				int rowsInserted = statement.executeUpdate();
				
				if (rowsInserted > 0) {
					System.out.println("List saved successfully.");
				} else {
					System.out.println("Failed to save list.");
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getUserLists(long ID) {
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			/*
			 * Starting SQL, not final.
			 * 
			 * SELECT l.list_name
			 * FROM list l
			 * JOIN account a ON l.account_id = a.account_id;
			 */
			
			
			String sql = ""; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
