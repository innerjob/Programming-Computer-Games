package ClassAssignments.Assignment2;

/**

 * @author Sam
 * 
 * 	This class draws the panel and a ball. The main purpose it to have a ball drawn on the screen and to have it fall
 * 		and bounce. When the mouse is clicked somewhere on the screen the ball will change directions. If it hits the sides
 * 		10 times the game stops and you get your score
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import com.TechU.OpenSource.TechUtil;

public class MovingBall 
{		
	static final int BOXSIZE = 500;
	public static void main(String[] args)
	{
		//Create the frame
		JFrame frame = new JFrame("Bouncy Ball");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(100, 100, 500, 500);
		
		//Create the panel and add it to the frame
		BOUNCEYPANEL panel = new BOUNCEYPANEL();
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();//this will make sure that the frame is sized to the panel
	}
}

class BOUNCEYPANEL extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static final int BOXSIZE = 500;//Make the panel a square
	Thread bounce;
	private Ball Ball;
	private JLabel lbl = new JLabel("Click To Change Direction. Don't Hit The Sides. Score 1000 And You Win!");
	private KeyListener listener;
//	private TechUtil util = new TechUtil();

	
	public BOUNCEYPANEL()
	{	
		Ball = new Ball(250, 150);
		add(lbl, BorderLayout.NORTH);
		
		//Set the size of the JPanel
		setPreferredSize(new Dimension(BOXSIZE, BOXSIZE));
		
		//Change the direction of the ball on mouse click
		addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e){Ball.reverse();}//Change ball direction
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	//Start The Thread
	public void addNotify()
	{
		super.addNotify();
		bounce = new Thread(this);
		bounce.start();//Start the thread
	}
	//Paint The Ball
	public void paintComponent(Graphics g)
	{
		//Set the color of the screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, BOXSIZE, BOXSIZE);
		//Draw the ball
		Ball.draw(g);
		//Draw the score
		Ball.drawScore(g);
	}
	
	public void run() 
	{
		while(bounce != null)
		{
			//Move the ball
			Ball.move();
			//Increase the score
			Ball.increaseScore();
			//Check to see when the counter runs out
			int c = Ball.getCounter();
			int s = Ball.getScore();
			
			//Player will lose if the counter runs out before they get to 1000
			if(c == 0)
			{//Stop the game from running
				bounce = null;
				//Print out an error message when the counter runs out

				//util.popup("Lost", "You Lost!", 100, 100); Fix this by adding the other JAR file from my desktop
				System.out.println("Sorry...\nYou Lost! \nScore: " + Ball.getScore());
			}else if(s == 1000)
			{
				bounce = null;
				//Print out a win message when the score reaches 1000
				//util.popup("Lost", "You Lost!", 100, 100); Fix this by adding the other JAR file from my desktop
				System.out.println("Congratulations!\nYou Win! \nScore: " + Ball.getScore());

//				util.popup("Lost", "Sorry...\nYou Lost!\nScore: " + Ball.getScore(), 100, 150); //Fix this by adding the other JAR file from my desktop
				System.out.println("You Lost! \nScore: " + Ball.getScore());
			}else if(s == 1000)
			{
				bounce = null;
//				util.popup("Lost", "Congratulations!\nYou Win!\nScore: " + Ball.getScore(), 100, 150);

			}
			
			//Repaint the screen
			repaint();
			try 
			{
				//Delay the thread 
				Thread.sleep(40);
		    } catch(InterruptedException e) {}
		}
	}
	
}
