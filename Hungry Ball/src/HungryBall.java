import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HungryBall 
{
	public static boolean Easy = false, Medium = false, Hard = false, isClicked = false;
	public int one = 1;
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hungry Ball");
		JPanel panel1 = new JPanel();
		panel1.setVisible(true);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Make it false and be able to set a difficulty level
		HungryBallPanel panel = new HungryBallPanel();
		panel.setVisible(false);
		
		//Buttons to set difficulty
		JButton easy = new JButton("Easy");// speed = 10
		easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Easy = true;
				isClicked = true;
				panel.setVisible(true);
				panel1.setVisible(false);
			}
		});
		
		JButton medium = new JButton("Medium");// speed = 15
		medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Medium = true;
				isClicked = true;
				panel.setVisible(true);
				panel1.setVisible(false);
			}
		});
		
		JButton hard = new JButton("Hard");// speed = 20
		hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Hard = true;
				isClicked = true;
				panel.setVisible(true);
				panel1.setVisible(false);
			}
		});
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);
		
		frame.add(panel1, BorderLayout.NORTH);
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
	
	//Start The Thread
	public void addNotify()
	{
		super.addNotify();
		thread = new Thread(this);
		if(HungryBall.isClicked)
		{
			thread.start();
		}
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
	public void run() 
	{
		while(thread != null)//run the program
		{
			Ball.moveBall();
			if(Ball.outOfBounds())
			{
				thread = null;//stop the game
				System.out.println("You lost! \nScore: " + Ball.getScore());
			}
			repaint();
			try {
				if(HungryBall.Easy)
				{
					Thread.sleep(50);
				}else if(HungryBall.Medium)
				{
					Thread.sleep(40);
				}else if(HungryBall.Hard)
				{
					Thread.sleep(30);
				}
			} catch (InterruptedException e) {}
		}
	}

	//Sets the direction of the ball
	class KeyL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int event = e.getKeyCode();
			
			switch(event)
			{
			case KeyEvent.VK_UP: Ball.set_up(true);
				break;
				
			case KeyEvent.VK_DOWN: Ball.set_down(true);
				break;
			
			case KeyEvent.VK_LEFT: Ball.set_left(true);
				break;
			
			case KeyEvent.VK_RIGHT: Ball.set_right(true);
				break;
			}
		}
	}

	// For some reason this won't work
	//class MseL extends MouseAdapter {
//		public void mousePressed(MouseEvent e){
//			requestFocus();
//		}
	//}
}

