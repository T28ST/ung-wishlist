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
	


}

	