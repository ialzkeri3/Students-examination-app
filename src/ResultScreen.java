import javax.swing.*;
//import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultScreen extends JFrame implements ActionListener{

	private JTextField id;
	private JTextField correct;
	private JTextField inCorrect;
	private JTextField percent;
	
	public ResultScreen(String ID, int correctAnswers, int total) {

		super("Test Result");
		setSize(400,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		setLayout(new BorderLayout());
		
		JLabel idL = new JLabel(" Student ID ");
		JLabel correctL = new JLabel(" # of Corrects Answers ");
		JLabel inCorrectL = new JLabel(" # of Incorrects Answers ");
		JLabel percentL = new JLabel(" % of Correct Answers ");

		id = new JTextField(""+ID);
		id.setEditable(false);
		
		correct = new JTextField(""+correctAnswers);
		correct.setEditable(false);
		
		inCorrect = new JTextField(""+(total - correctAnswers));
		inCorrect.setEditable(false);
		
		double percentage = ((double)correctAnswers/total)*100;
		percent = new JTextField(""+percentage+"%");
		percent.setEditable(false);
		
		JPanel resultP = new JPanel();
		resultP.setLayout(new GridLayout(4,2));
		
		resultP.add(idL); 			resultP.add(id);
		resultP.add(correctL);		resultP.add(correct);
		resultP.add(inCorrectL);	resultP.add(inCorrect);
		resultP.add(percentL);		resultP.add(percent);
		
		JButton close = new JButton("Close");
		close.addActionListener(this);
		
		add(resultP,BorderLayout.CENTER);
		
		JPanel southRegion = new JPanel();
		southRegion.setLayout(new GridLayout(1,5));
		southRegion.add(new JLabel("     "));
		southRegion.add(new JLabel("     "));
		southRegion.add(close);
		southRegion.add(new JLabel("     "));
		southRegion.add(new JLabel("     "));
		
		add(southRegion,BorderLayout.SOUTH);
		
		storeData();
		
		pack();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Close")) 
			System.exit(0);
	}
	
	private void storeData(){
		
		try {
			FileInputStream fin = new FileInputStream("studentsReport.txt");
			Scanner sc1 = new Scanner(fin);
			
			int line = 0;
			int studentLine = -1;
			
			sc1.nextLine();
			
			while(sc1.hasNextLine()) {
				
				String studentName = sc1.next();
				if(studentName.equals(id.getText())) 
					studentLine = line;
				sc1.nextLine();
				line++;
			}
			
			sc1.close();
			if(studentLine != -1) {

				Scanner sc2 = new Scanner(new FileInputStream("studentsReport.txt"));
				ArrayList<String> input = new ArrayList<String>();
			
				sc2.nextLine();

				while(sc2.hasNextLine()) 
					input.add(sc2.nextLine());
				
				sc2.close();
				input.remove(studentLine);
	
				FileOutputStream fot = new FileOutputStream("studentsReport.txt");
				PrintWriter pw  = new PrintWriter(fot);
				
				pw.println("Name \t Correct \t Wrong \t percentage");
				
		
				for(int i = 0; i<input.size() ;i++) 
					pw.println(input.get(i));
				
				pw.close();
				
			}
			
			FileOutputStream fot = new FileOutputStream("studentsReport.txt",true);
			PrintWriter pw2  = new PrintWriter(fot);
			
			
			pw2.println(id.getText()+" \t "+correct.getText()+" \t "+inCorrect.getText()+" \t "+percent.getText());
			pw2.close();
			
			
		}catch(IOException e) {
			System.exit(0);
		}
		
	}

}
