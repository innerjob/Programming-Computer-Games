import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Sam Redmond
 * This class creates a Ball object and it's methods.
 */
public class Ball 
{
	private int x, y;
	private int vx = 0, vy = 20;
	private int diameter = 50;
	private static final int up = 1;
	private static final int down = 2;
	private static final int left = 3;
	private static final int right = 4; 
	//Count the number of bounces
	private int counter = 10;
	//Score
	private int score;
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
		g.fillOval(x, y, diameter, diameter);
	}
	
	//Draws The Score On The Screen
	public void drawScore(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif",Font.PLAIN, 24));
		g.drawString("Lives: "+counter,10, 60);
		g.drawString("Score: "+score, 350, 60);
	}
	
	//Move the ball
	public void move()
	{
		x += vx;
		y += vy;
		
		/*
		 * If the ball bounces off of a wall of the panel increase counter by 1 and bounce the ball in the opposite direction and increase the speed
		 */
		if(x < 0)//If it bounces off the left side
		{
			vx = -vx;
			x = 0;
			diameter = diameter += 12;//Increase the size of the ball
			counter--;//Take one off the counter
			state = right;//Make sure the program knows which direction the ball is moving
		}
		if(x + diameter> MovingBall.BOXSIZE)//If it bounces off the right side
		{
			vx = -vx;
            x = MovingBall.BOXSIZE - diameter;
			diameter = diameter += 12;//Increase the size of the ball
            counter--;//Take one off the counter
          state = left;//Make sure the program knows which direction the ball is moving
		}
		if(y < 0)//If it bounces off the top
		{
			vy = -vy;
			y = 0;
			diameter = diameter += 12;//Increase the size of the ball
			counter--;//Take one off the counter
			state = down;//Make sure the program knows which direction the ball is moving
		}
		if(y + diameter> MovingBall.BOXSIZE)//if it bounces off the bottom
		{
			vy = -vy;
			y = MovingBall.BOXSIZE - diameter;
			diameter = diameter += 12;//Increase the size of the ball
			counter--;//Take one off the counter
			state = up;//Make sure the program knows which direction the ball is moving
		}
	}
	
	//Get the counter to see when it hits zero
	public int getCounter()
	{
		return counter;
	}
	
	//Reverses The Direction Of The Ball
	public void reverse()
	{	
		switch(state)
		{
		//if the mouse is clicked make it go left
		case down:
				vx = -20;
				vy = 0;
				state = left;
			break;
		//if the mouse is clicked make it go up
		case left: 
				vx = 0;
				vy = -20;
				state = up;
			break;
		//if the mouse is clicked make it go right
		case up: 
				vx = 20;
				vy = 0;
				state = right;
			break;
		//if the mouse is clicked make it go down
		case right: 
				vx = 0;
				vy = 20;
				state = down;
			break;
		}
	}
	
	//Increase Score
	public void increaseScore()
	{
		score = score+1;
	}
	
	//Return the score
	public int getScore()
	{
		return score;
	}
}
