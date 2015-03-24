/**
 * ROUGH DRAFT
 * 
 * @author Sam Redmond
 * 
 * This class will have a bullet and it will be shooting along a scrolling world filled with obstacles 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ShootingBullet 
{
//	public static void main(String[] args)
//	{
//		ShootingBullet game = new ShootingBullet();
//	}
	
	public ShootingBullet(String name)
	{
		JFrame frame = new JFrame(name + " Playing Shooting Bullet");
		
		frame.setSize(500, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//have the frame popup in the center
		frame.setResizable(false);
		
		ShootingPanel panel = new ShootingPanel();
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.pack();
	}
}




class ShootingPanel extends JPanel implements Runnable
{
	
	private Thread thread;
	
	private final int WIDTH = 500;
	private final int HEIGHT = 300;
	
	private Bullet bullet = new Bullet();
	
	public ShootingPanel()
	{
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {bullet.jump();}
			public void mouseReleased(MouseEvent arg0) {}
		});
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		bullet.paint(g);
		bullet.drawScore(g);
	}
	
	//Start The Thread
	public void addNotify()
	{
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	
	//Run The Game!
	public void run()
	{
		while(thread != null)
		{
			bullet.move();
			bullet.addScore();
			repaint();
			boolean hit = bullet.hit();
			
			//Reset The Game If The Bullet Hits
			if(hit == true)
			{
				bullet.reset();
			}
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
