package ClassAssignments.ForrestFire;

/*

 * ForestFire.java
 *
 * A simple game demonstrating Sprite collisions
 *
 * written by mike slattery - feb 1999
 * updated by mcs - sep 2002
 * updated by mcs - feb 2011
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ForrestFire
{
	public static void main(String[] args)
	{
		JFrame f = new JFrame("ForestFire");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new FFPanel();
		f.add(panel, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
}

class FFPanel extends JPanel implements Runnable
{
	TreeSprite tree;
	FireSprite fire;
	Sprite scenery[];
	boolean boxDebug = false;
	Image rockImage, lakeImage, treeImage, fireImage;
	Image burnImage, smokeImage;
	Thread anim = null;
	Image buffer;
	Graphics bufgr;
	Font font;
	// Global scale variable for bounding boxes
	public static float BBscale = 1.0f;

	public static final int FRAME_DELAY = 50;
	public static final int DYING = 80;

	public FFPanel()
	{
		super();
		setPreferredSize(new Dimension(400, 300));

		addMouseListener(new mseL());
		addKeyListener(new keyL());

		rockImage = new ImageIcon(getClass().getResource("rock.gif")).getImage();
		lakeImage = new ImageIcon(getClass().getResource("lake.gif")).getImage();
		treeImage = new ImageIcon(getClass().getResource("tree.gif")).getImage();
		fireImage = new ImageIcon(getClass().getResource("fire.gif")).getImage();
		burnImage = new ImageIcon(getClass().getResource("burn.gif")).getImage();
		smokeImage = new ImageIcon(getClass().getResource("smoke.gif")).getImage();

		
		font = new Font("TimesRoman",Font.ITALIC,30);
		setLevel1();
	}

	public void addNotify()
	{
		super.addNotify();
		buffer = createImage(400,300);
		bufgr = buffer.getGraphics();
		anim = new Thread(this);
		anim.start();
	}

	public void run()
	{
		while(anim != null)
		{
			fire.updateSprite();
			tree.updateSprite();
			repaint();
			try
			{
				Thread.sleep(FRAME_DELAY);
			} catch (InterruptedException e)
				{}
		}
	}

	public void setLevel1()
	{
		scenery = new Sprite[7];
		scenery[0] = new RockSprite(150,100,rockImage);
		scenery[1] = new RockSprite(30,200,rockImage);
		scenery[2] = new LakeSprite(220,150,lakeImage);
		scenery[3] = new WallSprite(0,0,5,299);
		scenery[4] = new WallSprite(0,0,399,5);
		scenery[5] = new WallSprite(0,294,399,5);
		scenery[6] = new WallSprite(394,0,5,299);

		tree = new TreeSprite(5,5,scenery,treeImage,burnImage);
		fire = new FireSprite(320,100,scenery,tree,fireImage,smokeImage);
	}

	private boolean lostGame()
	{
		return !tree.isAlive();
	}

	private boolean wonGame()
	{
		return tree.isAlive() && !fire.isAlive();
	}

	public void paintComponent(Graphics g)
	{
		render(bufgr);
		g.drawImage(buffer,0,0,null);
	}

	public void render(Graphics g)
	{
		//System.out.println("Begin paint");
		g.setColor(Color.white);
		g.fillRect(0,0,400,300);
		//System.out.println("Begin scenery");
		for (int i = 0; i < scenery.length; i++)
		{
			//System.out.println("In paint loop, i="+i);
			scenery[i].paint(g, boxDebug);
		}
		fire.paint(g, boxDebug);
		tree.paint(g, boxDebug);

		if (wonGame())
		{
			g.setColor(Color.black);
			g.setFont(font);
			g.drawString("You win!",100,100);
		}

		if (lostGame())
		{
			g.setColor(Color.black);
			g.setFont(font);
			g.drawString("You lose.",100,100);
		}
	}

	private class mseL extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			//This is kind of clumsy, but it works.
			// It would be better to keep track of whether or
			//not the applet has focus (with FocusListener)
			//and then either requestFocus or headTo as
			//appropriate.
			//
			requestFocus();
			tree.headTo(e.getX(),e.getY());
		}
	}

	private class keyL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			switch (key)
			{
				case KeyEvent.VK_B: boxDebug = true;
									break;
				case KeyEvent.VK_S: FFPanel.BBscale -= 0.1f;
									boxDebug = true;
									break;
				case KeyEvent.VK_G: FFPanel.BBscale += 0.1f;
									boxDebug = true;
									break;
			}
		}
		public void keyReleased(KeyEvent e)
		{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_B)
				boxDebug = false;
		}
	}
}

class WallSprite extends Sprite
{
  int width, height;
  Color color = Color.black;

	public WallSprite(int x1, int y1, int w, int h)
	{
		super(x1, y1, null);  // The walls don't have images
		width = w;
		height = h;
		resume();
	}

	public Rectangle getBoundingBox()
	{
		return new Rectangle((int)x, (int)y, width, height);
	}
}

class RockSprite extends Sprite
{
	public RockSprite(int x, int y, Image i)
	{
		super(x, y, i);
		resume();
	}
}

class LakeSprite extends Sprite
{
	public LakeSprite(int x, int y, Image i)
	{
		super(x, y, i);
		resume();
	}
}

class ActorSprite extends Sprite
{
	Sprite scenery[];
	boolean alive = true;
	float speed = 0.0f;  // This will be reset in subclasses
	int counter;

	ActorSprite(int x1, int y1, Sprite s[], Image i)
	{
		super(x1, y1, i);
		scenery = s;
		setVelocity(0.0f,0.0f);
		resume();
	}

	public void headTo(float x1, float y1)
	{
		float vx = x1 - x;
		float vy = y1 - y;
		float len = (float)Math.sqrt(vx*vx+vy*vy);
		setVelocity(vx*speed/len, vy*speed/len);
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public boolean collision(Sprite h)
	{
		Rectangle bb = getBoundingBox();
		return bb.intersects(h.getBoundingBox());
	}

}

class TreeSprite extends ActorSprite
{
	Image burnImage;

	TreeSprite(int x1, int y1, Sprite s[], Image i, Image bi)
	{
		super(x1, y1, s, i);
		burnImage = bi;
		speed = 5.0f;
	}

	public void updateSprite()
	{
		if (!active)
			return;
		if (alive)
		{
			updatePosition();
			for (int i = 0; i < scenery.length; i++)
			{
				if (collision(scenery[i]))
				{
					// The tree is wood, hence floats on water
					if (scenery[i] instanceof LakeSprite)
						continue;
					// Hit something solid, back up and stop
					x -= dx; y -= dy;
					setVelocity(0.0f,0.0f);
				}
			}
		}
		else
		{
			// We're on fire - wait a bit, then go away
			counter--;
			if (counter <= 0)
				suspend();
		}
	}

	public void burn()
	{
		setVelocity(0.0f,0.0f);
		alive = false;
		image = burnImage;
		counter = FFPanel.DYING;
	}
}

class FireSprite extends ActorSprite
{
	TreeSprite tree;
	Image smokeImage;

	static final int PERSISTENCE = 20;

	FireSprite(int x1, int y1, Sprite s[], TreeSprite t, Image i, Image si)
	{
		super(x1, y1, s, i);
		tree = t;
		smokeImage = si;
		speed = 7.0f;
	}

	public void updateSprite()
	{
		if (!active)
			return;
		counter--;
		if (alive)
		{
			if (counter <= 0)
			{
				// Reset velocity
				headTo(tree.getX(), tree.getY());
				counter = PERSISTENCE;
			}
			updatePosition();
			for (int i = 0; i < scenery.length; i++)
			{
				if (collision(scenery[i]))
				{
					// The fire is quenched by water
					if (scenery[i] instanceof LakeSprite)
					{
						setVelocity(0.0f,0.0f);
						alive = false;
						image = smokeImage;
						counter = FFPanel.DYING;
					}
					else
					{
						// Hit something solid, back up and stop
						x -= dx; y -= dy;
						setVelocity(0.0f,0.0f);
					}
				}
			}
			// Check the tree
			if (collision(tree))
			{
				tree.burn();
				suspend();
			}
		}
		else
		{
			// We're quenched - wait a bit, then go away
			if (counter <= 0)
				suspend();
		}
	}
}