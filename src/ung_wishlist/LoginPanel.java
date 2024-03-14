package ung_wishlist;

import javax.swing.*;

public class LoginPanel extends JPanel{
	// Components
	private JTextField FName;
	private JTextField LName;
	private JTextField email;
	private JTextField DoB;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton loginButton;
	private JButton forgotpassword;
	private JButton createaccount;
	
	
	public LoginPanel() {
		// Initialize components and add to the panel
	}
	
	//Methods
	
	// Login
	// When login button is pushed
	// Get information from the text boxes and put into string variables
	// If Login is true ( Call authentication class to check the username and password)
	// Else say invalid
	// Return to login screen

	
	// Create account
	
	
	// send to database (new class for sending new account to DB)

	public void createAccount(){

		// Get account info from text boxes ( fname, lname, username, email, pass)

		String firstName = FName.getText();
        String lastName = LName.getText();
        String emailAddress = email.getText();
        String dateOfBirth = DoB.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

		// check that they don't already exist ( probably new class for checking for already existing username/email.)

		boolean usernameExists = Authentication.checkUsernameExists(username);
        boolean emailExists = Authentication.checkEmailExists(emailAddress);
	}
	
	// Forgot password
	
	
	
}
