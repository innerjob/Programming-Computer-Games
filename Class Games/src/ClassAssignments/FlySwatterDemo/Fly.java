package ClassAssignments.FlySwatterDemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Fly-swatting game
 * Click on the fly to kill it.
 * 
 * @author mike slattery
 * @version jan.2015
 *
 */
public class Fly extends JFrame
{
	public static void main(String[] args) {
			JFrame f = new JFrame("Fly Swatter Game");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			FlyPanel panel = new FlyPanel();
			f.add(panel, BorderLayout.CENTER);
			f.pack();
			f.setVisible(true);
	}
}

class FlyPanel extends JPanel implements Runnable
{
  Thread anim;
  int fly_x, fly_y;
  boolean fly_alive = true;
  int swatter_x, swatter_y;

  private static final int PWIDTH = 650; // size of panel
  private static final int PHEIGHT = 500;

  public FlyPanel()
  {
	super();
	setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
	fly_x = PWIDTH/2; fly_y = PHEIGHT/2;
	swatter_x = PWIDTH - 100; swatter_y = 100;
	mseL ml = new mseL();
	addMouseListener(ml);
	addMouseMotionListener(ml);
  }

  public void addNotify()
  {
	super.addNotify();
	anim = new Thread(this);
	anim.start();
  }

  public void run()
  {
    while(anim != null)
    {
		updateFly();
		repaint();
		try{
		  Thread.sleep(0);
		} catch (Exception e) {}
    }
  }

  public void updateFly()
  {
  	int dx = (int)(20*Math.random()-10);
  	int dy = (int)(20*Math.random()-10);
  	fly_x += dx; fly_y += dy;
  	// If offscreen, move back to center
  	if (fly_x < 0 || fly_x > PWIDTH || fly_y < 0 || fly_y > PHEIGHT)
  	{
  		fly_x = PWIDTH/2; fly_y = PHEIGHT/2;
  	}
  }

  public void paintComponent(Graphics g)
  {
	g.setColor(Color.cyan);
	g.fillRect(0, 0, PWIDTH,  PHEIGHT);
	
 	if (fly_alive)
 	{
 		g.setColor(Color.black);
 		g.fillOval(fly_x-10, fly_y-10, 20, 20);
 	}
    g.setColor(Color.red);
    g.drawRect(swatter_x-20, swatter_y-20, 40, 40);
  }

  class mseL extends MouseAdapter implements MouseMotionListener
  {
    public void mousePressed(MouseEvent e)
    {
      int x = e.getX();
      int y = e.getY();
      if (x-20 < fly_x && fly_x < x+20 &&
	    y-20 < fly_y && fly_y < y+20)
	  {
	    // Fly is killed
	    fly_alive = false;
	  }
    }

    public void mouseMoved(MouseEvent e)
    {
      int x = e.getX();
      int y = e.getY();
      swatter_x = x;
      swatter_y = y;
      //repaint();

    }

    public void mouseDragged(MouseEvent e){}
  }
}