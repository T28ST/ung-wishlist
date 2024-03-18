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

		if (usernameExists) {
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one."); //username taken
        } else if (emailExists) {
            JOptionPane.showMessageDialog(null, "Email is already associated with an account. Please use a different one."); //email already in use
        } else {
			boolean passwordValid = Authentication.isPasswordValid(password);

			if(passwordValid){
				AccountDatabaseManager.createAccount(firstName, lastName, emailAddress, dateOfBirth, username, password);
            	JOptionPane.showMessageDialog(null, "Account created successfully!");
			} else {
				JOptionPane.showMessageDialog(null, "Password is invalid.");
			}


            // Here you should implement the logic to send the account information to the database.
            // Assuming that logic is implemented in a separate class called AccountDatabaseManager.
        }
	}
	
	// Forgot password
	
	
	
}
