import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Pong.java
 *
 * Pong game for two human players.
 *
 * @author mike slattery
 * @version jan.2007
 */

public class Pong {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	public static final int EDGE = 30;

	public static void main(String[] args) {
		JFrame f = new JFrame("Pong");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new PongPanel();
		f.add(panel, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
}

class PongPanel extends JPanel implements Runnable {

	public static final int FRAME_DELAY = 30;

	Ball ball;
	Paddle pleft, pright;
	ScoreKeeper score;

	Thread anim;

	PongPanel() {
		super();
		setPreferredSize(new Dimension(Pong.WIDTH, Pong.HEIGHT));

		ball = new Ball();
		pleft = new Paddle(Pong.EDGE);
		pright = new Paddle(Pong.WIDTH - Pong.EDGE);
		score = new ScoreKeeper();

		KeyListener watcher = new KeyL();
		addKeyListener(watcher);
		addMouseListener(new MseL());
	}

	public void addNotify() {
		super.addNotify();

		anim = new Thread(this);
		anim.start();
	}

	public void run() {
		while (anim != null) {
			gameUpdate();
			repaint();
			try {
				Thread.sleep(FRAME_DELAY);
			} catch (InterruptedException e) {}
		}
	}

	void gameUpdate() {
		pleft.move();
		pright.move();
		ball.move();
		ball.checkHit(pleft);
		ball.checkHit(pright);
		checkScore();
	}

	void checkScore() {
		boolean scored = false;
		if (ball.getX() < Pong.EDGE) {
			score.bumpRight();
			scored = true;
		}
		if (ball.getX() > Pong.WIDTH - Pong.EDGE) {
			score.bumpLeft();
			scored = true;
		}
		if (scored)
			ball.reset();
	}



	public void paintComponent(Graphics g){
		g.setColor(Color.cyan);
		g.fillRect(0, 0, Pong.WIDTH, Pong.HEIGHT);

		pleft.draw(g);
		pright.draw(g);
		score.draw(g);
		ball.draw(g);
	}

	// Use an inner class for the key listener
	class KeyL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int c = e.getKeyCode();
			switch (c) {
			case KeyEvent.VK_UP: pleft.setUpKey(true);
			break;
			case KeyEvent.VK_DOWN: pleft.setDownKey(true);
			break;
			case KeyEvent.VK_SEMICOLON: pright.setUpKey(true);
			break;
			case KeyEvent.VK_PERIOD: pright.setDownKey(true);
			break;
			}
		}

		public void keyReleased(KeyEvent e) {
			int c = e.getKeyCode();
			switch (c) {
			case KeyEvent.VK_A: pleft.setUpKey(false);
			break;
			case KeyEvent.VK_Z: pleft.setDownKey(false);
			break;
			case KeyEvent.VK_SEMICOLON: pright.setUpKey(false);
			break;
			case KeyEvent.VK_PERIOD: pright.setDownKey(false);
			break;
			}
		}
	}

	class MseL extends MouseAdapter {
		public void mousePressed(MouseEvent e){
			requestFocus();
		}
	}
}