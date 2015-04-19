package ClassAssignments.ClassNotes;
import java.awt.*;
import javax.swing.*;
public class Test extends JPanel
{
	public Test()
	{
		super();
		setPreferredSize(new Dimension(500,500));
	}
	
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(50, 50, 150, 150);
		
		g.setColor(Color.GRAY);
		g.fillRect(50, 50, 150, 150);
		g.fillRect(250, 50, 150, 150);
		g.fillRect(50, 250, 150, 150);
		g.fillRect(250, 250, 150, 150);
		
		g.setColor(Color.RED);
		g.fillRect(210,210,30,30);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("The City");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//CityPanel panel = new CityPanel();
		frame.pack();
		frame.setVisible(true);
	}
	
	class CityPanel extends JPanel
	{
		public CityPanel()
		{
			super();
			setPreferredSize(new Dimension(500,500));
		}
	}
}
