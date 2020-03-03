import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame implements ActionListener {

	private JMenuItem aQuestion;
	private JMenuItem dQuestion;
	private JMenuItem aUser;
	private JMenuItem dUser;
	private JMenuItem vReport;
	private JMenuItem exit;
	
	public AdminLogin() {
		super("Adminstrator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,120);
		setVisible(true);
	
		aQuestion = new JMenuItem("Add Question");
		aQuestion.addActionListener(this);

		dQuestion = new JMenuItem("Delete Question");
		dQuestion.addActionListener(this);
		
		aUser = new JMenuItem("Add User");
		aUser.addActionListener(this);
		
		dUser = new JMenuItem("Delete User");
		dUser.addActionListener(this);
		
		vReport = new JMenuItem("View Report");
		vReport.addActionListener(this);
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		
		JMenuBar adminbar = new JMenuBar();
		JMenu quiz = new JMenu("Quiz");
		JMenu user = new JMenu("User");
		JMenu report = new JMenu("Report");
		JMenu exitM = new JMenu("Exit");
		
		quiz.add(aQuestion); quiz.add(dQuestion);
		user.add(aUser); user.add(dUser);
		report.add(vReport); exitM.add(exit);
		
		adminbar.add(quiz); adminbar.add(user);
		adminbar.add(report); adminbar.add(exitM);
		
		setJMenuBar(adminbar);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("Add Question"))
			new addQuestion();

		else if(e.getActionCommand().equals("Delete Question"))
			new deleteQuestion();
		
		else if(e.getActionCommand().equals("Add User"))
			new addUser();
		
		else if(e.getActionCommand().equals("Delete User"))
			new deleteUser();
		
		else if(e.getActionCommand().equals("View Report"))
			new ViewReport();
		
		else if(e.getActionCommand().equals("Exit")) {
			dispose();
		}
		
	}
	

}
