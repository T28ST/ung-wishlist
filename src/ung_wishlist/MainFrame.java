package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {
	private JFrame frame;
	private JPanel loginPanel;
	private JPanel createAccountPanel;
	
	public MainFrame() {
		frame = new JFrame("Secret Shopper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new CardLayout());
		frame.setLocationRelativeTo(null);
		
		loginPanel = new LoginPanel(this);
		createAccountPanel = new CreateAccount(this);
		
		frame.add(loginPanel, "loginPanel");
		frame.add(createAccountPanel, "createAccountPanel");
		
		showLoginPanel();

		frame.setVisible(true);
	}
	
	public void showLoginPanel() {
		CardLayout cardLayout = (CardLayout)(frame.getContentPane().getLayout());
		cardLayout.show(frame.getContentPane(), "loginPanel");
	}
	
	public void showAccountPanel() {
		CardLayout cardLayout = (CardLayout)(frame.getContentPane().getLayout());
		cardLayout.show(frame.getContentPane(), "createAccountPanel");
	}
		
		
	
	// Add components such as login, account creation, search etc
	// Ex: 
	// LoginPanel loginPanel = new LoginPanel();
	// add(loginPanel);
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
		
	}
}
