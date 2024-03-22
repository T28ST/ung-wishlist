package ung_wishlist;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		setTitle("Secret Shopper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);		
		

		
	}
	// Add components such as login, account creation, search etc
	// Ex: 
	// LoginPanel loginPanel = new LoginPanel();
	// add(loginPanel);
	
	public void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Login");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 150);
				frame.setLocationRelativeTo(null);
				
				LoginPanel loginPanel = new LoginPanel();
				frame.add(loginPanel);
				
				frame.setVisible(true);
				
			}
		} );
	}
}
