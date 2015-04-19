package ClassAssignments.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.Renderer;


public class Game extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH/4*3;
	public static final String TITLE = "Zombies";
	private static Game game = new Game();
	private boolean running = false;
	private Thread thread;
	
	private Render gfx;
	
	public static Game getInstance()
	{
		return game;
	}
	
	
	public void init()
	{
		gfx = new Render();
		
	}
	
	public void tick()
	{
		
	}
	
	public void render()//This will take care of all the stuff in the background
	{
		//Create a buffer strategy
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		///////////////////////////////////////////////////
		
		gfx.renderBackground(g);
		gfx.renderForeground(g);
		
		g.dispose();//Dispose of graphics context and show buffer strategy
		bs.show();
		
	}
	
	//Run Method
	public void run() 
	{
		init();//Initialize the game
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;//Allow ourselves to have 60 ticks in a second
		double n = 1000000000/numTicks;
		double delta = 0;
		
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{
			long currentTime = System.nanoTime();
			delta+=(currentTime-lastTime)/n;
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000)
			{
				timer+=1000;
				System.out.println(ticks + " Ticks, FPS: " + frames);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	//Main Method
	public static void main(String[] args)
	{
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.pack();
		
		game.start();
	}
	

	//Game Constructor
	public Game() 
	{
		
	}

	
	//Start the game running and start the thread
	private synchronized void start()
	{
		if(running)
		{
			return;
		}else
		{
			running = true;
		}
		thread = new Thread(this);
		thread.start();
	}
	//Stops the thread and the game
	private synchronized void stop()
	{
		if(!running)
		{
			return;
		}else
		{
			running = false;
		}
		//Stop the thread from running
		try 
		{
			thread.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(1);
	}
}
