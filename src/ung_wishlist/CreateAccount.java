
package ung_wishlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
public class CreateAccount extends JPanel {
	private MainFrame mainFrame; // Allows opening other panels for main app
	
	// Password requirement
	private static final int MIN_PASS_LENGTH = 8;
	
	// Password validation error codes
	private static final int PASS_GOOD = 0;
	private static final int PASS_TOO_SHORT = 1;
	private static final int PASS_NO_DIGITS = 2;
	private static final int PASS_NO_LOWER = 3;
	private static final int PASS_NO_UPPER = 4;
	private static final int PASS_NO_SPECIAL = 5;
	
	

	public CreateAccount(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout());
		setSize(640, 480);
		
		JPanel panel = new JPanel(new GridLayout(6, 2));

		//JFrame CreateFrame = new JFrame("Create Account");
		
		JTextField emailField = new JTextField();
		JLabel emailLabel = new JLabel("Email");
		
		JTextField userField = new JTextField();
		JLabel userLabel = new JLabel("User Name");
		
		JTextField fNameField = new JTextField();	
		JLabel fNameLabel = new JLabel("First Name");
		
		JTextField lNameField = new JTextField();	
		JLabel lNameLabel = new JLabel("Last Name");
		
		JPasswordField passField = new JPasswordField(30);
		JLabel passLabel = new JLabel("Password");
		
		JButton createLogin = new JButton("Create Account");
		JButton cancelButton = new JButton("Cancel");
		
		// Add to grid layout 
		panel.add(fNameLabel); 		// First Name Label
		panel.add(fNameField); 		// First Name Text Field
		panel.add(lNameLabel); 		// Last Name Label
		panel.add(lNameField); 		// Last Name Text Field
		panel.add(userLabel);  		// User name Label
		panel.add(userField);	 	// User Name Text Field
		panel.add(emailLabel); 		// Email Label
		panel.add(emailField); 		// Email TextField
		panel.add(passLabel);		// Password Label
		panel.add(passField);		// Password Field
		panel.add(createLogin);		// Create account button
		panel.add(cancelButton);	// Cancel button
		
		createLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			
			String fName = fNameField.getText();
			String lName = lNameField.getText();
			String username = userField.getText();
			String email = emailField.getText();
			char[] passwordChars = passField.getPassword();
			String password = new String(passwordChars);
			
			saveAccount(fName, lName, email, username, password);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showLoginPanel();
			}
		});
		
		// Add gridlayout to main panel
		add(panel, BorderLayout.CENTER);

    }
	
	public void saveAccount(String fName, String lName, String email, String username, String password ) {
		
		// Check that email doesn't already belong to an account
		if (Authentication.checkEmailExists(email)) {
	    	JOptionPane.showMessageDialog(null, "Account with email already exists.");
	    	return;
		}
		
		// Check that user name isn't taken.
		if (Authentication.checkUsernameExists(username)) {
	    	JOptionPane.showMessageDialog(null, "Username already exists.");
	    	return;
		}
		
		int passResult = isValidPassword(password);
		if (passResult == PASS_GOOD) {
			Authentication.createAccount(fName, lName, email, username, password);
			JOptionPane.showMessageDialog(null, "Account created successfully.");
			mainFrame.showLoginPanel();
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalide password. " + getPassErrorMsg(passResult));
		}
		

		}

	
private static int isValidPassword(String password) {
	
	// Check that password is below 30 characters and contains a digit, lowercase letter, uppercase letter and special character.
	if (password.length() <= MIN_PASS_LENGTH) {
		return PASS_TOO_SHORT;
	}
     if (!password.matches(".*\\d.*") ) {
    	 return PASS_NO_DIGITS;
     }
     if (!password.matches(".*[a-z].*") ) {
    	 return PASS_NO_LOWER;
     }
     if (!password.matches(".*[A-Z].*") ) {
    	 return PASS_NO_UPPER;
     }
     if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") ) {
    	 return PASS_NO_SPECIAL;
     }
     
     return PASS_GOOD;
}

private static String getPassErrorMsg(int errorCode) {
	switch (errorCode) {
	case PASS_TOO_SHORT: 
		return "Password is too short.";
	case PASS_NO_DIGITS:
		return "Password must contain a number.";
	case PASS_NO_LOWER:
		return "Password must contain an lowercase letter.";
	case PASS_NO_UPPER:
		return "Password must contain an uppercase letter.";
	case PASS_NO_SPECIAL:
		return "Password must contain a special character.";
	default: 
		return "Unknown error.";
				
		
	}
}
  
}
