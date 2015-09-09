package ml.polymetric.textgame03;

public class Launcher
{
    public static final boolean debugMode = true; // Whether to print debug messages/generate bug report code
	
	private static Game	game = new Game(); // Create new Game object
	
	public static void main(String[] args)
	{
		game.start();
	}
	
	public static Game getGame() {return game;}
}
