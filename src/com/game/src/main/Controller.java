package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;



public class Controller 
{

	// This stores all of our bullet objects; 
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();
	
	Random r = new Random();
	
	Bullet TempBullet;
	Enemy TempEnemy;

	Game game;
	Textures tex;



	public Controller(Game game, Textures tex)
	{

		this.game = game;
		this. tex = tex;
		
		
		addEnemy(new Enemy(r.nextInt(Game.WIDTH * game.SCALE), 0, tex));

	}



	public void tick()
	{

		// Grabs the bullet from the List
		for(int i = 0; i < b.size(); i++)
		{

			TempBullet = b.get(i);

			if(TempBullet.getY() < 0)
			{
				removeBullet(TempBullet);
			}

			TempBullet.tick();

		}
		
		// Grabs the enemy from the List
		for(int i = 0; i < e.size(); i++)
		{

			TempEnemy = e.get(i);

			TempEnemy.tick();

		}
		
	}	



		public void render(Graphics g) 
		{

			// Grabs the bullet from the List
			for(int i = 0; i < b.size(); i++)
			{

				TempBullet = b.get(i);

				TempBullet.render(g);

			}
			
			// Grabs the enemy from the List
			for(int i = 0; i < e.size(); i++)
			{

				TempEnemy = e.get(i);

				TempEnemy.render(g);

			}

		}



		public void addBullet(Bullet bullet) 
		{

			b.add(bullet);

		}

		
		
		public void removeBullet(Bullet bullet) 
		{

			b.remove(bullet);

		}



		public void addEnemy(Enemy bullet) 
		{

			e.add(bullet);

		}

		
		
		public void removeBullet(Enemy bullet) 
		{

			e.remove(bullet);

		}
		
	}
