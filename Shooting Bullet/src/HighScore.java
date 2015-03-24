import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;


public class HighScore 
{
//	public static void main(String[] args)
//	{
//		HighScore score = new HighScore();
//	}
	
	private JFrame frame = new JFrame("High Scores");
	private DefaultListModel hsModel = new DefaultListModel();
	private JList hsList = new JList();
	private int width = 400, height = 450;
	
	private JButton reset = new JButton("Replay");
	
	public HighScore()
	{
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
	}

}
