package ung_wishlist;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ViewSearchedList extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;
    private JPanel itemDetailsPanel;
    private JButton markAsPurchased; // Button to mark item as greyed out
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
        markAsPurchased = new JButton("Mark as Purchased");
        headerPanel.add(markAsPurchased);
        tableModel = new CustomTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Description");
        tableModel.addColumn("Link");
        tableModel.addColumn("Price");
        tableModel.addColumn("Purchased");
        // Add some test data to the table
        Object[][] testData = {
                {"Item 1", "Description 1", "Link 1", 10.99,false},
                {"Item 2", "Description 2", "Link 2", 20.49,false},
                {"Item 3", "Description 3", "Link 3", 15.79,false}
        };

        for (Object[] data : testData) {
            tableModel.addRow(data);
        }

        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        
        // Panel to display item details
        itemDetailsPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        add(itemDetailsPanel, BorderLayout.SOUTH);

        // Add double-click listener to table
    

        // Add action listener to markAsGreyButton
        markAsPurchased.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int selectedRow : selectedRows) {
                    // Update the value in the last column to true for the selected rows
                    tableModel.setValueAt(true, selectedRow, table.getColumnCount() - 1);
                }
                // Repaint the table
                table.repaint();
            }
        });
     
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (evt.getClickCount() == 2 && row != -1 && col == 0 && !greyedOutRows.contains(row)) {
                    // If double-clicked on item name and it's not greyed out, show item details
                    showItemDetails(row);
                }
            }
        });

      

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1 && greyedOutRows.contains(selectedRow)) {
                        // If a greyed-out row is selected, deselect it
                        table.clearSelection();
                    }
                }
            }
        });

        // Set custom cell renderer for table
        table.setDefaultRenderer(Object.class, getTableCellRenderer());
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

    // Custom TableCellRenderer to handle greyed-out rows
    private TableCellRenderer getTableCellRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                // Check if the value of the last column is true (indicating purchased)
                boolean isPurchased = false;
                Object lastColumnValue = table.getValueAt(row, table.getColumnCount() - 1);
    
                if (lastColumnValue instanceof Boolean) {
                    isPurchased = (Boolean) lastColumnValue;
                }
                // Set background color to grey for purchased items
                if (isPurchased) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else {
                    // Reset background color for other rows
                    c.setBackground(table.getBackground());
                }
                return c;
            }
        };
    }


    public List<String> getGreyedOutRowNames() {
        List<String> greyedOutRowNames = new ArrayList<>();
        for (int rowIndex : greyedOutRows) {
            String productName = (String) table.getValueAt(rowIndex, 0);
            greyedOutRowNames.add(productName);
        }
        return greyedOutRowNames;
    }
    class CustomTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make cells in the last column not editable if the purchase status is false
            if (column == getColumnCount() - 1) {
                Object purchaseStatus = getValueAt(row, column);
                return purchaseStatus == null || !(Boolean) purchaseStatus;
            }
            return super.isCellEditable(row, column);
        }
    }
}
