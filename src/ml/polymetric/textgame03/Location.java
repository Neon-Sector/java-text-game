package ml.polymetric.textgame03;

import java.util.ArrayList;

public class Location
{
	private String				locTitle;			// E.g. "House"
	private String				locDescription;		// E.g. "You are in a yellow house"
	private ArrayList<Exit>		exits;				// Stores exits. THERE CAN ONLY BE ONE EXIT OF EACH DIRECTION!
	private ArrayList<Item>		items;				// Stores items. TODO: Figure out what happens when multiple items of the same type are in array
	private boolean				alreadyVisited;		// If the player has already visited this room
	
	// Blank constructor
	// Try to avoid using this.
	public Location()
	{
		// Print information if the location is blank.
		this.locTitle = "Blank Location";
		this.locDescription = "This location is undefined." + "\nThat means the developer did something wrong."
				+ "\nIf you actually find a location like this, please contact us." + "\nWe commend you if you do :P";
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
	}

	// Full constructor
	public Location(String title, String description)
	{
		this.locTitle = title;
		this.locDescription = description;
		this.exits = new ArrayList<Exit>();
		this.items = new ArrayList<Item>();
	}
	
	public void addExit(Exit exit)						{this.exits.add(exit);}
	public void removeExit(Exit exit)					{if (this.exits.contains(exit)) this.exits.remove(exit);}
	public ArrayList<Exit> getExits()					{return (ArrayList<Exit>) this.exits.clone();}
	public void addItem(Item item)						{this.items.add(item);}
	public void removeItem(Item item)					{if (this.items.contains(items)) {this.items.remove(items);}}
	public ArrayList<Item> getItems()					{return items;}
	public String getTitle()							{return this.locTitle;}
	public void setTitle(String locTitle)				{this.locTitle = locTitle;}
	public String getDescription()						{return this.locDescription;}
	public void setDescription(String locDescription)	{this.locDescription = locDescription;}
	public boolean hasAlreadyVisited()					{return alreadyVisited;}
	public void setAlreadyVisited()						{alreadyVisited = true;}
}
