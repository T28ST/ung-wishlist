package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.sql.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.BevelBorder;


public class LoginPanel extends JPanel{
	private MainFrame mainFrame;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton forgotPasswordButton;
	private JButton createAccountButton;


	
	
	public LoginPanel(MainFrame mainFrame) { 
		this.mainFrame = mainFrame;
		JPanel panel = new JPanel(new GridLayout(0,1));

		JLabel usernameLabel = new JLabel("Username:");
		usernameField = new JTextField();
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JTextField();
		
		JLabel blankLabel= new JLabel("");

		
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String username = usernameField.getText();
        		String password = passwordField.getText();
        		
        		login(username, password);

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
				mainFrame.showCreateAccountPanel();
        	}
        });
       setLayout(new GridLayout(1, 2, 0, 0));
       
       JPanel logo = new JPanel();
       logo.setBorder(null);
       add(logo);
       logo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
       
       JLabel Icon = DefaultComponentFactory.getInstance().createTitle("");
       Icon.setIcon(new ImageIcon("C:\\Git\\ung-wishlist\\img\\gift.png"));
       logo.add(Icon);
       
       JLabel appName = DefaultComponentFactory.getInstance().createLabel("Secret Shopper");
       appName.setVerticalAlignment(SwingConstants.BOTTOM);
       appName.setLabelFor(Icon);
       logo.add(appName);
	  
       panel.add(usernameLabel);
       panel.add(usernameField);
       panel.add(passwordLabel);
       panel.add(passwordField);
       panel.add(blankLabel);
       panel.add(loginButton);
       panel.add(createAccountButton);
       panel.add(forgotPasswordButton);
       
       add(panel);

	}

	public void login(String username, String password) {
		
		// Checks login, returns null if password is incorrect or no login
		User currentUser = Authentication.CheckLogin(username, password);
		
		if (currentUser != null ) {
			JOptionPane.showMessageDialog(this, "Weclome back " + currentUser.getFirstName() + "!");
			mainFrame.showAccountScreen(currentUser);
			
		} else {
			JOptionPane.showMessageDialog(this, "Invalid username or password");
		}
	}

}

