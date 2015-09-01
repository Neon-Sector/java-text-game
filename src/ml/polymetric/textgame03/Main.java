package ml.polymetric.textgame03;

import java.util.Random;

public class Main
{
	private static Game		game	= new Game();	// Create new Game object
    private static Random	r		= new Random(); // Create a Random object for generating random numbers, etc.
	
	public static void main(String[] args)
	{
		game.start();
		return;
		// TODO Fix exit code
	}
	
	public static Game getGame()
	{
		return game;
	}
}
