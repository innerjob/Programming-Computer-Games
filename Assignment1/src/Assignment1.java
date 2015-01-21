import java.awt.*;

import javax.swing.*;;

/**
 * 
 * @author Sam Redmond
 * 
 *
 */
public class Assignment1 
{
	private static JFrame frame;
	
	//Store Circles And Lines In An Array
	private Circle[] circles = new Circle[2];
	private Line[] lines = new Line[2];
	
	public static void paintComponent(Graphics g)
	{
		g.setColor(Color.BLUE);
		for(int i = 0; i <= 3; i++)
		{
			//paint the circles on the screen
		}
	}
	
	public static void main(String args)
	{
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100,100,500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graphics g = null;
		paintComponent(g);
		
	}

}

//Create A Circle
class Circle 
{
	//Default Constructor
	public Circle()
	{
		
	}
	
	//Constructor 
	public Circle(int x, int y)
	{
		
	}
}

//Create A Line
class Line
{
	//Default Constructor
	public Line()
	{
		
	}
	//Constructor 
	public Line(int x, int y)
	{
		
	}
}
