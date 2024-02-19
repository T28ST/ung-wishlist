package ung_wishlist;
import javax.swing.*;


public class Test {
	
	    public static void main(String[] args)
    {
      
        JFrame frame = new JFrame();
        ArrayList<String> items = new ArrayList<String>();
        
        @SuppressWarnings("unchecked")
        JList<String> list = new JList<String>(items.toArray(new String[items.size()]));
     
        JTextField button= new JTextField(" Insert product to add to list");
 
        // x axis, y axis, width, height
        button.setBounds(150, 200, 220, 50);
        list.setBounds(150, 100, 220, 200);
        
        // adding button in JFrame
       
        // 400 width and 500 height
        frame.setSize(500, 600);
        
        
        JButton okButton = new JButton("add");
        okButton.setBounds(150, 300, 220, 50);
        okButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
            items.add(button.getText());
        	
          
           }
        });
        // using no layout managers
        frame.setLayout(null);
        frame.add(button);
        frame.add(okButton);
        frame.add(list);
        // making the frame visible
        frame.setVisible(true);
    }
	

}
