package ClassAssignments.PongDemo;

import java.awt.*;

public class Paddle {
   int x, y;

   boolean upKey = false, downKey = false;

   final static int WIDTH = 6;
   final static int HEIGHT = 40;
   final static int SPEED = 4;

   public Paddle(int x1){
      x = x1;
      y = Pong.EDGE;
   }

   public void move(){
	   if (upKey && getTop()>0)
	       y -= SPEED;
	   if (downKey && getBottom()<Pong.HEIGHT)
	       y += SPEED;
   }

   public void draw(Graphics g){
	   g.setColor(Color.red);
	   g.fillRect(x, y, WIDTH, HEIGHT);
   }

   public void setUpKey(Boolean val){
	   upKey = val;
   }

   public void setDownKey(Boolean val){
	   downKey = val;
   }

   public int getX(){
	   return x-(WIDTH/2);
   }

   public int getTop(){
	   return y;
   }

   public int getBottom(){
	   return y+HEIGHT;
   }
}