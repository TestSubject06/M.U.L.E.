/*
 * This class exists solely to serve as the entry point and kick the game off.
 */
package mule;

import gears.GGame;

import java.awt.Color;

/**
 * This class serves as the entry point for the application.
 * @author Zack
 */
@SuppressWarnings("serial")
public class Mule extends GGame{
	/**
	 * Entry Point
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Mule mule = new Mule();
	}

	/**
	 * This just literally kicks off the Gears engine with some defaults.
	 * The starting state, the height and width of the game, and the default
	 * background color of the game.
	 */
	public Mule(){
		//Game start.
		super(new MainMenu(), 640, 480, Color.black);
	}
}
