package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.sql.SQLException;


public class LoginPanel extends JPanel{
	private MainFrame mainFrame;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton forgotPasswordButton;
	private JButton createAccountButton;


	
	
	public LoginPanel(MainFrame mainFrame) { 
		this.mainFrame = mainFrame;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

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
        
        // Forgot password button with action event.
	   forgotPasswordButton = new JButton("Forgot Password");
       forgotPasswordButton.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   // Placeholder
    		   JOptionPane.showMessageDialog(null, "Don't forget next time silly");
    	   }
       });
	    
	    createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				// Switch to Create Account Panel
				mainFrame.showAccountPanel();
        	}
        });
	  
       add(usernameLabel);
       add(usernameField);
       add(passwordLabel);
       add(passwordField);
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

    
    // Testing login logic
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
}

