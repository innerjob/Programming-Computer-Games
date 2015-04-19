package ClassAssignments.ShootingBullet;

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
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ShootingBullet 
{
	//Don't Need A Main Method
//	public static void main(String[] args)
//	{
//		ShootingBullet game = new ShootingBullet();
//	}
	
	public static JFrame getFrame()
	{
		return frame;
	}
	
	private static JFrame frame = new JFrame();
	
	public ShootingBullet(String name)
	{
		frame.setTitle(name + " Playing Shooting Bullet");
		String name1 = name;
		frame.setSize(3000, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) 
			{
				System.exit(0);
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
//		frame.setLocationRelativeTo(null);//have the frame popup in the center
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		frame.setLocation(screenWidth/4, screenHeight/4);
		frame.setResizable(false);
		
		ShootingPanel panel = new ShootingPanel(name1, frame);
		
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
	public String name1;
	
	public ShootingPanel(String name, JFrame frame)
	{
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {bullet.jump();}
			public void mouseReleased(MouseEvent arg0) {}
		});
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		name1 = name;
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
		JFrame frame = ShootingBullet.getFrame();
		
		while(thread != null)
		{
			bullet.move();
			bullet.addScore();
			repaint();
			boolean hit = bullet.hit();
			
			//End the game if the player hits the top or bottom
			if(hit == true)
			{
				frame.dispose();
				removeAll();
				bullet.reset(frame, name1);
				thread = null;
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
