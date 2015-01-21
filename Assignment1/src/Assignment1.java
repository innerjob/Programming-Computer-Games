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
	
	
	public static void paintCircles(Graphics g)
	{
		for(int i = 0; i <= 3; i++)
		{
			//paint the circles on the screen
		}
	}
	
	public static void paintLines(Graphics g)
	{
		for (int i = 0; i <=3; i++)
		{
			//paint the lines
		}
	}
	
	public static void main(String args)
	{
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100,100,500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graphics g = null;
		Graphics g1 = null;
		paintCircles(g);
		paintLines(g1);
		
	}

}
