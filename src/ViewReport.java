import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ViewReport extends JFrame implements ActionListener{

	public ViewReport() {
		super("Student Report");
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		setLayout(new BorderLayout());
		
		JTextArea data = new JTextArea(10,20);
		data.setText(getResults());
		data.setEditable(false);
		
		JPanel southRegion = new JPanel();
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(this);
		
		southRegion.setLayout(new GridLayout(1,5));
		southRegion.add(new JLabel("       "));
		southRegion.add(new JLabel("       "));
		southRegion.add(exit);
		southRegion.add(new JLabel("       "));
		southRegion.add(new JLabel("       "));
		
		add(southRegion,BorderLayout.SOUTH);
		add(data,BorderLayout.CENTER);
		
		
		pack();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Exit"))
			dispose();
	}
	
	private String getResults(){
		
		try {
			FileInputStream fin = new FileInputStream("studentsReport.txt");
			Scanner sc = new Scanner(fin);
			
			String data = "";

			while(sc.hasNextLine())
				data += sc.nextLine()+"\n";
			
			sc.close();
			
			return data;
			
		}catch(IOException e) {
			System.exit(0);
			return null;
		}
	}

}
