import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExclamationMarkWindow extends JFrame implements ActionListener {

	public ExclamationMarkWindow(String messageType, String Label) {
		super(messageType);
		setVisible(true);
		setResizable(false);
		
		
		JPanel center = new JPanel();

		ImageIcon tickIcon  = new ImageIcon("exclamationMark.png");
		JLabel icon = new JLabel(tickIcon);
		center.add(icon); center.add(new JLabel(Label));
		add(center,BorderLayout.CENTER);

		JLabel space = new JLabel("");
		
		JPanel exitBottom = new JPanel();
		JButton exit = new JButton("  Exit  ");
		exit.addActionListener(this);
		exitBottom.add(space);	exitBottom.add(exit);	exitBottom.add(space);
		add(exitBottom,BorderLayout.SOUTH);
		
		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("  Exit  "))
			dispose();
	}

}
