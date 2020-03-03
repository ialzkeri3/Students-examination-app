	import javax.swing.*; 
	import java.awt.*;
	import java.awt.event.*;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
	
public class deleteUser extends JFrame implements ActionListener{
		
		private JComboBox usersList;
		
		public deleteUser() {
		
			super("Delete a User");
			setSize(400,150);	
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setVisible(true);
			
			setLayout(new BorderLayout());


			JPanel userDe = new JPanel();

			usersList = new JComboBox(getUsers());
			usersList.setEditable(true);
			
			JLabel userSelect = new JLabel("Select User:");
			JButton delete = new JButton("Delete");
			delete.addActionListener(this);


			JPanel bottom = new JPanel();
			
			JLabel space = new JLabel("");
			
			JButton exit = new JButton("  Exit  ");
			exit.addActionListener(this);
		
			bottom.add(space);	bottom.add(exit);	bottom.add(space);

			userDe.add(userSelect); userDe.add(usersList); userDe.add(delete);
			
			add(userDe,BorderLayout.NORTH);

			add(bottom,BorderLayout.SOUTH);
			
			
			
			pack();

		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Delete")) {
			
				if(usersList.getSelectedItem() == null)
					new ExclamationMarkWindow("Error","There are no users        ");
				else {
					deleteUser((String)usersList.getSelectedItem());  // ************ update the comboBox **************
					usersList.removeAllItems();
					usersList = new JComboBox(getUsers());
	
					ImageIcon tickIcon  = new ImageIcon("tickMark.png");
					JOptionPane.showMessageDialog(this,"The student has been deleted successfuly.","Successful",JOptionPane.ERROR_MESSAGE,tickIcon);
				}
			}

			else if(e.getActionCommand().equals("  Exit  ")) {
				dispose();
			}
			
		}
		
		private String[] getUsers() {
			ArrayList<String> usersList1 = new ArrayList<String>();
			
			try {
				FileInputStream passwords = new FileInputStream("password.txt");
				Scanner sc = new Scanner(passwords);
				int line = 1;
				
				while(sc.hasNextLine()) {
					if(line%2 != 0) {
						usersList1.add(sc.nextLine());
						line++;
					}
					else {
						sc.nextLine();
						line++;
					}
				}
				sc.close();
				

			}catch(IOException ex) {
				System.exit(0);
			}	
			
			String[] usersList2 = new String[usersList1.size()];
			usersList2 = usersList1.toArray(usersList2);
			return usersList2;
		}

		private void deleteUser(String user) {
			
			
			try {
				FileInputStream usersIn = new FileInputStream("password.txt");
				Scanner sc = new Scanner(usersIn);
				
				ArrayList<String> usersList1 = new ArrayList<String>(30);
				
				while(sc.hasNextLine()) 
					usersList1.add(sc.nextLine());
				sc.close();
				
				for(int i = 0; i<(usersList1.size()-1);i++) {
					if(user.equals(usersList1.get(i))) {
						usersList1.remove(i);
						usersList1.remove(i);
					}
				}
				
				FileOutputStream usersOut = new FileOutputStream("password.txt");
				PrintWriter pw = new PrintWriter(usersOut);

				for(int i = 0; i<usersList1.size();i++) 
					pw.println(usersList1.get(i));
				pw.close();	

			}catch(IOException ex) {
				System.exit(0);
			}
			
			
		}
		
}
