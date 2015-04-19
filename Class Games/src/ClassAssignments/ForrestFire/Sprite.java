package ClassAssignments.ForrestFire;

/*

 * Sprite.java
 *
 * Define a base Sprite class to represent moving
 * shapes in a game.
 *
 * written by mike slattery - sep 2002
 * merged BitmapSprite into this base class - mcs, sep 2005
 * merged Hittable - mcs, feb 2011
 */
import java.awt.*;

public class Sprite
{
  float x,y;
  float dx,dy;

  Image image;

  boolean active=false, visible=false;

  public Sprite(int x1, int y1, Image i) {
	  x = (float)x1;
	  y = (float)y1;
	  image = i;
  }

  void updatePosition() {
  	x += dx;
  	y += dy;
  }

  void setPosition(float a, float b) {
  	x = a; y = b;
  }

  void setVelocity(float a, float b) {
    dx = a; dy = b;
  }

  boolean isActive() {
  	return active;
  }

  void suspend() {
    active = false; visible = false;
  }

  void resume() {
    active = true; visible = true;
  }

  void updateSprite() {}

  public void paint(Graphics g) {
       if (visible) {
         g.drawImage(image, (int)x, (int)y, null);
       }
  }

  public void paint(Graphics g, boolean debug)
  	{
  		paint(g);
  		if (debug)
  		{
  			g.setColor(Color.black);
  			Rectangle bb = getBoundingBox();
  			g.drawRect(bb.x, bb.y, bb.width, bb.height);
  		}
	}

  public Rectangle getBoundingBox()
  	{
  		int width = image.getWidth(null);
  		int height = image.getHeight(null);
  		int xoff = (int)(width*(1.0f - FFPanel.BBscale)/2.0f);
  		int yoff = (int)(height*(1.0f - FFPanel.BBscale)/2.0f);
  		int bbw = (int)(width*FFPanel.BBscale);
  		int bbh = (int)(height*FFPanel.BBscale);
  		return new Rectangle((int)x+xoff, (int)y+yoff, bbw, bbh);
	}
}
