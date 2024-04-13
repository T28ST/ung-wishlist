package ung_wishlist;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

// Class purpose: 
// Account options with gifts list here and buttons to create a new one
// Edit details about accounts


public class AccountScreen extends JPanel{
	private MainFrame mainFrame;  
	private User currentUser; 
	private String searchedName;
	private boolean viewMode; // Boolean to determine edit list action
	User searchedUser;

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
		viewMode = false;
		
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
		
		// Pulls lists for the current user using their ID and assigning it to this list
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
				 String listName = list.getSelectedValue();
				 
                 if (listName != null) { // Check if an item is selected
                	 if (!viewMode) {
                		 mainFrame.showGiftEditList(listName, currentUser); 
                	 } else {
                		 mainFrame.showSearchedList(listName, searchedName);
                	 }
     			}
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
		        searchedName = searchField.getText();
		        viewMode = true; // 
		        // Check if user exists in DB
		        if( Authentication.checkUsernameExists(searchedName)) {
		        	// Update list label with searched user's name
		            listLabel.setText(searchedName + " Lists");
		            // Assign new User object as the searched user
		            searchedUser = Authentication.getSearchedUser(searchedName);
		            // Get that user's list.
		            Authentication.getUserLists(searchedUser.getId(), list);
		            // Check if this user has any lists
		            if (list.getModel().getSize() == 0) {
		            	// If not list, reflect with noListsLabel
		    			noListsLabel.setText(searchedName + " has no lists!");
		    			noListsLabel.setEnabled(true);
		    		} else {
		    			// otherwise, turn label off.
		    			noListsLabel.setEnabled(false);
		    		}
		            
		            createListButton.setEnabled(false); // Disable create list button
		            editListButton.setText("View List"); // Edit list button becomes View list button,
		            deleteListButton.setEnabled(false); // Disable edit list button
		            backButton.setEnabled(true); // Enable back button to leave search.
		            
		            // Adds double click function to list.
		            //(Should add to constructor with same function as view list for consistency.)
		            list.addMouseListener(new MouseAdapter() {
		                @Override
		                public void mouseClicked(MouseEvent e) {
		                    if (e.getClickCount() == 2) { // Double-click detected
		                        String listName = list.getSelectedValue();
		                        if (listName != null) { // Check if list item is selected
		                            mainFrame.showSearchedList(listName, searchedName);
		                        }
		                    }
		                }
		            });


		        } else {
		        	JOptionPane.showMessageDialog(null, "User does not exist!");

		        }
		     }
		
		});
		
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	viewMode = false;
		    	searchedUser.logout();
		        // Clear the search field
		        searchField.setText("");
		        // Restore buttons.
		        createListButton.setEnabled(true);
		        editListButton.setText("Edit List");
		        deleteListButton.setEnabled(true);
		        // Hide back button
		        backButton.setEnabled(false);
		        // Refresh right panel with the original user's data
		        Authentication.getUserLists(currentUser.getId(), list);
		        if (list.getModel().getSize() == 0) {
					noListsLabel.setText("You don't have a list!");
					noListsLabel.setEnabled(true);
				}
		    }
		});
	}
	
	
}

