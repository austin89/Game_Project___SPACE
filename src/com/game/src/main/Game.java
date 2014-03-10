package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;



public class Game extends Canvas implements Runnable
{

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Game";
	
	private boolean is_shooting = false; // stops from full-auto shooting
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null; // takes in sprite sheet
	private BufferedImage background = null; // takes in background image
	
	private Player p;
	private Controller c;
	private Textures tex;
	
	// Temporary
	//private BufferedImage player;
	
	
	
	public void init()
	{
		
		// This is so you don't have to click the screen to start playing
		requestFocus();
		
		// Reads in the sprite_sheet that holds our images we made
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// Temporary
		//SpriteSheet ss = new SpriteSheet(spriteSheet);
		//player = ss.grabImage(1, 1, 32, 32);
		
		addKeyListener(new KeyInput(this));
		
		tex = new Textures(this);
		
		p = new Player(200, 200, tex);
		c = new Controller(this, tex);
		
	}
	
	
	
	private synchronized void start()
	{
		
		if(running)
		{
			return;
		}
			
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	
	
	private synchronized void stop()
	{
		
		if(!running)
		{
			return;
		}
		
		running = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	public void run() 
	{
		
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1)
			{	
				// Refreshing 60 frames-a-sec
				tick();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	
	
	private void tick()
	{
		
		// Calling the method in Player; Refer to Run()
		p.tick();
		c.tick();
		
	}
	
	
	
	private void render()
	{
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			// Going to have 3 buffers; 
			// Loading the image behind the screen image
			// Then loading an image behind that one
			createBufferStrategy(3);  
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.setColor(Color.blue);
		g.fillRect(0, 0, 800, 800);
		//Temporary
		//graphics.drawImage(player, 100, 100, this);
		
		g.drawImage(background, 0, 0, null);
		
		p.render(g);
		c.render(g);
		
		g.dispose();
		bs.show();
		
	}

	
	
	public void keyPressed(KeyEvent e)
	{
		
		int key = e.getKeyCode();
		
		// Sense if key right is pressed
		if(key == KeyEvent.VK_RIGHT)
		{
			p.setVelX(5);
		}
		
		// Sense if key left is pressed
		else if(key == KeyEvent.VK_LEFT)
		{
			p.setVelX(-5);
		}
		
		// Sense if key down is pressed
		else if(key == KeyEvent.VK_DOWN)
		{
			p.setVelY(5);
		}
		
		// Sense if key up is pressed
		else if(key == KeyEvent.VK_UP)
		{
			p.setVelY(-5);
		}
		
		// Sense if key spacebar is pressed
		else if(key == KeyEvent.VK_SPACE && !is_shooting)
		{
			is_shooting = true;
			c.addBullet(new Bullet(p.getX(), p.getY(), tex));
		
		}
		
	}
	
	
	
	public void keyReleased(KeyEvent e)
	{
		
		int key = e.getKeyCode();
		
		// Sense if key is moving right
		if(key == KeyEvent.VK_RIGHT)
		{
			p.setVelX(0);
		}
		
		// Sense if key is moving left
		else if(key == KeyEvent.VK_LEFT)
		{
			p.setVelX(0);
		}
		
		// Sense if key is moving down
		else if(key == KeyEvent.VK_DOWN)
		{
			p.setVelY(0);
		}
		
		// Sense if key is moving up
		else if(key == KeyEvent.VK_UP)
		{
			p.setVelY(0);
		}
		
		// Forces player to release spacebar to shoot another bullet
		else if(key == KeyEvent.VK_SPACE)
		{
			is_shooting = false;
		}
		
	}
	
	
	
	public static void main(String args[])
	{
		
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();

	}
	
	
	
	public BufferedImage getSpriteSheet()
	{
		
		return spriteSheet;
		
	}

}
