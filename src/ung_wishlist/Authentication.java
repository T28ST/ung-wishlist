package ung_wishlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Authentication {
	static final String DB_URL = "jdbc:postgresql://ung-swe-7079.g8z.gcp-us-east1.cockroachlabs.cloud:26257/ung_swe";
	static final String DB_USER = "Team_2";
	static final String DB_PASS = "lxPD1YxuiuMKaxUiNglMCw";
	
	
	// Checks if given username already exists.	
	// Also used for checking if searched user exists.
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
	public static boolean checkListExists(String listName, long ID) {
		boolean exists = false;
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create SQL string to inject
			
			String sql = "SELECT COUNT(*) AS count FROM list l JOIN account a ON l.account_id = a.account_id WHERE list_name = ? AND a.account_id = ?"; // Count the number of results that have that username
			// Prepare the sql statement and add the list's name to statement
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, listName);
				statement.setLong(2, ID);
				
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
			
			String sql = "INSERT INTO list (list_name, account_id) VALUES (?, ?);"; 
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				
				statement.setString(1, listName);
				statement.setLong(2, ID);
				
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

	// Deletes a list with the given name that belongs to the logged in user.
	public static void deleteList(long ID, String listName) {
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			String sql = "DELETE FROM list WHERE list_name = ? AND account_id = ?";
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				
				statement.setString(1,listName);
				statement.setLong(2, ID);
				
				int rowsDeleted = statement.executeUpdate();
				
				if (rowsDeleted > 0) {
					System.out.println("Rows deleted successfully");
				} else {
					System.out.println("No rows deleted.");
				}
					
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Saves a gifts in a list to the database
	public static void saveListGifts(long listID, List<ItemDetails> gifts) {
		
		// gifts passed as object
		// account id passed
		// for each object:
		// Check if gift id exists
		// save row in db accessing to account
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			for (ItemDetails gift : gifts) {
				
				String selectQuery = "SELECT * FROM gift WHERE gift_id = ?";
				PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
				selectStatement.setLong(1, gift.getID());
				ResultSet resultSet = selectStatement.executeQuery();
				
				if (resultSet.next()) {
					// If there are items in the result set
					// Update them with new information
					String updateQuery = "UPDATE gift SET gift_title = ?, gift_desc = ?, gift_price = ?, gift_link = ?, purchased = ? WHERE gift_id = ?";
					PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
					updateStatement.setString(1, gift.getName());
					updateStatement.setString(2, gift.getDescription());
					updateStatement.setDouble(3, gift.getPrice());
					updateStatement.setString(4, gift.getLink());
					updateStatement.setBoolean(5, gift.getPurchased());
					updateStatement.setLong(6, gift.getID());
					updateStatement.executeUpdate();
					System.out.println("Item " + gift.getID() + " " + gift.getName() +" updated.");
					
				} else {
					// If the item doesn't already exist
					// add to table
					String insertQuery = "INSERT INTO gift (gift_title, gift_desc, gift_price, gift_link, purchased, list_id) VALUES (?,?,?,?,?,?)";
					PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
					insertStatement.setString(1, gift.getName());
					insertStatement.setString(2, gift.getDescription());
					insertStatement.setDouble(3, gift.getPrice());
					insertStatement.setString(4, gift.getLink());
					insertStatement.setBoolean(5, gift.getPurchased());
					insertStatement.setLong(6, listID);
					insertStatement.executeUpdate();
					
					ResultSet generatedKeys = insertStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						long id = generatedKeys.getLong(1);
						System.out.println("New gift inserted with ID " + id);
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
		
		
	}
	

	// deleteGifts(userID listID deletedGiftsList)
		//	for each id in list:
		//		delete each gift on DB with that ID
	
	public static void deleteGifts(List<Long> ids) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			// Create a string of ids seperated by commas
			StringBuilder idString = new StringBuilder();
			for (int i = 0; i < ids.size(); i++) {
				if (i > 0) {
					idString.append(", ");
				}
				idString.append("?");
				
			}
			
			// Delete Query
			String deleteQuery = "DELETE FROM gift WHERE gift_id IN (" + idString + ")";
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			
			// Set id values in delete query
			for (int i = 0; i < ids.size(); i++) {
				deleteStatement.setLong(i + 1, ids.get(i));
			}
			
			int rowsAffected = deleteStatement.executeUpdate();
			System.out.println(rowsAffected + " row(s) deleted");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void getUserLists(long ID, JList<String> list) {
		ResultSet resultSet = null;
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
						
			String sql = "SELECT l.list_name FROM account a JOIN list l ON a.account_id = l.account_id WHERE a.account_id = ?"; 
			
			try(PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
				statement.setLong(1, ID);
				resultSet = statement.executeQuery();
				
				resultSet.beforeFirst();				
				DefaultListModel<String> model = new DefaultListModel<>();
				
				try {
					boolean resultSetEmpty = true;
					
					while (resultSet.next() ) {
						String data = resultSet.getString("list_name");
						model.addElement(data);
						resultSetEmpty = false;
					}
					
					list.setModel(model);
					
					if (resultSetEmpty) {
						model.addElement("No Data Available.");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (resultSet != null) {
							resultSet.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<ItemDetails> getListGifts(long ID) {
		ResultSet resultSet = null;
		ArrayList<ItemDetails> list = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
		
			String sql = "SELECT g.gift_id, g.gift_title, g.gift_desc, g.gift_price, g.gift_link, g.purchased FROM list l JOIN gift g ON g.list_id = l.list_id WHERE l.list_id = ?"; 
			
			try(PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
				statement.setLong(1, ID);
				resultSet = statement.executeQuery();
				
				// While there are items in the result set
				// add each to the list arraylist
				while (resultSet.next()) {
					long giftId = resultSet.getLong("gift_id");
					String name = resultSet.getString("gift_title");
					String description = resultSet.getString("gift_desc");
					double price = resultSet.getDouble("gift_price");
					String link = resultSet.getString("gift_link");
					Boolean purchased = resultSet.getBoolean("purchased");
					
		            list.add(new ItemDetails(giftId, name, description, link, price, purchased));
				}
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Get the ID of the list with the given name.
	public static long getListID(long ID, String listName) {
		long listID = 0;
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			
			String sql = "SELECT l.list_id FROM account a JOIN list l ON a.account_id = l.account_id WHERE l.account_id = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
				statement.setLong(1, ID);
				ResultSet resultSet = statement.executeQuery();
				
				if (resultSet.next()) {
					listID = resultSet.getLong("list_id");
				} else {
					System.out.println("getListID: result set is empty.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listID;
		
	}
		
	// getSearchedUser(String username)
		// sets new User object and returns
		// only username and ID;
	public static User getSearchedUser(String username) {
		User user = null;
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT username, account_id FROM account WHERE username = ?";
			
			
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				
				statement.setString(1, username);
				
				try(ResultSet rs = statement.executeQuery()) {
					
					if (rs.next()) {
						return new User(rs.getLong("account_id"), rs.getString("username"), null, null, null);
					} else {
						System.out.println("getSearchedUser: result set is empty");
					}
					
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// Update gift purchase status
	public static void updatePurchased(long giftID, boolean status) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			String selectQuery = "SELECT * FROM gift WHERE gift_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setLong(1, giftID);
			ResultSet resultSet = selectStatement.executeQuery();
			
			if (resultSet.next()) {
				// If the gift exists
				// Update its purchased status
				String updateQuery = "UPDATE gift SET purchased = ? WHERE gift_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setBoolean(1, status);
                updateStatement.setLong(2, giftID);
                updateStatement.executeUpdate();
                System.out.println("Gift " + giftID + " updated purchased status as:  " + status);
		} else {
			System.out.println("Gift doesn't exist in database!");
			return;
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
