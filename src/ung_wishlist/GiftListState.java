package ung_wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public abstract class GiftListState extends JPanel {
	
	// Components

	GiftListPanel giftList;
	
		// Constructor passes GiftListView that handles user input and displays information 
		GiftListState(GiftListPanel gitfList) {
			this.giftList = giftList;
			
		}
		
		// only available in edit mode
		abstract public void deleteItem(); // right click function deletes item
		abstract public void addItem(); 		 
		abstract public void editItem();
		// available in view and edit mode
		abstract public void markPurchased();
		

		// State object is passed to this method from other class 
		// set GiftListView's State object to the passed object
			
	// SaveList method
	
	public void saveList(String listName) {
		
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

	