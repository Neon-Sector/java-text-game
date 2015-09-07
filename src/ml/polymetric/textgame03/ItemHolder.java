package ml.polymetric.textgame03;

public class ItemHolder extends Item
{
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private int maxItems;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// Blank constructor
	// Try to avoid using this.
	public ItemHolder()
	{
		super();
		this.maxItems = 0;
	}
	
	// Default Constructor
		public ItemHolder(String title, String description, String shortDesc, boolean takeable)
		{
			super(title, description, shortDesc, takeable);
			this.maxItems = 3;
		}
		
	// Full Constructor
	public ItemHolder(String title, String description, String shortDesc, boolean takeable, int maxItems)
	{
		super(title, description, shortDesc, takeable);
		this.maxItems = maxItems;
	}
}
