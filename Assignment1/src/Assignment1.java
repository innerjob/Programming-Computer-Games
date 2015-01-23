import java.util.*;

import javax.swing.*;

import java.awt.*;

public class Assignment1 
{
	private static Graphics graph;
	
	public static void paintComponent(Graphics g)
	{
		
		Random rand = new Random();
		ArrayList<Ball> balls= new ArrayList<Ball>();
		for(int i = 0; i < 10; i++)
		{
			balls.add(new Ball(rand.nextInt(100), rand.nextInt(100), 40, 40));
		}
		for(Ball b : balls)
			b.paint(graph);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Sam Redmond: Assignment 1");
		JPanel panel = new JPanel();
		
		paintComponent(graph);
		
		
		
		
		
		frame.setVisible(true);
		frame.setBounds(100,100,500,500);
		frame.setResizable(false);
		
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
}
