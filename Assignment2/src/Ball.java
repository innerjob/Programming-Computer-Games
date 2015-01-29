import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author Sam Redmond
 */
public class Ball 
{
	private int x, y;
	private int vx = 0, vy = 10;
	private static final int radius = 20, up = 1, down = 2, left = 3, right = 4;
	private int counter;
	private int originalPosX, originalPosY;
	private Random rand = new Random();
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
	
	//Move the ball
	public void move()
	{
		x += vx;
		y += vy;
		
	}
	
	//Reverses The Direction Of The Ball
	public void reverse()
	{	
		switch(state)
		{
		case down: //if the mouse is clicked make it go left
			break;
		case left: //if the mouse is clicked make it go up
			break;
		case up: //if the mouse is clicked make it go right
			break;
		case right: //if the mouse is clicked make it go down
		}
	}
}
