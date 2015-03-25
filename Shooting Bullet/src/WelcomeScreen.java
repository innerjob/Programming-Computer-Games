/**
 * @author Sam Redmond
 * 
 * The Purpose of this class is to display a welcome screen to the user for them to start a new game.
 */

/*
 * Displays The Welcome Screen When The Game Starts
 ***BUG***
 * For some reason you have to resize the frame in order to see any of the components added to it.
 *********
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.TechU.OpenSource.TechUtil;


public class WelcomeScreen 
{
	
	public static void main(String[] args)
	{
		WelcomeScreen welcome = new WelcomeScreen();
	}
	
	private TechUtil util = new TechUtil();
	
	private JFrame frame = new JFrame("Welcome To The Shooting Bullet Game");
	private int width = 400, height = 100;
	
	private JButton start = new JButton("Start Game");
	private JButton viewHS = new JButton("View High Score");
	
	private JLabel name = new JLabel("Enter Your Name: ");
	private JTextField field = new JTextField();
	
	private JPanel panel = new JPanel();
	
	public WelcomeScreen()
	{
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		frame.setLayout(new BorderLayout(0,0));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.add(start, BorderLayout.SOUTH);
		
		field.setColumns(15);
		panel.add(name);
		panel.add(field);
		field.setHorizontalAlignment(SwingConstants.CENTER);
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//Start the game and dispose of the welcome screen
				String name = field.getText();
				if(name.equals(null) || name.equals(""))
				{
					//Popup with an error message
					util.popup("Error", "Enter Your Name", 100, 100);
					field.setText("");
				}
				else
				{
					ShootingBullet bullet = new ShootingBullet(name);
					frame.dispose();
				}
			}
		});
	}
}
