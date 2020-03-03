import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class addQuestion extends JFrame implements ActionListener  {

		
		private JTextField questionF;
		
		private JRadioButton op1R;
		private JRadioButton op2R;
		private JRadioButton op3R;
		private JRadioButton op4R;
		
		private JTextField op1F;
		private JTextField op2F;
		private JTextField op3F;
		private JTextField op4F;
		
		private  ButtonGroup optionsGroup;
		
	
		public addQuestion() {
			
		
			super("Add Question");
			setSize(400,400);	
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setVisible(true);
			
			setLayout(new BorderLayout());
			
			JPanel questionP = new JPanel();
			JPanel optionsP = new JPanel();
			JPanel buttonsP = new JPanel();
			
			questionF = new JTextField(15);
			JLabel questionL = new JLabel("           Enter Question:");
			questionP.add(questionL); questionP.add(questionF);
			
			optionsP.setLayout(new GridLayout(4,1));
		
			JPanel op1 = new JPanel();
			JPanel op2 = new JPanel();
			JPanel op3 = new JPanel();
			JPanel op4 = new JPanel();
			
			op1R = new JRadioButton("Option 1",true);
			op1F = new JTextField(10); 
			op1.add(op1R); 	op1.add(op1F);
			optionsP.add(op1);
			
			op2R = new JRadioButton("Option 2");
			op2F = new JTextField(10);
			op2.add(op2R); op2.add(op2F);
			optionsP.add(op2);
			
			op3R = new JRadioButton("Option 3");
			op3F = new JTextField(10);
			op3.add(op3R); op3.add(op3F);
			optionsP.add(op3);
			
			op4R = new JRadioButton("Option 4");
			op4F = new JTextField(10);
			op4.add(op4R); op4.add(op4F);
			optionsP.add(op4);
			
		    optionsGroup = new ButtonGroup();
		    optionsGroup.add(op1R);
		    optionsGroup.add(op2R);
		    optionsGroup.add(op3R);
		    optionsGroup.add(op4R);
			

			add(questionP,BorderLayout.NORTH);
			add(optionsP,BorderLayout.CENTER);
		
			
			JButton addQues = new JButton("Add");
			JButton exit = new JButton("Exit");
			
			addQues.addActionListener(this);
			exit.addActionListener(this);

			buttonsP.setLayout(new FlowLayout());
			buttonsP.add(addQues); buttonsP.add(exit);
			add(buttonsP,BorderLayout.SOUTH);
		
			pack();
			
	
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Add")) {
				addquestion();
			}
			else if(e.getActionCommand().equals("Exit")) {
				dispose();
			}
		}
		
		private void addquestion() {


				try {
					
					if(questionF.getText().equals("") || op1F.getText().equals("") || op2F.getText().equals("") || op3F.getText().equals("") || op4F.getText().equals("")) {
						JOptionPane.showMessageDialog(this,"You have to fill all options and the question!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
					else {
					FileOutputStream questions = new FileOutputStream("questions.txt",true);
					PrintWriter pw1 = new PrintWriter(questions);
					
					pw1.println(questionF.getText());
					pw1.println(op1F.getText());
					pw1.println(op2F.getText());
					pw1.println(op3F.getText());
					pw1.println(op4F.getText());
					
					pw1.close();
					
					
					FileOutputStream correct = new FileOutputStream("correct.txt",true);
					PrintWriter pw2 = new PrintWriter(correct);

					if(op1R.isSelected())
						pw2.println(op1F.getText());
					
					if(op2R.isSelected())
						pw2.println(op2F.getText());
					
					if(op3R.isSelected())
						pw2.println(op3F.getText());
					
					if(op4R.isSelected())
						pw2.println(op4F.getText());
					
					pw2.close();
					
					ImageIcon tickIcon  = new ImageIcon("tickMark.png");
					JOptionPane.showMessageDialog(this,"Question have been successfully addes","Successful",JOptionPane.ERROR_MESSAGE,tickIcon);

					}
					
					questionF.setText("");
					op1F.setText("");
					op2F.setText("");
					op3F.setText("");
					op4F.setText("");
					
				}catch(IOException ex) {
					System.exit(0);
				}	
		}
}
