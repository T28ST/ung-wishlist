package ung_wishlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class UserInterfaceList extends JPanel {
    private User currentUser;

    public UserInterfaceList(User currentUser) {
        this.currentUser = currentUser;

        // Set the layout of the panel
        setLayout(new BorderLayout());

        // Create a DefaultListModel to hold items in the JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        list.setBackground(Color.gray);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(new Font("Arial", Font.PLAIN, 14)); // Change the font here
                return label;
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && !list.isSelectionEmpty()) { // Check for right-click
                    // Get the index of the item under the mouse pointer
                    int index = list.locationToIndex(e.getPoint());
                    // Show a confirmation dialog before deleting the item
                    int option = JOptionPane.showConfirmDialog(UserInterfaceList.this, "Delete this item?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Remove the item from the JList
                        listModel.remove(index);
                    }
                } else if (e.getClickCount() == 2 && !list.isSelectionEmpty()) { // Check for double-click
                    // Get the selected value from the JList
                    String selectedValue = list.getSelectedValue();
                    System.out.println("Double-clicked Value: " + selectedValue);
                    String amazonURL = "https://www.amazon.com/s?k=" + selectedValue;

                    try {
                        // Open the default web browser with the Amazon URL
                        Desktop.getDesktop().browse(new URI(amazonURL));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Create and configure a JPanel to hold the JTextField and JButton
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField textField = new JTextField("Insert product to add to list", 20);

        JButton addButton = new JButton("Add");
        addButton.setIcon(new ImageIcon("add_icon.png")); // Add an icon to the button
        addButton.addActionListener(e -> {
            // Add the text from the JTextField to the JList
            String newItem = textField.getText();
            if (!newItem.isEmpty()) {
                listModel.addElement(newItem);
                textField.setText(""); // Clear the text field
            }
        });
        textField.addActionListener(e -> {
            // Trigger the same action as clicking the "Add" button when Enter is pressed
            addButton.doClick();
        });
        inputPanel.add(textField);
        inputPanel.add(addButton);

        // Add components to the panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
