import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentLogin extends JFrame implements ActionListener {

	private JTextArea questionA;
	
	private JRadioButton op1;
	private JRadioButton op2;
	private JRadioButton op3;
	private JRadioButton op4;
	
	private  ButtonGroup optionsGroup;
	
	private int totalQues;
	private int currentQuestion;
	private int correctAnswers;
	
	private JButton next;
	private String id;
	
	
	public StudentLogin(String id) {
		
		super("Student Screen");
		setSize(400,400);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		this.id = id;
		
		setLayout(new BorderLayout());
		
		questionA = new JTextArea();
		questionA.setEditable(false);
		
		JPanel optionsP = new JPanel();
		optionsP.setLayout(new GridLayout(4,1));
	

		op1 = new JRadioButton();
		optionsP.add(op1);
		
		op2 = new JRadioButton();
		optionsP.add(op2);
		
		op3 = new JRadioButton();
		optionsP.add(op3);
		
		op4 = new JRadioButton();
		optionsP.add(op4);
		
	    optionsGroup = new ButtonGroup();
	    optionsGroup.add(op1);
	    optionsGroup.add(op2);
	    optionsGroup.add(op3);
	    optionsGroup.add(op4);
		


	
		totalQues = getTotal();



		JPanel bottom = new JPanel();

		JLabel space = new JLabel("");
		
		next = new JButton(" Next Question ");
		next.addActionListener(this);
	
		bottom.add(space);	bottom.add(next);	bottom.add(space);
		
		if(totalQues != 0) {
			currentQuestion = 1;
			setFirstValues();
			add(questionA,BorderLayout.NORTH);
			add(optionsP,BorderLayout.CENTER);
			add(bottom,BorderLayout.SOUTH);
		}
		else {
			
			JPanel center = new JPanel();

			ImageIcon tickIcon  = new ImageIcon("exclamationMark.png");
			JLabel icon = new JLabel(tickIcon);
			center.add(icon); center.add(new JLabel("No available questions currently   "));
			add(center,BorderLayout.CENTER);

			JPanel exitBottom = new JPanel();
			JButton exit = new JButton("  Exit  ");
			exit.addActionListener(this);
			exitBottom.add(space);	exitBottom.add(exit);	exitBottom.add(space);
			add(exitBottom,BorderLayout.SOUTH);
		}

		pack();
		


		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(getSelected() == null && (e.getActionCommand().equals(" Next Question ") || e.getActionCommand().equals(" View Result ")))
			JOptionPane.showMessageDialog(this,"You have to select an answer","ERROR",JOptionPane.ERROR_MESSAGE);
		else {
		
			if(e.getActionCommand().equals(" Next Question ") || e.getActionCommand().equals(" View Result ")) {
	
				if(currentQuestion < totalQues) 
					viewNext();
				else if(currentQuestion == totalQues)
					new ResultScreen(id, correctAnswers, currentQuestion);
			}
			else if(e.getActionCommand().equals("  Exit  "))
				dispose();
		}
	}
	
	private int getTotal() {
		
		int total = 0;
		int lines = 0;
		
		try {
			

		FileInputStream questions = new FileInputStream("questions.txt");
		Scanner sc = new Scanner(questions);
		
		while(sc.hasNextLine()) {
			lines++;
			sc.nextLine();
		}

		sc.close();
		
		total = lines/5;

		
		return total;
		
		}catch(IOException e) {
			System.exit(0);
			return 0;
		}
				
	}
	
	private void setFirstValues() {
		
		try {
			FileInputStream questions = new FileInputStream("questions.txt");
			Scanner sc = new Scanner(questions);

			questionA.setText(sc.nextLine());
			op1.setText(sc.nextLine());
			op2.setText(sc.nextLine());
			op3.setText(sc.nextLine());
			op4.setText(sc.nextLine());
			
			sc.close();
			
			if(totalQues == 1)
				next.setText(" View Result ");
				
		}catch(IOException e) {
			System.exit(0);
		}
	
	}
	
	private void viewNext() {
		
		try {
			
		FileInputStream answers = new FileInputStream("correct.txt");
		Scanner answerSc = new Scanner(answers);
		
		int line = 1;
		
		String selected = getSelected();
		
		while(answerSc.hasNextLine()) {
			String correctA = answerSc.nextLine();
			if(line == currentQuestion) {
				if(correctA.equals(selected)) {
					correctAnswers++;
					}
			}
			line++;
		}
			answerSc.close();

			
		FileInputStream questions = new FileInputStream("questions.txt");
		Scanner sc = new Scanner(questions);

		line = 0;
		int nextQuesLine = currentQuestion * 5;
		
		while(sc.hasNextLine()) {
			if(line == nextQuesLine) {
				questionA.setText(sc.nextLine());
				op1.setText(sc.nextLine());
				op2.setText(sc.nextLine());
				op3.setText(sc.nextLine());
				op4.setText(sc.nextLine());
				optionsGroup.clearSelection();
				line = -1;
				}
			if(line != -1)
				sc.nextLine();
			
			line++;
		}
		sc.close();
		
		}catch(IOException e) {
			System.exit(0);
		}
				
		if(currentQuestion == (totalQues-1)) 
			next.setText(" View Result ");
		
		currentQuestion++;
	}
	
	private String getSelected() {
		
		JRadioButton selected = null;
		
		if(op1.isSelected())
			selected = op1;
		else if(op2.isSelected())
			selected = op2;
		else if(op3.isSelected())
			selected = op3;
		else if(op4.isSelected())
			selected = op4;


		try {
			return selected.getText();
		}catch(NullPointerException e) {
			return null;
		}

	}
	
}