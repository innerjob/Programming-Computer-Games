import java.awt.*;

public class ScoreKeeper {

   int left = 0;
   int right = 0;

   Font font;

   public ScoreKeeper(){
	   font = new Font("SansSerif", Font.BOLD, 24);
   }

   public void draw(Graphics g){
	   g.setColor(Color.black);
	   g.setFont(font);
	   g.drawString(""+left, 2*Pong.EDGE, 2*Pong.EDGE);
	   g.drawString(""+right, Pong.WIDTH - 2*Pong.EDGE, 2*Pong.EDGE);
   }

   public void bumpRight(){
	   right++;
   }

   public void bumpLeft(){
	   left++;
   }

}