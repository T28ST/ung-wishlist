package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.sql.SQLException;


public class LoginPanel extends JPanel{
	private String FName;
	private String LName;
	private String Email;
	private int DoB;
	
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton loginButton;

	private JButton forgotPasswordButton;
	private JButton createAccountButton;
	private JTextComponent emailField;
	private JTextComponent lnameField;
	private JTextComponent fnameField;
	private JTextComponent dobField;

	
	public LoginPanel() {
		setLayout(new GridLayout(4, 2));
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JTextField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String username = usernameField.getText();
        		String password = passwordField.getText();
        		
        		login();
        	}
        } );
        
	   forgotPasswordButton = new JButton("Forgot Password");
       forgotPasswordButton.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   JOptionPane.showMessageDialog(null, "Don't forget next time silly");
    	   }
       });
	    
	    createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				
				CreateAccount createAccount = new CreateAccount();
				try {
				createAccount.main(null);
				} catch (SQLException sql) {
					System.out.println("SQL Exception occured " + sql.getMessage());
				}
				
        	}
        });
	    
	    
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // empty label as placeholder
        add(loginButton);
        add(createAccountButton);
        add(forgotPasswordButton);

	}
	//Methods
	

	// Login
	// pulls information from login panel
	// checks that they are correct
	// if so, login and go to account screen.
	
	// needs code to set user as logged in...
	public void login() {
		
    String username = usernameField.getText();
    String password = passwordField.getText();

 
    
    // Testing login login
    if (username.equals("admin") && password.equals("admin")) {
    	JOptionPane.showMessageDialog(null, "Login test successful");
    } else {
    	JOptionPane.showMessageDialog(null, "Login Incorrect, Test Successful");
   }
    
    /*
     * Login for successful login
     * Needs work to actually log the user in.
     * 
     *   boolean isAuthenticated = Authentication.isPasswordCorrect(username, password);
    if (isAuthenticated) {
        JOptionPane.showMessageDialog(this, "Login successful!");
        usernameField.setText("");
        passwordField.setText("");
    } 
    	else {
        JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    *
    *
    */
}

	

	// Create account method
	// Retrieves information from create account screen
	// checks that username and password are unique
	// if so, creates the account and opens account screen.
	
	// Needs code to return to create account panel.
	// Needs code to go to login screen when account creation successful
	public void createAccount() {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String FName = fnameField.getText();
        String LName = lnameField.getText();
        String Email = emailField.getText();
        String DoB = dobField.getText();
        
		// check that they don't already exist ( probably new class for checking for already existing username/email.)

        boolean usernameExists = Authentication.checkUsernameExists(username);
        boolean emailExists = Authentication.checkEmailExists(Email);

		if (usernameExists) {
			
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one."); //username taken
            // break, Return to create account screen
            
		} else if (emailExists) {
        	
            JOptionPane.showMessageDialog(null, "Email is already associated with an account. Please use a different one."); //email already in use
            // break, return to create account screen
            
		} else {
			
			boolean passwordValid = Authentication.isPasswordValid(password);

			if(passwordValid){
				Authentication.createAccount(FName, LName, Email, DoB, username, password); //placeholder push account info to database
            	JOptionPane.showMessageDialog(null, "Account created successfully!");
            	
            	// Account creation successful, go to login screen.
            	
			} else {
				
				JOptionPane.showMessageDialog(null, "Password is invalid.");
				// Clear password field, return to create account
			}
        }
        
	} 
	
	
	public static void forgotPassword(String usernameOrEmail) {
		
	}
}
	
	

