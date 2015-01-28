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
	private int x, y, speedX = 0, speedY = 10;
	private static final int radius = 20, up = 2, down = 1;
	private int bounces;
	
	int moving = down;
	
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
		//Start moving
		x += speedX;
		
		
		//Check to see if the ball has reached the edge of the 
		switch(moving)
		{
		case down: y += speedY;
		}
		//Bounce the ball and decrease the speed to 0 in increments of -2
		
		
	}
	
	//Keeps track of the number of bounces
	public void bounces()
	{
		bounces++;
	}
}
