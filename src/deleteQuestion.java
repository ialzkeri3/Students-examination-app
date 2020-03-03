import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class deleteQuestion extends JFrame implements ActionListener {

	private JComboBox quesList;
	
	public deleteQuestion() {
		
		super("Delete a question");
		setSize(400,150);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		setLayout(new BorderLayout());


		JPanel quesDe = new JPanel();
	
		
		quesList = new JComboBox(getQues());
		quesList.setEditable(true);
		
		JLabel quesSelect = new JLabel("Select Question:");
		JButton delete = new JButton("Delete");
		delete.addActionListener(this);
		
		JPanel bottom = new JPanel();

		JLabel space = new JLabel("");
		
		JButton exit = new JButton("  Exit  ");
		exit.addActionListener(this);
	
		bottom.add(space);	bottom.add(exit);	bottom.add(space);
	
		quesDe.add(quesSelect); quesDe.add(quesList); quesDe.add(delete);
		add(quesDe,BorderLayout.NORTH);
	
		add(bottom,BorderLayout.SOUTH);
		
		
		
		pack();
	
	}
	
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand().equals("Delete")) {
			
			if(quesList.getSelectedItem() == null)
				new ExclamationMarkWindow("Error","There are no questions     ");
			else {	
				deleteQues((String)quesList.getSelectedItem());
				ImageIcon tickIcon  = new ImageIcon("tickMark.png");
				JOptionPane.showMessageDialog(this,"The question has been deleted successfuly.","Successful",JOptionPane.YES_OPTION,tickIcon);
			
			
			quesList.removeAllItems();
			quesList = new JComboBox(getQues());
			dispose();
			new deleteQuestion();
			}
		}
		else if(e.getActionCommand().equals("  Exit  "))
			dispose();
	}
	
	
	private void deleteQues(String ques) {
		
		// 1. move all elements to an arrayList
		// 2. find the selected question from the arrayList
		// 3. remove the question and the next four line (options) from the arrayList
		// 4. write the arrayList into questions file again
		// 5. do (1-4) again for the corresponding correct Answers
		
		try {
			FileInputStream quesIn = new FileInputStream("questions.txt");
			Scanner sc = new Scanner(quesIn);
			
			ArrayList<String> quesList1 = new ArrayList<String>();
			
			
			while(sc.hasNextLine()) 
				quesList1.add(sc.nextLine());
			sc.close();
			
			int questionNum = 0;
			
			for(int i = 0; i<(quesList1.size()-4);i++) {
				if(ques.equals(quesList1.get(i))) {
					quesList1.remove(i);//question
					quesList1.remove(i);//op1
					quesList1.remove(i);//op2
					quesList1.remove(i);//op3
					quesList1.remove(i);//op4
				}
				questionNum = i/5;
			}
			
			FileOutputStream quesOut = new FileOutputStream("questions.txt");
			PrintWriter pw = new PrintWriter(quesOut);

			for(int i = 0; i<quesList1.size();i++) 
				pw.println(quesList1.get(i));
			pw.close();	
			
			
			FileInputStream answersIn = new FileInputStream("correct.txt");
			Scanner answSc = new Scanner(answersIn);
			
			ArrayList<String> answersList = new ArrayList<String>();
			
			while(answSc.hasNextLine()) 
				answersList.add(answSc.nextLine());
			answSc.close();

			
			System.out.println(answersList.toString());
			answersList.remove(questionNum);
			System.out.println(answersList.toString());
			
			FileOutputStream answersOut = new FileOutputStream("correct.txt");
			PrintWriter answersPw = new PrintWriter(answersOut);
			
			for(int i = 0; i<answersList.size();i++) {
				answersPw.println(answersList.get(i)); System.out.println(answersList.get(i));}
			answersPw.close();	

		}catch(IOException ex) {
			System.exit(0);
		}
	}
	
	
	private String[] getQues() {
		ArrayList<String> quesList1 = new ArrayList<String>();
		
		try {
			FileInputStream questions = new FileInputStream("questions.txt");
			Scanner sc = new Scanner(questions);
			int line = 0;
			
			while(sc.hasNextLine()) {
				if(line%5 == 0 || line == 0) {
					quesList1.add(sc.nextLine());
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
		
		String[] quesList2 = new String[quesList1.size()];
		quesList2 = quesList1.toArray(quesList2);
		return quesList2;
	
	}

	
}
