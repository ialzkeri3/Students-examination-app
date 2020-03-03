import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class LoginWin extends JFrame implements ActionListener  {

	
	private JTextField userIn;
	private JPasswordField passIn;
	
	public LoginWin() {
		super("ICS-201 Online Quiz System");
		setSize(400,150);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel();
		JPanel data = new JPanel();
		
		data.setLayout(new GridLayout(2,1));

		JPanel userP = new JPanel();
		JPanel passP = new JPanel();
		
		
		userIn = new JTextField(10);
		passIn = new JPasswordField(10);
		
		JLabel user = new JLabel("          User:");
		JLabel pass = new JLabel("Password:");
		
		userP.add(user); userP.add(userIn);
		passP.add(pass); passP.add(passIn);
		data.add(userP); data.add(passP);
		
		add(data,BorderLayout.CENTER);

		
		
		add(buttons,BorderLayout.SOUTH);
		
		JButton login = new JButton("Login");
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
		if(e.getActionCommand().equals("Login")) {
			if(userIn.getText().equals("admin")&& String.valueOf(passIn.getPassword()).equals("admin"))
				new AdminLogin();
			else if(isStudent(userIn.getText(), String.valueOf(passIn.getPassword()))) 
				new StudentLogin(userIn.getText());
			else
				JOptionPane.showMessageDialog(this,"Wrong username or password.","Inane error",JOptionPane.ERROR_MESSAGE);
		}

		else if(e.getActionCommand().equals("Clear")) {
			userIn.setText("");
			passIn.setText("");
		}
		else if(e.getActionCommand().equals("Exit")) {
			dispose();
		}
	}

	private boolean isStudent(String uname, String upass) {
		try{
			FileInputStream passwords = new FileInputStream("password.txt");
			Scanner ps = new Scanner(passwords);
			
			while(ps.hasNextLine()) {
				if(uname.equals(ps.nextLine())&& upass.equals(ps.nextLine())) {
					ps.close();
					return true;			
				}
			}
			ps.close();
			return false;
		}catch(IOException e) {
			System.exit(0);
		}
		
		return false;
	
	}
}
