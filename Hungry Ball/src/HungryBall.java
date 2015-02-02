import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class HungryBall 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hungry Ball");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HungryBallPanel panel = new HungryBallPanel();
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
	}
}

class HungryBallPanel extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public static final int BOXSIZE = 500;
	private Ball Ball;
	
	public HungryBallPanel()
	{
		Ball = new Ball(250, 150);
		setPreferredSize(new Dimension(BOXSIZE,BOXSIZE));
	}
	
	public void addNotify()
	{
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, BOXSIZE, BOXSIZE);
		Ball.drawBall(g);
		Ball.drawFood(g);
		Ball.drawScore(g);
	}
	
	@Override
	public void run() {
		while(thread != null)//run the program
		{
			Ball.moveBall();
			if(Ball.outOfBounds() == true)
			{
				thread = null;//stop the game
				System.out.println("You lost! \nScore: " + Ball.getScore());
			}
			
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {}
		}
	}
	
}