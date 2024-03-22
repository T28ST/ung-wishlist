package ung_wishlist;

import javax.swing.*;

public class MainApp {
	public static void main(String[] args) {
		// Initialize
		SwingUtilities.invokeLater(() -> {
			// Create and display the main frame
			MainFrame mainFrame = new MainFrame();
			mainFrame.setVisible(false);
			mainFrame.main(args);
			
		});
	}

}
