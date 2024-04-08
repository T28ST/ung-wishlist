package ung_wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class GiftListView extends JPanel {
	
	// Components
	private String ListName; 	// Name of current list
	private MainFrame mainFrame; 
	private State state;
	private ResultSet list;
	private User currentUser;
	
	
	public GiftListView(MainFrame mainFrame, User currentUser) {
		// Initialize
		setSize(640, 480);

		// set default state as view
		this.mainFrame = mainFrame;
		this.state = null;
		this.currentUser = currentUser;
		
		// UI here
		

		
	
		
	}

	// SetState method
	
	public void setState(State newState) {
        this.state = newState;
        
	}
		// State object is passed to this method from other class 
		// set GiftListView's State object to the passed object
			
	// SaveList method
	
	public void saveList(String listName) {
		this.list = Authentication.createList(listName);
	}
		// save list with name/id sent from account screen for display or editing
		// get list from DB as ResultSet (call method in Authentication that returns ResultList)
	
		/* public List<GiftItem> retrieveGiftList(ResultSet resultSet) throws SQLException {
		List<GiftItem> giftList = new ArrayList<>();
	
		while (resultSet.next()) {
			
			
			String name = resultSet.getString("name");
			boolean isPurchased = resultSet.getBoolean("purchased?");
			
			// Create a new GiftItem object and add it to the list
			GiftItem giftItem = new GiftItem(id, name, desc, count, link, isPurchased, price);
			giftList.add(giftItem);
		}*/
		// cycle through ResultSet, save to array
	
	// OpenInViewMode
		// SetState(view)
	
	// OpenInEditMode (listID)
		// if list is not null (editing list)
			// SaveList(listID)
		// SetState(edit)
	
	// UI Methods
		// UI methods delegate execution to the active state


}

// Base state class declares methods that all concrete states should implement
	abstract class State {
		private GiftListView currentView;
		
		// Constructor passes GiftListView that handles user input and displays information 
		public State(GiftListView currentView) {
			this.currentView = currentView;
			
		}
		
		// only available in edit mode
		abstract public void deleteItem(); // right click function deletes item
		abstract public void addItem(); 		 
		abstract public void editItem();
		// available in view and edit mode
		abstract public void markPurchased();
		
}
	
	class ViewState extends State {
	
		// Define deleteItem
			// no function in view mode
		
		// Define addItem
			// no function in view mode
		
		// Define editItem
			// no function in view mode
		
		// Define markPurchased();
			// User can mark an item purchased
	}
	
	class EditState extends State {
		
		// Define deleteItem
			// deletes item from list
	
		// Define addItem
			// adds item to list
		
		// Define editItem
			// edit item attributes already in list
		
		// Define markPurchased();
			// User can mark an item purchased
	}
	