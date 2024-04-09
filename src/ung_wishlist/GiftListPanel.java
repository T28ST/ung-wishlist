package ung_wishlist;

import java.sql.ResultSet;

public class GiftListPanel {
	private GiftListState state;
	private String ListName; 	// Name of current list
	private MainFrame mainFrame; 
	private ResultSet list;
	private User currentUser;
	
	public GiftListPanel() {
		
	}
	
	public void changeState(GiftListState state) {
		this.state = state;
	}
	
	public GiftListState getState() {
		return state;
	}

}
