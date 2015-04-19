package ClassAssignments.Assignment1;

import java.util.*;

import javax.swing.*;

import java.awt.*;

public class Assignment1 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Sam Redmond: Assignment 1");
		frame.setVisible(true);
		frame.setBounds(100,100,500,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PANEL p = new PANEL();
		frame.add(p, BorderLayout.CENTER);
	}
}

class PANEL extends JPanel
{

	int panelSize = 500;
	
	Random rand = new Random();
	
	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Line> lines = new ArrayList<Line>();
	
	private Graphics graph;
	private Image graphImage;
	
//	Ball b;
	
	public PANEL()
	{
		super();
		
		//Add Balls to the list
		for(int i = 0; i < 10; i++)
		{
			balls.add(new Ball(rand.nextInt(500), rand.nextInt(500), 50, 50));
		}
		
		//Add Lines To The List
		for(int i = 0; i < 10; i++)
		{
			lines.add(new Line(rand.nextInt(500),rand.nextInt(500),rand.nextInt(500),rand.nextInt(500)));
		}
		
		setPreferredSize(new Dimension(panelSize, panelSize));
	}
	
	protected void paintComponent(Graphics g)
	{
		if(graph == null)
		{
			graphImage = createImage(panelSize, panelSize);
			graph = graphImage.getGraphics();
		}
		
		graph.setColor(Color.white);
		graph.fillRect(0, 0, 500, 500);
		
		//paint the balls
		for(Ball b : balls)
		{
			b.paint(graph);
		}
		
		for(Line l : lines)
		{
			graph.drawLine(rand.nextInt(500), rand.nextInt(500), rand.nextInt(500), rand.nextInt(500));
		}
		
//		b.paint(graph);
		
		g.drawImage(graphImage, 0, 0, null);
	}
	
}