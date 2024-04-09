package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainFrame {
	private JFrame frame;
	private JPanel loginPanel;
	private JPanel createAccountPanel;
	private JPanel accountPanel;
	private ResultSet currentUser;
	
	public MainFrame() {
		frame = new JFrame("Secret Shopper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.getContentPane().setLayout(new CardLayout());
		frame.setLocationRelativeTo(null);
		
		loginPanel = new LoginPanel(this);
		createAccountPanel = new CreateAccount(this);

		
		frame.getContentPane().add(loginPanel, "loginPanel");
		frame.getContentPane().add(createAccountPanel, "createAccountPanel");

		// searchPanel
		// giftListPanel
		
		showLoginPanel();

		frame.setVisible(true);
	}
	
	public void showLoginPanel() {
		CardLayout cardLayout = (CardLayout)(frame.getContentPane().getLayout());
		cardLayout.show(frame.getContentPane(), "loginPanel");
	}
	
	public void showCreateAccountPanel() {
		CardLayout cardLayout = (CardLayout)(frame.getContentPane().getLayout());
		cardLayout.show(frame.getContentPane(), "createAccountPanel");
	}
		
	
	// showAccountScreen
	public void showAccountScreen(User currentUser) {
		accountPanel = new AccountScreen(this, currentUser);
		frame.getContentPane().add(accountPanel, "accountPanel");
		CardLayout cardLayout = (CardLayout)(frame.getContentPane().getLayout());
		cardLayout.show(frame.getContentPane(), "accountPanel");
	}
	
	// showSearchScreen
	
	// showGiftListView
	
	
	// Set Current User
	// sets the user when login is successful
	public void setCurrentUser(ResultSet user) {
		currentUser = user;
	}
	
	// Get User Information
	// gets the information for user that is logged in
	public ResultSet getUserInfo() {
		return currentUser;
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
		
	}
}
