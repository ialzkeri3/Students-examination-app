import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class addUser extends JFrame implements ActionListener{
	
	private JTextField userIn;
	private JPasswordField passIn;
	private JPasswordField passConfirm;
	
	public addUser() {
		super("Add New User");
		setSize(400,150);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel();
		JPanel data = new JPanel();
		
		data.setLayout(new GridLayout(3,1));

		JPanel userP = new JPanel();
		JPanel passP = new JPanel();
		JPanel passC = new JPanel();
		
		userIn = new JTextField(10);
		passIn = new JPasswordField(10);
		passConfirm = new JPasswordField(10);
		
		JLabel user = new JLabel("                 New User:");
		JLabel pass = new JLabel("                Password:");
		JLabel passConf = new JLabel("Confirm Password:");
	///////////////////////	
		userP.add(user); userP.add(userIn);
		passP.add(pass); passP.add(passIn);
		passC.add(passConf); passC.add(passConfirm);
		data.add(userP); data.add(passP); data.add(passC);
		
		add(data,BorderLayout.CENTER);

		
		
		add(buttons,BorderLayout.SOUTH);
		
		JButton login = new JButton("Add User");
		JButton clear = new JButton("Clear");
		JButton exit = new JButton("Exit");
		
		login.addActionListener(this);
		clear.addActionListener(this);
		exit.addActionListener(this);
		
		buttons.add(login);
		buttons.add(clear);
		buttons.add(exit);
		
		pack();

	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Add User")) {
			
			if(!(isExist(userIn.getText()))) {
			if(String.valueOf(passIn.getPassword()).equals(String.valueOf(passConfirm.getPassword()))) {
				try {
					FileOutputStream passwords = new FileOutputStream("password.txt",true);
					PrintWriter pw = new PrintWriter(passwords);
					pw.println(userIn.getText());
					pw.println(String.copyValueOf(passIn.getPassword()));
					pw.close();
					userIn.setText("");
					passIn.setText("");
					passConfirm.setText("");
					ImageIcon tickIcon  = new ImageIcon("tickMark.png");
					JOptionPane.showMessageDialog(this,"Student have been added successfuly.","Successful",JOptionPane.ERROR_MESSAGE,tickIcon);
				}catch(IOException ex) {
					System.exit(0);
				}	
			}
			
			else 
				JOptionPane.showMessageDialog(this,"Passwords doesn't match, try again.","ERROR",JOptionPane.ERROR_MESSAGE);
		}
			else
				JOptionPane.showMessageDialog(this,"User already exist!","ERROR",JOptionPane.ERROR_MESSAGE);
			
		}

		else if(e.getActionCommand().equals("Clear")) {
			userIn.setText("");
			passIn.setText("");
			passConfirm.setText("");
		}
		else if(e.getActionCommand().equals("Exit")) {
			dispose();
		}
		
	}
	
	private boolean isExist(String user) {
		try {
			FileInputStream passwords = new FileInputStream("password.txt");
			Scanner sc = new Scanner(passwords);
			int line = 1;
			
			while(sc.hasNextLine()) {
				if(line%2 != 0) {
					if(user.equals(sc.nextLine())) {
						sc.close();
						return true;
					}
				}
				sc.nextLine();
				line++;
			}
			sc.close();
			return false;

		}catch(IOException ex) {
			System.exit(0);
			return false;
		}	
		
	}
	
	
}
