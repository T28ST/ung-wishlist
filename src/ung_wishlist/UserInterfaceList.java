package ung_wishlist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UserInterfaceList extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;
    private ArrayList<ItemDetails> items;
    private MainFrame mainFrame;
    private long listID;
    private User currentUser;

    public UserInterfaceList(MainFrame mainFrame, String listName, User currentUser) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        tableModel = new CustomTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Description");
        tableModel.addColumn("Link");
        tableModel.addColumn("Price");
        tableModel.addColumn("Purchased");
        
        listID = Authentication.getListID(currentUser.getId(), listName);
        this.currentUser = currentUser;

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        table.setBackground(Color.gray);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton backButton = new JButton("Go back.");
        backButton.addActionListener(e -> goBack());
        inputPanel.add(backButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedRow());
        inputPanel.add(deleteButton);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> showAddItemDialog());
        inputPanel.add(addButton);

        JButton editButton = new JButton("Edit Item");
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                showEditItemDialog(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an item to edit.", "No Item Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        inputPanel.add(editButton);

        JButton saveButton = new JButton("Save List");
        saveButton.addActionListener(e -> {
            saveList();
        });

        inputPanel.add(saveButton);

        add(inputPanel, BorderLayout.NORTH);

        // Add double-click listener to table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (col == 0) {
                        // Handle double click on product
                        showItemDetails(row);
                    }
                }
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        items = Authentication.getListGifts(listID);

        for (ItemDetails item : items) {
            Object[] rowData = new Object[5];
            rowData[0] = item.getName();            // Product
            rowData[1] = item.getDescription(); // Description
            rowData[2] = item.getLink();               // Link
            rowData[3] = item.getPrice();             // Price
            rowData[4] = item.getPurchased();     // Purchased

            tableModel.addRow(rowData);
        }

    }

    private void deleteSelectedRow() {
         int selectedRow = table.getSelectedRow();
         if (selectedRow != -1) {
             // Remove the selected row from the table model and the items list
             tableModel.removeRow(selectedRow);
             ItemDetails selectedItem = items.remove(selectedRow);
         
             List<Long> ids = new ArrayList<>();
             ids.add(selectedItem.getID());
             Authentication.deleteGifts(ids);
         } else {
             JOptionPane.showMessageDialog(null, "Please select a row to remove.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
         }
    }

    private void showAddItemDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField linkField = new JTextField();
        JTextField priceField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Link:"));
        panel.add(linkField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Item",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String link = linkField.getText();
            double price = Double.parseDouble(priceField.getText());
            boolean purchased = false;

            // Add the item to the list and table model
            ItemDetails newItem = new ItemDetails((long) 0, name, description, link, price, purchased);
            items.add(newItem);
            Object[] rowData = {newItem.getName(), newItem.getDescription(), newItem.getLink(), newItem.getPrice(), newItem.getPurchased()};
            tableModel.addRow(rowData);
        }
    }

    private void showEditItemDialog(int rowIndex) {
        ItemDetails selectedItem = items.get(rowIndex);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField(selectedItem.getName());
        JTextField descriptionField = new JTextField(selectedItem.getDescription());
        JTextField linkField = new JTextField(selectedItem.getLink());
        JTextField priceField = new JTextField(String.valueOf(selectedItem.getPrice()));

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Link:"));
        panel.add(linkField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Item",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            selectedItem.setName(nameField.getText());
            selectedItem.setDescription(descriptionField.getText());
            selectedItem.setLink(linkField.getText());
            selectedItem.setPrice(Double.parseDouble(priceField.getText()));

            // Update the values in the table model
            table.setValueAt(selectedItem.getName(), rowIndex, 0);
        }
    }

    private void showItemDetails(int rowIndex) {
        ItemDetails selectedItem = items.get(rowIndex);
        String itemDetails = "Name: " + selectedItem.getName() + "\n" +
                "Description: " + selectedItem.getDescription() + "\n" +
                "Link: " + selectedItem.getLink() + "\n" +
                "Price: " + selectedItem.getPrice();
        JOptionPane.showMessageDialog(null, itemDetails, "Item Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveList() {
        Authentication.saveListGifts(listID, items);
        JOptionPane.showMessageDialog(null, "List saved!");
    }

    private void goBack() {
        String title = "Confirmation";
        String message = "Are you sure? Leaving without saving will lose progress."; 
        int optionType = JOptionPane.YES_NO_OPTION;
        int messageType = JOptionPane.WARNING_MESSAGE;
        
        int choice = JOptionPane.showConfirmDialog(null, message, title, optionType, messageType);
        
        if (choice == JOptionPane.YES_OPTION) {
            System.out.println("Return to account screen.");
            mainFrame.showAccountScreen(currentUser);
        } else {
            System.out.println("Cancel back button.");
            return;
        }
        
    }

    class CustomTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}

