package ClassAssignments.CarDrivingDemo;
import java.awt.*;
import javax.swing.*;

/**
 * Animation example with Finite State Machine
 * Define track of car relative to a building.
 * 
 * @author mike slattery
 * @version jan.2015
 */


public class DrivingAroundBldg {

   public static void main(String[] args) {
		JFrame f = new JFrame("Driving in the City");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrivingABPanel panel = new DrivingABPanel();
		f.add(panel, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
   }
}


class DrivingABPanel extends JPanel implements Runnable {

  Thread anim = null;  // animation thread
  Car c,c2; 
  Building[] bldgs = new Building[4];

  private static final int PWIDTH = 500; // size of panel
  private static final int PHEIGHT = 500;

  // off screen rendering
  private Graphics dbg;
  private Image dbImage = null;

  public DrivingABPanel() {
    super();
  	bldgs[0] = new Building(50,50,150);
  	bldgs[1] = new Building(250,50,150);
  	bldgs[2] = new Building(50,250,150);
  	bldgs[3] = new Building(250,250,150);
    // Starting position of cars set in Car constructor
  	c = new Car(30, bldgs[1], 10);
  	c2 = new Car(30, bldgs[0], 10);
  	
  	setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
  }

  public void addNotify() {
	  super.addNotify();
	  start();
  }

  private void start() {
  	anim = new Thread(this);
  	anim.start();
  }

  protected void paintComponent(Graphics g) {

    if (dbImage == null){
        dbImage = createImage(PWIDTH, PHEIGHT);
        dbg = dbImage.getGraphics();
    }

    // First, render on offscreen buffer

    // Draw a black background
    dbg.setColor(Color.black);
    dbg.fillRect(0,0,450,450);

    // Draw some buildings
    for (Building b:bldgs)
    	b.paint(dbg);

    // Draw cars
    c.paint(dbg);
    c2.paint(dbg);

    // Then copy the buffer to the screen
    g.drawImage(dbImage, 0, 0, null);
  }

  public void run() {

    while (anim != null)
    {
      c.update();
      c2.update();
      repaint();
      try {
      	Thread.sleep(40);
      } catch (InterruptedException e) {}
    }
  }

  public void stop() {
    anim = null;  // stop animation thread
  }
}