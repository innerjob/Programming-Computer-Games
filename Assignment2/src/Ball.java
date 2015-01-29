import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author Sam
 *	
 *	This class draws the ball, makes it bounce, and keeps track of the number of bounces
 *
 */
public class Ball 
{
	private int x, y;
	private static final int radius = 20;
	private int moves;
	Random rand = new Random();
	
	//Constructor that makes the ball
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}
	
	//Draws the ball
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(x, y, radius, radius);
	}
	
	public void drawMoves(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.BOLD, 24));
		g.drawString(""+moves, x, y);
	}
	
	//Move the ball
	public void move()
	{
		x = rand.nextInt(400);
		y = rand.nextInt(400);
	}
	
	//Keeps track of the number of moves made
	public void moves()
	{
		moves+=1;
	}
}
