/**
 * @author Sam
 * 
 * 	This class draws the panel and a ball. The main purpose it to have a ball drawn in the center of the screen 
 * 		and to have it fall and bounce when the mouse is clicked somewhere on the screen
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MovingBall 
{
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
	Thread bounce;
	private int panelSize = 500;
	private Graphics graph;
	private Ball Ball;
	private JLabel lbl = new JLabel("Click To Change Position");
	
	public BOUNCEYPANEL()
	{	
		Ball = new Ball(240, 200);
		add(lbl, BorderLayout.NORTH);
		//Set the size of the JPanel
		setPreferredSize(new Dimension(panelSize, panelSize));
	}
	
	public void addNotify()
	{
		super.addNotify();
		bounce = new Thread(this);
		bounce.start();
	}

	public void paintComponent(Graphics g)
	{
		Ball.draw(g);
	}
	
	public void run() 
	{
		while(bounce != null)
		{
			//Bounce the ball
			addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) 
				{
					Ball.move();
					repaint();
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
			try 
			{
				Thread.sleep(40);
		    } catch(InterruptedException e) {}
		}
	}
	
}
