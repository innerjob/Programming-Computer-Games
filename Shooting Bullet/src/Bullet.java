import java.awt.Color;
import java.awt.Graphics;

/**
 * ROUGH DRAFT
 * @author Sam Redmond
 * 
 *This is the Bullet class that will create a bullet that will be a drawn image of a bullet
 *
 *
 *
 *For the rough draft this will just be a red dot
 *
 */
public class Bullet 
{
	private int acceleration = 1;
	private int speed = 2;
	
	private int x = 237, y = 150, diameter = 26; //Starting position of the bullet
	
	//Bullet Constructor
	public Bullet()
	{
		
	}
	
	//Moves the bullet
	public void move()
	{
		if((y > 0) && y < 300)//For now I will just use the top and bottom of the screen as parameters for losing, adding more stuff as I go along
		{//Later the bullet will have a bounding box around it and so will all of the other game objects to check for collisions
			speed += acceleration;
			y += speed;
		}else
		{
			reset();
		}
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
	
	//Resets the game after the bullet touches something
	public void reset()
	{
		y = 150;
		speed = 2;
		
	}
}
