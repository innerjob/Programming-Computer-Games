/**
 * @author Sam
 * 
 * 	This class draws the panel and a ball. The main purpose it to have a ball drawn in the center of the screen 
 * 		and to have it fall and bounce when the mouse is clicked somewhere on the screen
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovingBall 
{		
	static final int BOXSIZE = 500;
	public static void main(String[] args)
	{
		//Create the frame
		JFrame frame = new JFrame("Bouncy Ball");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		
		//Create the panel and add it to the frame
		BOUNCEYPANEL panel = new BOUNCEYPANEL();
		frame.add(panel, BorderLayout.CENTER);
	}
}

class BOUNCEYPANEL extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BOXSIZE = 500;
	Thread bounce;
	private JPanel panel = new JPanel();
	private Graphics graph;
	private Ball Ball;
	private JButton btn = new JButton("Bounce");
	private JLabel lbl = new JLabel("Click To Reverse Direction");
	
	public BOUNCEYPANEL()
	{	
		Ball = new Ball(250, 150);
		add(panel, BorderLayout.NORTH);
		panel.add(lbl);
		//Set the size of the JPanel
		setPreferredSize(new Dimension(BOXSIZE, BOXSIZE));
		
//		addMouseListener(new MouseListener()
//		{
//			public void mouseClicked(MouseEvent e){Ball.reverse(e);}
//			public void mousePressed(MouseEvent e) {}
//			public void mouseReleased(MouseEvent e) {}
//			public void mouseEntered(MouseEvent e) {}
//			public void mouseExited(MouseEvent e) {}
//		});
	}
	
	//Start The Thread
	public void addNotify()
	{
		super.addNotify();
		bounce = new Thread(this);
		bounce.start();
	}

	//Paint The Ball
	public void paintComponent(Graphics g)
	{
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
			//Ball.reverse(); --> Try to get the ball to reverse on click
			Ball.move();
//			addMouseListener(new MouseListener()
//			{
//				public void mouseClicked(MouseEvent e){Ball.reverse(e);}
//				public void mousePressed(MouseEvent e) {}
//				public void mouseReleased(MouseEvent e) {}
//				public void mouseEntered(MouseEvent e) {}
//				public void mouseExited(MouseEvent e) {}
//			});
			//Repaint the screen
			repaint();
			try 
			{
				Thread.sleep(40);
		    } catch(InterruptedException e) {}
		}
	}
	
}
