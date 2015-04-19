package ClassAssignments.ShootingBullet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class HighScore 
{
	//Don't need the main method here
//	public static void main(String[] args)
//	{
//		HighScore score = new HighScore("Sam");
//	}
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel score = new JLabel("Score: ");
	private JTextField scoreField = new JTextField();
	private int width = 350, height = 100;
	
	private JPanel buttonPanel = new JPanel();
	
	private JButton reset = new JButton("Replay");
	private JButton menu = new JButton("Back To Main Menu");
	private JButton exit = new JButton("Exit");
	
	private String name1;
	
	public HighScore(String name, int hs)
	{
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout(0,0));
		frame.setTitle(name + "'s Score");
		
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		name1 = name;
		
//		buttonPanel.add(reset);
//		buttonPanel.add(menu);
		buttonPanel.add(exit);
		
		frame.add(panel, BorderLayout.CENTER);
		panel.add(score);
		panel.add(scoreField);
		scoreField.setColumns(15);
		scoreField.setHorizontalAlignment(SwingConstants.CENTER);
		scoreField.setEditable(false);
		scoreField.setText(String.valueOf(hs));
		
		//Replay the game ********************************************DOESN'T WORK*********************************************
//		reset.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e)
//			{
//				frame.dispose();
//				ShootingBullet bullet = new ShootingBullet(name1);
//			}
//		});
		
		
		//Close this frame and open up the welcome screen**********ALSO DOESN'T WORK THE FRAME.DISPOSE() METHOD ISN'T KILLING THE THREAD************************
//		menu.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e)
//			{
//				frame.dispose();
//				WelcomeScreen screen = new WelcomeScreen();
//			}
//		});
		
		//Exit the game entirely
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
	}

}
