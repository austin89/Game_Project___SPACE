package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Bullet 
{

	private double x;
	private double y;
	
	private Textures tex;
	
	public Bullet(double x, double y, Textures tex)
	{
		
		this.x = x;
		this.y = y;
		this.tex = tex;
		//SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());	
		// Grabs the image in the square from the Spritesheet
		//image = ss.grabImage(2, 1, 32, 32);
		
	}
	
	
	
	public void tick()
	{
		
		// Controls bullet speed
		y -= 1;
		
	}
	
	
	
	public void render(Graphics g)
	{
		
		// Draws graphic to screen
		g.drawImage(tex.missile, (int)x, (int) y, null);
		
	}
	
	
	
	public double getY()
	{
		return y;
	}
	
}
