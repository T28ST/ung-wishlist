package ung_wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GiftListView extends JPanel {
	
	// Components
	private String ListName; 	// Name of current list
	private MainFrame mainFrame; 
	private State state;
	private ResultSet list;
	
	public GiftListView(MainFrame mainFrame) {
		// Initialize

		// set default state as view
		this.mainFrame = mainFrame;
		this.state = null;
		
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
		if (list != null)
			
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
	public void openInViewMode() {
		setState(new ViewState(this)); // Assuming you have a State enum with VIEW mode
    }

	
	// OpenInEditMode (listID)
	public void openInEditMode(String listID) {
        if (list != null) { // Check if list is not null (indicating editing)
            saveList(listID); // Save the list with the given listID
        }
        setState(new EditState(this)); // Set the state to edit mode
    }
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
	
		public ViewState(GiftListView giftListView) {
		    super(giftListView);
		}

		// Define deleteItem
	    // No function in view mode
	    public void deleteItem() {
	        // No action in view mode
	        System.out.println("Cannot delete item in view mode.");
	    }

	    // Define addItem
	    // No function in view mode
	    public void addItem() {
	        // No action in view mode
	        System.out.println("Cannot add item in view mode.");
	    }

	    // Define editItem
	    // No function in view mode
	    public void editItem() {
	        // No action in view mode
	        System.out.println("Cannot edit item in view mode.");
	    }

	    // Define markPurchased
	    // User can mark an item purchased
	    public void markPurchased() {
	        // Perform action to mark item as purchased
	        System.out.println("Item marked as purchased.");
	    }
	}
	
	
	class EditState extends State {
		
		public EditState(GiftListView giftListView) {
		    super(giftListView);
		}
		// Define deleteItem
	    // Deletes item from list
	    public void deleteItem() {
	        // Perform action to delete item from list
	        System.out.println("Item deleted from list.");
	    }

	    // Define addItem
	    // Adds item to list
	    public void addItem() {
	        // Perform action to add item to list
	        System.out.println("Item added to list.");
	    }

	    // Define editItem
	    // Edit item attributes already in list
	    public void editItem() {
	        // Perform action to edit item attributes already in list
	        System.out.println("Item attributes edited.");
	    }

	    // Define markPurchased
	    // User can mark an item purchased
	    public void markPurchased() {
	        // Perform action to mark item as purchased
	        System.out.println("Item marked as purchased.");
	    }
	}
	