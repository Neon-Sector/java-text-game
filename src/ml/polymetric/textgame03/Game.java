package ml.polymetric.textgame03;

import java.util.*;

public class Game implements Runnable
{
    private boolean				running				= false;						// Boolean is true if game thread is running
    private Thread				t					= new Thread(this);				// Thread object
    private Scanner				s					= new Scanner(System.in);		// Scanner for getting console input
    private Random				r					= new Random();					// Random object for generating random numbers, etc.
    private Location			currentLocation;									// Current location object
    private ArrayList<Item>		inventory			= new ArrayList<Item>();		// Item inventory array
    private String[]			command;											// User input/command
    private boolean				commandTrue			= false;						// If the command returned true or not
    private boolean				exit				= false;						// Whether to end the loop or not
    

    // Main game loop method. This is called when start() is called. Do not call it on its own.
    @Override
    public void run()
    {
    	init();
	
    	while (running)
    	{
    		System.out.print("> ");
    		command = s.nextLine().toLowerCase().split(" "); // Get user input command
    		System.out.println();
    		commandTrue = CommandHandler.execute(command);
    		if (commandTrue)
    			printCurrentLocation();
    		if (exit)
    			break;
    			// TODO Fix exit code
    	}
	
    	stop();
    }
    
    // Starts the thread, calls run method.
    public void start()
    {
    	if (running) // If thread is already started, do not start it again
    		return;
    	
    	running = true;
    	
    	t.start();
    }
	
    // Stops the thread.
    public void stop()
    {
    	if (!running) // If thread is already stopped, do not stop it again
    		return;
    	
    	running = false;
    	
    	try
    	{
    		t.join();
    	}
    	catch (InterruptedException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    private void init()
    {
    	// Locations
    	Location emptyRoomSouth = new Location(
    			"Southern Empty Room",
    			"You are in an empty room, with a wooden floor and white painted walls and roof."
    					+ "\nTo the north there is a doorway to a long hallway."
    					+ "\nYou can see another room at the end.");
    	
    	Location emptyRoomNorth = new Location(
    			"Northern Empty Room",
    			"You are in an empty room, with a wooden floor and white painted walls and roof."
    					+ "\nTo the south there is a doorway to a long hallway."
    					+ "\nYou can see another room at the end." + "\nThere is a ladder in the center of the room,"
    					+ "\nLeading upwards and downwards.");
	
    	Location hallway = new Location(
    			"Long Hallway",
    			"You are in a long hallway leading north and south."
    					+ "\nThere are exits to empty rooms at the ends.");
	
    	Location upwardTunnel = new Location(
    			"Upward Tunnel",
    			"You are in a vertical tunnel running upwards."
    					+ "\nYou are climbing on a ladder. You cannot see the top."
    					+ "\nBelow you, you see the tunnel runs through a bright room,"
    					+ "\nand continues downwards into darkness.");
	
    	Location downwardTunnel = new Location(
    			"Downward Tunnel",
    			"You are in a vertical tunnel running downwards."
    					+ "\nYou are climbing on a ladder. You cannot see the top."
    					+ "\nAbove you, you see the tunnel runs through a bright room,"
    					+ "\nand continues upwards into darkness.");
	
    	Location basement = new Location(
    			"Basement",
    			"You are in an old brick room."
    					+ "\nThere is a ladder at the center, leading upwards."
    					+ "\nThere is a grate in the floor, underneath which is a shallow empty sewer system.");
	
    	Location backyard = new Location(
    			"Backyard",
    			"You are outside, in a small grassy back yard.");
	
    	Location houseKitchen = new Location(
    			"Kitchen",
    			"You are in a small kitchen.");
	
    	// Items
    	// TODO Add more items to rooms
    	backyard.addItem(new Item(
    			"House",
    			"The house is wooden, and painted a pale yellow."
    					+ "\nIt appears to be relatively new."
    					+ "\nIt is obvious this family was of average wealth, and had children.",
    			"There is a yellow house to the west.",
    					false));
    	backyard.addItem(new Item(
    			"Playset",
    			"The playset has a old swing and a slide.",
    			"There is a playset here.",
    			false));
    	
    	houseKitchen.addItem(new Item(
    			"Wooden Table",
    			"The table appears to be very old and ornate.",
    			"There is a wooden table here.",
    			false));
	
    	// Exits
    	emptyRoomSouth.addExit(new Exit(Exit.NORTH, hallway));			// North exit from Southern Empty Room to Hallway
    	
    	emptyRoomNorth.addExit(new Exit(Exit.SOUTH, hallway));			// South exit from Northern Empty Room to Hallway
    	emptyRoomNorth.addExit(new Exit(Exit.UP, upwardTunnel));		// Upwards exit from Northern Empty Room to Upward Tunnel
    	emptyRoomNorth.addExit(new Exit(Exit.DOWN, downwardTunnel));	// Downwards exit from Northern Empty Room to Downward Tunnel
    	
    	hallway.addExit(new Exit(Exit.SOUTH, emptyRoomSouth));			// South exit from Hallway to Southern Empty Room
    	hallway.addExit(new Exit(Exit.NORTH, emptyRoomNorth));			// North exit from Hallway to Northern Empty Room
    	
    	upwardTunnel.addExit(new Exit(Exit.DOWN, emptyRoomNorth));		// Downwards exit from Upward Tunnel to Northern Empty Room
    	upwardTunnel.addExit(new Exit(Exit.UP, backyard));				// Upwards exit from Upward Tunnel to Backyard
    	
    	downwardTunnel.addExit(new Exit(Exit.UP, emptyRoomNorth));		// Upwards exit from Downward Tunnel to Northern Empty Room
    	downwardTunnel.addExit(new Exit(Exit.DOWN, basement));			// Downwards exit from Downward Tunnel to Basement
    	
    	basement.addExit(new Exit(Exit.UP, downwardTunnel));			// Upwards exit from Basement to Downwards Tunnel
    	
    	backyard.addExit(new Exit(Exit.DOWN, upwardTunnel));			// Downwards exit from Backyard to Upward Tunnel
    	backyard.addExit(new Exit(Exit.WEST, houseKitchen));			// West exit from Backyard to Kitchen
		backyard.addExit(new Exit(Exit.IN, houseKitchen));				// Inwards exit from Backyard to Kitchen (Alt. to West)
		
		houseKitchen.addExit(new Exit(Exit.EAST, backyard));			// East exit from Kitchen to Backyard
		houseKitchen.addExit(new Exit(Exit.OUT, backyard));				// Outwards exit from Kitchen to Backyard (Alt. to East)
		
		// Set spawn location
		currentLocation = emptyRoomSouth;
		
		// Print current location (Introductory)
		printCurrentLocation();
    }
    
    // Prints the current location's title, description, and items.
    public void printCurrentLocation()
    {
    	// Print the current location's title
    	System.out.println(currentLocation.getTitle());
    	// Print the current location's description
    	System.out.println(currentLocation.getDescription());
    	for (int i = 0; i < currentLocation.getItems().size(); i++)
    	{ System.out.println(currentLocation.getItems().get(i).getShortDesc()); }
    }
    
    // Gets current location
    public Location getCurrentLocation()
    { return currentLocation; }
    
    // Sets current location
    public void setCurrentLocation(Location currentLocation)
    { this.currentLocation = currentLocation; }
    
    // Gets inventory
    public ArrayList<Item> getInventory()
    { return inventory; }
	
    // Adds item to inventory
    public void addInventoryItem(Item item)
    { inventory.add(item); }
    
    // Removes item from inventory
    public void removeInventoryItem(Item item)
    { inventory.remove(item); }
    
    // Checks if game will exit when loop is finished
    public boolean isExit()
    { return exit; }
    
    // Sets if game will exit when loop is finished
    public void setExit(boolean exit)
    { this.exit = exit; }
}
