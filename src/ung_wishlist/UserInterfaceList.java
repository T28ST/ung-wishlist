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
    private List<ItemDetails> items;
    private MainFrame mainFrame;

    public UserInterfaceList(MainFrame mainFrame, String listName, User currentUser) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        tableModel = new CustomTableModel();
        tableModel.addColumn("Product");

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        table.setBackground(Color.gray);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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

        // Initialize list of items
        items = new ArrayList<>();
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

            // Add the item to the list and table model
            items.add(new ItemDetails(name, description, link, price));
            Object[] rowData = {name};
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

    class CustomTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Make all cells non-editable
            return false;
        }
    }


}
