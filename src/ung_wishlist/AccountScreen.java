package ung_wishlist;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Class purpose: 
// Account options with gifts list here and buttons to create a new one
// Edit details about accounts


public class AccountScreen extends JPanel{
	private MainFrame mainFrame;  
	private User currentUser; 
	
	// Passed currentUser class from LoginPanel stores
	public AccountScreen(MainFrame mainFrame, User currentUser) {
		this.mainFrame = mainFrame;
		this.currentUser = currentUser;
		
		// UI here 
		// Display User information (name, username etc...)
		// Display Lists user has created
		// Logout button
		// Create list button
		// Edit list button - Opens selected list to edit
		// Delete list button/function
		// Search for user button - Opens search screen
		
	}
	
	
	
	// Methods
	
		// Logout method
			// Clear store user information in User class 
			// Return to Login screen
	
		// Change Account Details (pass, user, name, email)
	
	
}

