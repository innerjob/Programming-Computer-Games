import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * @author Sam Redmond
 */
public class Ball 
{
	private int x, y;
	private int vx = 0, vy = 10;
	private static final int radius = 20, up = 1, down = 2, left = 3, right = 4;
	//Figure out the edges of the screen
	private int bottomEdge, topEdge, rightEdge, leftEdge;  
	//Count the number of bounces
	private int counter;
	//private Random rand = new Random();
	private int state = down;
	
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
	
	//Draws The Score On The Screen
	public void drawScore(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif",Font.PLAIN, 24));
		g.drawString(""+counter,60, 60);
	}
	
	//Move the ball
	public void move()
	{
		x += vx;
		y += vy;
		
		//If the ball bounces off of a wall of the panel increase counter by 1 and bounce the ball in the opposite direction
		if(x - radius < 0)
		{
			vx = -vx;
			x = radius;
			counter++;
		}
		if(x + radius > MovingBall.BOXSIZE)
		{
			vx = -vx;
            x = MovingBall.BOXSIZE - radius;
            counter++;
		}
		if(y - radius < 0)
		{
			vy = -vy;
			y = radius;
			counter++;
		}
		if(y + radius > MovingBall.BOXSIZE)
		{
			vy = -vy;
			y = MovingBall.BOXSIZE - radius;
			counter++;
		}
		
		
	}
	
	//Reverses The Direction Of The Ball
	public void reverse(MouseEvent e)
	{	
		switch(state)
		{
		//if the mouse is clicked make it go left
		case down: 
			if(e.isConsumed())
			{
				x -= 10;
				y += 0;
				state = left;
			}
			break;
		//if the mouse is clicked make it go up
		case left: 
			if(e.isConsumed())
			{
				x += 0;
				y -= 10;
				state = up;
			}
			break;
		//if the mouse is clicked make it go right
		case up: 
			if(e.isConsumed())
			{
				x += 10;
				y += 0;
				state = up;
			}
			break;
		//if the mouse is clicked make it go down
		case right: 
			if(e.isConsumed())
			{
				x += vx;
				y += vy;
				state = up;
			}
			break;
		}
	}
}
