import java.awt.Color;
import java.awt.Graphics;

public class Car {
	int cx, cy, size;
	Color color = Color.red;

	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int LEFT = 3;
	public static final int DOWN = 4;
	int state = RIGHT;
	int xlo,xhi,ylo,yhi;

	public Car(int x1, int y1,int s1)
	{
		cx = x1;
		cy = y1;
		size = s1;
		xlo = cx;
		yhi = cy;
		xhi = cx + 130;
		ylo = cy - 130;
	}
	
	public Car(int s1, Building b, int gap)
	{
		size = s1;
		cx = b.x - size - gap;
		cy = b.y + b.size + gap;
		int side = size + b.size + 2*gap;
		xlo = cx;
		yhi = cy;
		xhi = cx + side;
		ylo = cy - side;
	}

	public void paint(Graphics g)
	{
		g.setColor(color);
		g.fillRect(cx,  cy,  size,  size);
	}

	public void update()
	{
		switch(state)
		{
		case RIGHT: cx += 2;
					if (cx >= xhi)
						state = UP;
					break;
		case UP: cy -= 2;
					if (cy <= ylo)
						state = LEFT;
					break;
		case LEFT: cx -= 2;
					if (cx <= xlo)
						state = DOWN;
					break;
		case DOWN: cy += 2;
					if (cy > yhi)
						state = RIGHT;
					break;    			
		}
	}
}