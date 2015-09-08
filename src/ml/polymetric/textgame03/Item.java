package ml.polymetric.textgame03;

public class Item
{
	private String	itemTitle;		// Title of the item. E.g. "Cool Sword"
	private String	itemDescription;	// Detailed description of item. E.g. "The sword appears brand new. It has a marking on it."
	private String	itemShortDesc;		// Used for displaying location info. E.g. "There is a sword here."
	private boolean	takeable;		// If you can pick up the item or not.
	
	// Blank constructor
	// Try to avoid using this.
	public Item()
	{
		this.itemTitle = new String();
		this.itemDescription = new String();
		this.itemShortDesc = new String();
		this.takeable = false;
	}
	
	// Full Constructor
	public Item(String title, String description, String shortDesc, boolean takeable)
	{
		this.itemTitle = title;
		this.itemDescription = description;
		this.itemShortDesc = shortDesc;
		this.takeable = takeable;
	}
	
	public String getTitle()				{return itemTitle;}
	public void setTitle(String itemTitle)			{this.itemTitle = itemTitle;}
	public String getDescription()				{return itemDescription;}
	public void setDescription(String itemDescription)	{this.itemDescription = itemDescription;}
	public String getShortDesc()				{return itemShortDesc;}
	public void setShortDesc(String itemShortDesc)		{this.itemShortDesc = itemShortDesc;}
	public boolean isTakeable()				{return takeable;}
	public void setTakeable(boolean canTake)		{this.takeable = canTake;}
}
