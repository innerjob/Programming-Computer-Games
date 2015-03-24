import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * ROUGH DRAFT
 * @author Sam Redmond
 * 
 *This is the Bullet class that will create a bullet that will be a drawn image of a bullet
 */
public class Bullet 
{
	private int acceleration = 1;
	private int speed = 2;
	private int score;
	private int x = 237, y = 150, diameter = 26; //Starting position of the bullet
	
	//Bullet Constructor
	public Bullet()
	{
		
	}
	
	//Moves the bullet
	public void move()
	{
		x+=3;//Move the bullet to the right
		if((y > 0) && y < 300)//For now I will just use the top and bottom of the screen as parameters for losing, adding more stuff as I go along
		{//Later the bullet will have a bounding box around it and so will all of the other game objects to check for collisions
			speed += acceleration;
			y += speed;
		}else
		{
			
		}
	}
	
	//Get The Score
	public int getScore()
	{
		return score;
	}
	
	public boolean hit()
	{
		boolean hit = false;
		
		if((y > 0) && y<300)
		{
			hit = false;
		}else
		{
			hit = true;
		}
		
		return hit;
	}
	
	//This will be called on mouse click
	public void jump()
	{
		speed = -17;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x, y, diameter, diameter);
	}
	
	//Add 1 to the score while the thread is running
	public void addScore()
	{
		score = score + 1;
	}
	
	public void drawScore(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif",Font.PLAIN, 24));
		g.drawString("Score: "+score, 350, 60);
	}
	
	//Resets the game after the bullet touches something
	public void reset()
	{
		y = 150;
		speed = 2;
		score = 0;//Reset the score when the user loses
	}
}
