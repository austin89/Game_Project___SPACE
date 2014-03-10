package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Player 
{

	private double x;
	private double y;
	
	// Help make the keyboard input movement less choppy
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	
	public Player(double x, double y, Textures tex)
	{
		
		this.x = x;
		this.y = y;
		this.tex = tex;
		
	}
	
	
	
	public void tick()
	{
		
		// Putting animation to image
		//x++;
		x += velX;
		y += velY;
		
		if(x <= 0)
		{
			x = 0;
		}
		
		if(x >= 620)
		{
			x = 620;
		}
		
		if(y < 0)
		{
			y = 0;
		}
			
		if(y >= 448)
		{
			y = 448;
		}
		
	}
	
	
	
	public void render(Graphics g)
	{
		
		g.drawImage(tex.player, (int)x, (int)y, null);
		
	}
	
	
	
	public double getX()
	{
		
		return x;
		
	}
	
	
	
	public double getY()
	{
		
		return y;
		
	}
	
	
	
	public void setX(double x)
	{
		
		this.x = x;
		
	}
	
	
	
	public void setY(double y)
	{
		
		this.y = y;
		
	}
	
	
	
	public void setVelX(double velX)
	{
		
		this.velX = velX;
		
	}
	
	
	
	public void setVelY(double velY)
	{
		
		this.velY = velY;
		
	}

}
