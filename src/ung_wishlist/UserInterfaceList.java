package ung_wishlist;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.*;

public class UserInterfaceList extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private java.util.List<ItemDetails> items;

    public UserInterfaceList() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Purchased");
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

        table.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());

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

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize list of items
        items = new java.util.ArrayList<>();
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
            Object[] rowData = {name, false};
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

    private class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckBoxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected((Boolean) value);
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Interface List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new UserInterfaceList());
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class ItemDetails {
    private String name;
    private String description;
    private String link;
    private double price;

    public ItemDetails(String name, String description, String link, double price) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
