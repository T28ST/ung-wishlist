package ung_wishlist;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewSearchedList extends JPanel {
    
	private DefaultTableModel tableModel;
    private JTable table;
    private JPanel itemDetailsPanel;
    private JButton markAsGreyButton; // Button to mark item as greyed out
    private List<Integer> greyedOutRows; // List to keep track of greyed out rows
    private MainFrame mainFrame;
    
    public ViewSearchedList(MainFrame mainFrame, String listName, String searchedUser) {
        setLayout(new BorderLayout());
        this.mainFrame = mainFrame;
        greyedOutRows = new ArrayList<>();

        // Panel to display list name
        JPanel headerPanel = new JPanel();
        JLabel listLabel = new JLabel("List: " + listName);
        headerPanel.add(listLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Button to mark item as greyed out
        markAsGreyButton = new JButton("Mark as Grey");
        headerPanel.add(markAsGreyButton);

        // Table to display list items
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Description");
        tableModel.addColumn("Link");
        tableModel.addColumn("Price");

        // Add some test data to the table
        Object[][] testData = {
            {"Item 1", "Description 1", "Link 1", 10.99},
            {"Item 2", "Description 2", "Link 2", 20.49},
            {"Item 3", "Description 3", "Link 3", 15.79}
        };

        for (Object[] data : testData) {
            tableModel.addRow(data);
        }

        table = new JTable(tableModel);
        table.setEnabled(false); // Make the table read-only

        // Panel to display item details
        itemDetailsPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        add(itemDetailsPanel, BorderLayout.SOUTH);

        // Add double-click listener to table
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (evt.getClickCount() == 2 && row != -1 && col == 0) {
                    // If double-clicked on item name, show item details
                    showItemDetails(row);
                }
            }
        });
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1 && !greyedOutRows.contains(selectedRow)) {
                        String itemName = (String) table.getValueAt(selectedRow, 0);
                        System.out.println(itemName);
                    }
                }
            }
        });
        // Add action listener to markAsGreyButton
        markAsGreyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1 && !greyedOutRows.contains(selectedRow)) {
                    // Change the color of the selected row to grey
                    table.setSelectionBackground(Color.LIGHT_GRAY);
                    // Disable selection for the selected row
                    table.setRowSelectionAllowed(false);
                    // Add the selected row to the list of greyed out rows
                    greyedOutRows.add(selectedRow);
                    table.repaint();
                }
            }
        });
    }

    private void showItemDetails(int rowIndex) {
        // Clear previous item details
        itemDetailsPanel.removeAll();
        itemDetailsPanel.revalidate();
        itemDetailsPanel.repaint();

        // Get data from the table model
        String productName = (String) table.getValueAt(rowIndex, 0);
        String description = (String) table.getValueAt(rowIndex, 1);
        String link = (String) table.getValueAt(rowIndex, 2);
        double price = (double) table.getValueAt(rowIndex, 3);

        // Panel to display item details
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(new JLabel(productName));
        detailsPanel.add(new JLabel("Description:"));
        detailsPanel.add(new JLabel(description));
        detailsPanel.add(new JLabel("Link:"));
        detailsPanel.add(new JLabel(link));
        detailsPanel.add(new JLabel("Price:"));
        detailsPanel.add(new JLabel(String.valueOf(price)));

        itemDetailsPanel.add(detailsPanel, BorderLayout.NORTH);
        itemDetailsPanel.revalidate();
        itemDetailsPanel.repaint();
    }
}
