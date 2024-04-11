package ung_wishlist;

import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class GiftListPanel extends JPanel{
	private GiftListState state;
	private String ListName; 	// Name of current list
	private MainFrame mainFrame; 
	private ResultSet list;
	private User currentUser;
	
	public GiftListPanel(MainFrame mainFrame ,User currentUser, String listName) {
		this.state = new GiftListViewState(this);
		this.currentUser = currentUser;
		this.mainFrame = mainFrame;
		long listID;
		
		setSize(640, 480);
		
		JLabel noGiftsLabel = new JLabel("");
		
		// Gift Table model
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Description");
		model.addColumn("Price");
		model.addColumn("URL");
		model.addColumn("Purchased?");
		
		JTable table = new JTable(model);
		JScrollPane scrollPanel = new JScrollPane(table);
		
		Authentication.getListGifts(currentUser.getId(), model);
		
		if (model.getRowCount() == 0) {
			noGiftsLabel.setText("You don't have any gifts!");
			noGiftsLabel.setEnabled(true);
		}
		
	}
	
	public void changeState(GiftListState state) {
		this.state = state;
	}
	
	public GiftListState getState() {
		return state;
	}

}
