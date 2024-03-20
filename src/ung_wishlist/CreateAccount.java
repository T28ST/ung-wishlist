package Rickey.two;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AccountLoginandCreation {
	
	public static void main(String[] args) throws SQLException {
			JFrame CreateFrame = new JFrame("Create Account");
			JTextField IDField = new JTextField("",25);
			IDField.setBounds(50,80,150,20);	
			JLabel IDLabel = new JLabel("email");
			IDLabel.setBounds(50,60,150,20);
			JTextField UserField = new JTextField("",25);
			UserField.setBounds(50,120,150,20);
			JLabel UserLabel = new JLabel("User Name");
			UserLabel.setBounds(50,100,150,20);
			JTextField FNameField = new JTextField("",25);
			FNameField.setBounds(50,160,150,20);
			JLabel FNameLabel = new JLabel("Full name");
			FNameLabel.setBounds(50,140,150,20);
			JTextField LNameField = new JTextField("",25);
			LNameField.setBounds(50,200,150,20);
			JLabel LNameLabel = new JLabel("password");
			LNameLabel.setBounds(50,180,150,20);
			JButton CreateLogin = new JButton("Create Account");
			CreateLogin.setBounds(50,220,150,20);
			
			CreateLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				CreateFrame.setVisible(false);
				
				String IDFieldText = IDField.getText();
				int IDint= Integer.parseInt(IDFieldText);
				String UserFieldText = UserField.getText();
				String FNameFieldText = FNameField.getText();
				String LNameFieldText = LNameField.getText();
				//you can use the UserField text for when you need to recieve the values
				}
			});
			
			
			
			
			
			
			CreateFrame.add(CreateLogin);
			CreateFrame.add(LNameLabel);
			CreateFrame.add(FNameLabel);
			CreateFrame.add(UserLabel);
			CreateFrame.add(IDLabel);
			CreateFrame.add(LNameField);
			CreateFrame.add(FNameField);
			CreateFrame.add(UserField);
			CreateFrame.add(IDField);
			CreateFrame.setSize(400,500);
			CreateFrame.setLayout(null);
			CreateFrame.setVisible(true);
			
			
		}
	
	}

