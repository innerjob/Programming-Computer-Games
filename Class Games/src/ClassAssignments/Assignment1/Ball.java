package ClassAssignments.Assignment1;

import java.awt.Color;
import java.awt.Graphics;


public class Ball 
{
	private int x;
	private int y;
	private int width;
	private int height;
	Color color = Color.red;
	
	public Ball(int x1, int y1, int width1, int height1)
	{
		x = x1;
		y = y1;
		width = width1;
		height = height1;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
		
	}
}
