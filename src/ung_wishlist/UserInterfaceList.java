package ung_wishlist;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import java.awt.*;
import java.awt.event.*;

public class UserInterfaceList extends JPanel {
    private User currentUser;
    private MainFrame mainFrame;
    private DefaultTableModel tableModel;
    private JTable table;

    public UserInterfaceList(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Set the layout of the panel
        setLayout(new BorderLayout());

        // Create a DefaultTableModel to hold items in the JTable
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

        // Create and configure a JPanel to hold the JTextField and JButton
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add Item");
        addButton.setIcon(new ImageIcon("add_icon.png")); // Add an icon to the button
        addButton.addActionListener(e -> {
            // Show the dialog for adding item details
            showAddItemDialog();
        });
        inputPanel.add(addButton);

        // Create a renderer to render checkboxes in the "Purchased" column
        table.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());

        // Add MouseListener to the JTable to handle status change and open Amazon link
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (col == 1) { // Check if the click is in the "Purchased" column
                        Boolean currentValue = (Boolean) table.getValueAt(row, col);
                        table.setValueAt(!currentValue, row, col); // Toggle purchased status
                    }
                }
                else if (e.getClickCount() == 1) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (col == 0) { // Check if the click is in the "Product" column
                        String productName = (String) table.getValueAt(row, col);
                        String amazonURL = "https://www.amazon.com/s?k=" + productName;

                        try {
                            // Open the default web browser with the Amazon URL
                   
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        // Add components to the panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Custom renderer for rendering checkboxes in the "Purchased" column
    private class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckBoxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected((Boolean) value); // Set checkbox state from the table model
            return this;
        }
    }

    // Method to show the dialog for adding item details
    private void showAddItemDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns
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
            double price = Double.parseDouble(priceField.getText()); // Assuming price is entered as a double
            // Add the item to the table
            Object[] rowData = {name, false}; // Assume the purchased status is initially false
            tableModel.addRow(rowData);
            // For demonstration purposes, you can also add other details to your data model if needed.

            // Now, you need to handle the double-click event on the table to open a panel with item details
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int row = table.rowAtPoint(e.getPoint());
                        int col = table.columnAtPoint(e.getPoint());
                        if (col == 0) { // Check if the double click is in the "Product" column
                            // Get the details of the item clicked
                            String itemName = (String) table.getValueAt(row, col);
                        
                            String itemDetails = "Name: " + itemName + "\n" +
                                    "Description: " + description + "\n" +
                                    "Link: " + link + "\n" +
                                    "Price: " + price;
                            JOptionPane.showMessageDialog(null, itemDetails, "Item Details", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Interface List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UserInterfaceList(null));
        frame.pack();
        frame.setVisible(true);
    }
}

