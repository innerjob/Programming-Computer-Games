import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Sam
 *	
 *	This class draws the ball, makes it bounce, and keeps track of the number of bounces
 *
 */
public class Ball 
{
	private int x, y, speedX, speedY;
	private static final int radius = 20;
	private int bounces;
	
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
	
	//Bounces the ball
	public void bounce()
	{
		x += 0;
		y += 10;
	}
	
	//Keeps track of the number of bounces
	public void bounces()
	{
		bounces++;
	}
}
