package ClassAssignments.CarDrivingDemo;
import java.awt.Color;
import java.awt.Graphics;

public class Building {
	int x,y;
	int size;
	
	public Building(int x1, int y1, int s1)
	{
		x = x1;
		y = y1;
		size = s1;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(x, y,  size, size);
	}
}