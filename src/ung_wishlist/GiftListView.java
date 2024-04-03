package ung_wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GiftListView extends JPanel {
	
	// Components
	String ListName;
	
	private User currentUser;
	
	public GiftListView(User currentUser) {
		// Initialize
		this.currentUser = currentUser;
		
	}
	
	// Methods
	
	
	// method to open create list
	public void OpenList() {
	
	}
	// method to open edit list
	public void EditList() {
		
	}
	// method to view list (Guest)
	public void GuestOpen() {
		
	}
}

	abstract class State {
	
		//protected field List: GiftList
		
		
}
	
	class ViewState extends State{
		
	}
	
	// Opened from create list
	// Opened from edit list
	// Opened from view list ( User or Guest )

		// Method to save information pulled from the database into a list of GiftItems
	public List<GiftItem> retrieveGiftList(ResultSet resultSet) throws SQLException {
		List<GiftItem> giftList = new ArrayList<>();
	
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String desc = resultSet.getString("description");
			int count = resultSet.getInt("count");
			String link = resultSet.getString("link");
			boolean isPurchased = resultSet.getBoolean("purchased?");
			double price = resultSet.getDouble("price");
	
			// Create a new GiftItem object and add it to the list
			GiftItem giftItem = new GiftItem(id, name, desc, count, link, isPurchased, price);
			giftList.add(giftItem);
		}
	
		return giftList;
	}

}
