package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.sql.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.BevelBorder;

public class SearchUserLists extends JPanel {
	   private MainFrame mainFrame;
	    private User currentUser;
	    private JPanel AccountScreen;
	    private String searchedName;

	 
	    public SearchUserLists(MainFrame mainFrame, String searchedName) {
	        super();
	        this.mainFrame = mainFrame;
	        this.searchedName = searchedName;
	        
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
	
	
