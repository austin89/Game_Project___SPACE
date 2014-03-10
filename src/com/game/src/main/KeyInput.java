package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



// Handles keyboard input; Whenever a key is pressed this class is called
public class KeyInput extends KeyAdapter
{
	
	Game game;
	
	// Calls keyboard input within the game class
	public KeyInput(Game game)
	{
		
		this.game = game;
		
	}
	
	
	
	// When key is pressed calls the method in
	// Game
	public void keyPressed(KeyEvent e)
	{
		
		game.keyPressed(e);
		
	}
	
	
	
	// When key is released calls the method in
	// Game
	public void keyReleased(KeyEvent e)
	{
		
		game.keyReleased(e);
		
	}
	
}
