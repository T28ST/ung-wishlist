package ung_wishlist;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

// Class purpose: 
// Account options with gifts list here and buttons to create a new one
// Edit details about accounts


public class AccountScreen extends JPanel{
	private MainFrame mainFrame;  
	private User currentUser; 
	private String searchedName;
	

	public String getSearchedName() {
		return searchedName;
	}

	public void setSearchedName(String searchedName) {
		this.searchedName = searchedName;
	}
	
	ResultSet resultSet = null;
	
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
		
		// User Search 
		
		JLabel searchLabel = new JLabel("User Search");
		GridBagConstraints gbc_searchLabel = new GridBagConstraints();
		gbc_searchLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchLabel.gridx = 1;
		gbc_searchLabel.gridy = 3;
		searchPanel.add(searchLabel, gbc_searchLabel);
		
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


		
		JLabel listLabel = new JLabel("My Lists");
		listLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(listLabel, BorderLayout.NORTH);
		
		JLabel noListsLabel = new JLabel("");
		noListsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noListsLabel.setEnabled(false);
		rightPanel.add(noListsLabel, BorderLayout.SOUTH);
		JButton backButton = new JButton("Back");
		backButton.setEnabled(false); // Initially disabled until a search is performed
		rightPanel.add(backButton, BorderLayout.SOUTH);
		JList<String> list = new JList<>();
		Authentication.getUserLists(currentUser.getId(), list);
		
		if (list.getModel().getSize() == 0) {
			noListsLabel.setText("You don't have a list!");
			noListsLabel.setEnabled(true);
		}
		
		rightPanel.add(new JScrollPane(list), BorderLayout.CENTER);
		

		
		// Button actions
		
		// Logout user
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.logout();
				mainFrame.showLoginPanel();
			}
		});
		
		// Create list
		createListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter name of list: ");
				if (Authentication.checkListExists(name)) {
					JOptionPane.showMessageDialog(null, "List already exits!");
				} else {
					Authentication.createList(currentUser.getId(), name);
					Authentication.getUserLists(currentUser.getId(), list);
				}
			}
		});
		
		// Edit list
		editListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showgiftEditList();
			}
		});
		// Delete List
		deleteListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String listName = list.getSelectedValue();
				if (listName != null) {
					Authentication.deleteList(currentUser.getId(), listName);
					Authentication.getUserLists(currentUser.getId(), list);
				} else {
					JOptionPane.showMessageDialog(null, "No list selected.");
				}
				
			}
		});		
		
		// Search Users
		searchButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchedName = searchField.getText();
		        // Update the right panel with the searched person's name
	            listLabel.setText("Lists for " + searchedName);
	            // Generating a random list name for demonstration purposes
	            String randomListName = "Random List"; // Replace with your actual logic to generate a random list name
	            // Add the random list name to the JList
	            DefaultListModel<String> model = new DefaultListModel<>();
	            model.addElement(randomListName);
	            list.setModel(model);
	            // Clear the noListsLabel if there are lists available
	            noListsLabel.setText("");
	            noListsLabel.setEnabled(false);
	            createListButton.setEnabled(false);
	            editListButton.setEnabled(false);
	            deleteListButton.setEnabled(false);
	            // Show back button
	            backButton.setEnabled(true);
	            list.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    if (e.getClickCount() == 2) { // Double-click detected
	                        int index = list.getSelectedIndex();
	                        if (index != -1) { // Check if an item is selected
	                            String selectedListName = list.getModel().getElementAt(index);
	                            // Now you have the selected list name, you can pass it to the MainFrame
	                            mainFrame.showgiftEditList(selectedListName,searchedName);
	                        }
	                    }
	                }
	            });
		    
		    
		    
		    
		    }
		
		});
		
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Clear the search field
		        searchField.setText("");
		        // Enable edit buttons
		        createListButton.setEnabled(true);
		        editListButton.setEnabled(true);
		        deleteListButton.setEnabled(true);
		        // Hide back button
		        backButton.setEnabled(false);
		        // Refresh right panel with the original user's data
		        // You may need to implement a method to reload the original user's data
		        // and update the right panel accordingly
		    }
		});
	}
	
	
}

