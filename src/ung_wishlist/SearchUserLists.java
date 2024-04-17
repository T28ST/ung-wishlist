package ung_wishlist;

import javax.swing.*;
import java.awt.*;


public class SearchUserLists extends JPanel {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 7181351200121711213L;
	

	 
	    public SearchUserLists(MainFrame mainFrame, String searchedName) {
	        super();
	        
	        // Use the searchedName here as needed
	        System.out.println("Searched Name: " + searchedName);
	        
	        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
			
			JLabel listLabel = new JLabel(searchedName + "list");
			listLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			JLabel noListsLabel = new JLabel("");
			noListsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			noListsLabel.setEnabled(false);
		
			JList<String> list = new JList<>();
			
			
			if (list.getModel().getSize() == 0) {
				noListsLabel.setText("You don't have a list!");
				noListsLabel.setEnabled(true);
			}
			inputPanel.add(listLabel);
			inputPanel.add(listLabel);
			add(inputPanel);
	    }
	}
	
	

