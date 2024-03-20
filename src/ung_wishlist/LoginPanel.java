package ung_wishlist;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class LoginPanel extends JPanel{
	// Components
	// FName
	private String FName;
	// LName
	private String LName;
	// email
	private String Email;
	// DoB
	private int DoB;
	
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton loginButton;
	// Forgot password button
	// Create account button
	private JButton forgotPasswordButton;
	private JButton createAccountButton;
	private JTextComponent emailField;
	private JTextComponent lnameField;
	private JTextComponent fnameField;
	private JTextComponent dobField;

	
	public LoginPanel() {

		initializeComponents();
	}
	
	private void initializeComponents() {

        usernameField = new JTextField();
        passwordField = new JTextField();

        loginButton = new JButton("Login");
        forgotPasswordButton = new JButton("Forgot Password");
        createAccountButton = new JButton("Create Account");

        add(usernameField);
        add(passwordField);
        add(loginButton);
        add(forgotPasswordButton);
        add(createAccountButton);
    }

	//Methods
	
	// *create Login method-Done
	// When login button is pushed <- not sure what to do here ngl
	// *Get information from the text boxes and put into string variables-Done(i think)
	// *If Login is true ( Call authentication class to check the username and password)- Done
	// *Else say invalid-Done
	// Return to login screen- i will do my best

	public void login() {
		
    String username = usernameField.getText();
    String password = passwordField.getText();

    boolean isAuthenticated = Authentication.isPasswordCorrect(username, password);

    if (isAuthenticated) {
        JOptionPane.showMessageDialog(this, "Login successful!");
        usernameField.setText("");
        passwordField.setText("");
        
    } 
    	else {
        JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

	
	// Create account method
	// Get account info from text boxes ( fname, lname, username, email, pass)
	// check that they don't already exist ( probably new class for checking for already existing username/email.)
	// send to database (new class for sending new account to DB)
	
	public void createAccount() {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String FName = fnameField.getText();
        String LName = lnameField.getText();
        String Email = emailField.getText();
        String DoB = dobField.getText();
        

        boolean usernameExists = checkUsernameExists(username); 
        boolean emailExists = Authentication.checkEmailExists(Email);
        if (!Authentication.checkUsernameExists(username) && !Authentication.checkEmailExists(Email)) {
            Authentication.createAccount(username, password,FName, LName, Email, DoB);
            
        }
	} 
        
        //sendToDatabase(username, password, FName, LName, Email, DoB); <- no idea here either
	
	
	 private boolean checkUsernameExists(String username) {
	        // Logic to check if the username already exists in the database
	        // This could involve querying your database
		 	//No idea how to do this, this is on you Tyler lmao xD        
	        return true;
	    }
	
	
	// Forgot password method <- this will require alot of SQL implmentation and calling apperently so im not sure how you want me to go about this tbh.
	
	// (0_0)
	
	
	public static void forgotPassword(String usernameOrEmail) {
		
	}
}
	
	

