import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball 
{
	private int score, x, y, foodX, foodY, diameter = 20, diameter2 = 10, vx = 0, vy = 15;
	private boolean hitSide, isConsumed;
//	private static final int up = 1, down = 2, left = 3, right = 4;
//	private int state = down;
	private Random rand = new Random();
	private static final int up = 1, down = 2, left = 3, right = 4;
	private int state = down;
	
	boolean upKey = false, leftKey = false, downKey = false, rightKey = false;
	
	//Ball constructor
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		foodX = rand.nextInt(300);
		foodY = rand.nextInt(300);
	}
	
	//Draws a ball
	public void drawBall(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(x, y, diameter, diameter);
	}
	
	//Draws food for the ball
	public void drawFood(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(foodX, foodY, diameter2, diameter2);
	}
	
	//Draws the score
	public void drawScore(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawString("Score: "+score, 30, 40);
	}
	
	//Moves the ball on key press
	public void moveBall()
	{
		x += vx;
		y += vy;
		//State is down
		
		if(upKey)
		{
			vy = -(vy);
			//ball is going up
		}
		
		if(leftKey)
		{
			vy = 0;
			vx = 15;
			//ball is going left
		}
		
		if(rightKey)
		{
			vy = 0;
			vx = -15;
			//ball is going right
		}
		
		if(downKey)
		{
			vx = 0;
			vy = 15;
			//ball is going down
		}
	}
	
	//Checks to see if the ball hit a side
	public boolean outOfBounds()
	{
		if(x < 0)
		{
			hitSide = true;
		}
		if(x + diameter > HungryBallPanel.BOXSIZE)
		{
			hitSide = true;
		}
		if(y < 0)
		{
			hitSide = true;
		}
		if(y + diameter > HungryBallPanel.BOXSIZE)
		{
			hitSide = true;
		}
		
		return hitSide;
	}
	
	//Increases the score 
	public void increaseScore()
	{
		score = score + 1;
	}
	
	//Gets the score
	public int getScore()
	{
		return score;
	}

//	Set the up,left,down,right keys********
	public void set_up(boolean val)
	{
		upKey = val;
	}
	
	public void set_down(boolean val)
	{
		downKey = val;
	}
	
	public void set_left(boolean val)
	{
		leftKey = val;
	}
	
	public void set_right(boolean val)
	{
		rightKey = val;
	}
//******************************************
	//Checks to see if the ball and the food touched
	public void eat()
	{
		/*
		 * if the ball hits the food then the food will vanish and increaseScore will be called, the ball will grow, 
		 * and the food will appear in another random spot
		 * 
		 * 1.) Determine if there is a collision between the balls
		 * 2.) If there is, make the food vanish and reappear somewhere else, make the main ball grow
		 * 
		 */
		
		
		
		
	}
}
