package ung_wishlist;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.CardLayout;

// Class purpose: 
// Account options with gifts list here and buttons to create a new one
// Edit details about accounts


public class AccountScreen extends JPanel{
	private MainFrame mainFrame;  
	private User currentUser; 
	
	// Passed currentUser class from LoginPanel stores
	public AccountScreen(MainFrame mainFrame, User currentUser) {
		setSize(640, 480);
		this.mainFrame = mainFrame;
		this.currentUser = currentUser;
		
		// UI here 
		
		// Split layout
		JPanel leftPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new BorderLayout());
		setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(400);
		splitPane.setResizeWeight(1.0);
		add(splitPane);
		
		
		// Left panel components
		// User info displayed top half of left side
		// with logout button
		JPanel userInfoPanel = new JPanel(new GridLayout(4,1));
		JLabel nameLabel = new JLabel(currentUser.getFirstName() + " " + currentUser.getLastName());
		JLabel usernameLabel = new JLabel(currentUser.getUsername());
		JLabel emailLabel = new JLabel(currentUser.getEmail());
		JButton logoutButton = new JButton("Log Out");
		userInfoPanel.add(logoutButton);
		userInfoPanel.add(nameLabel);
		userInfoPanel.add(usernameLabel);
		userInfoPanel.add(emailLabel);
		
		leftPanel.add(new JScrollPane(userInfoPanel), BorderLayout.NORTH);
		
		// Buttons to add, edit and delete lists.
		JPanel buttonPanel = new JPanel(new GridLayout(3,1));
		JButton createListButton = new JButton("Create List");
		JButton editListButton = new JButton("Edit List");
		JButton deleteListButton = new JButton("Delete List");
		
		
		buttonPanel.add(createListButton);
		buttonPanel.add(editListButton);
		buttonPanel.add(deleteListButton);
		
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		JPanel searchPanel = new JPanel();
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[] {150, 200, 150};
		gbl_searchPanel.rowHeights = new int[] {30, 30, 0, 0, 30, 30, 0, 0, 0, 30, 30, 30, 30, 30};
		gbl_searchPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_searchPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0};
		searchPanel.setLayout(gbl_searchPanel);
		
		leftPanel.add(searchPanel);
		
		JLabel lblNewLabel = new JLabel("User Search");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		searchPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JTextField searchField = new JTextField();
		
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.fill = GridBagConstraints.BOTH;
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.gridx = 1;
		gbc_searchField.gridy = 6;
		searchPanel.add(searchField, gbc_searchField);
		JButton searchButton = new JButton("Search");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 0, 5);
		gbc_searchButton.fill = GridBagConstraints.BOTH;
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 8;
		searchPanel.add(searchButton, gbc_searchButton);
		
		// Right panel components

		
		
	}
	
	
	
	// Methods
	
		// Logout method
			// Clear store user information in User class 
			// Return to Login screen
	
		// Change Account Details (pass, user, name, email)
	
	
}

