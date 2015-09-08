package ml.polymetric.textgame03;

public class Exit
{
	public static final int			UNDEFINED		= 0;
	public static final int			NORTH			= 1;
	public static final int			SOUTH			= 2;
	public static final int			EAST			= 3;
	public static final int			WEST			= 4;
	public static final int			UP			= 5;
	public static final int			DOWN			= 6;
	public static final int			NORTHEAST		= 7;
	public static final int			NORTHWEST		= 8;
	public static final int			SOUTHEAST		= 9;
	public static final int			SOUTHWEST		= 10;
	public static final int			IN			= 11;
	public static final int			OUT			= 12;
	public static final String[] dirName	=
			{
					"UNDEFINED",
					"NORTH",
					"SOUTH",
					"EAST",
					"WEST",
					"UP",
					"DOWN",
					"NORTHEAST",
					"NORTHWEST",
					"SOUTHEAST",
					"SOUTHWEST",
					"IN",
					"OUT"
			};
	public static final String[] shortDirName =
			{
					"NULL",
					"N",
					"S",
					"E",
					"W",
					"U",
					"D",
					"NE",
					"NW",
					"SE",
					"SW",
					"I",
					"O"
			};
	private Location				leadsTo			= null;
	private int						direction;
	private String					directionName;
	private String					shortDirectionName;
	
	public Exit()
	{
		this.direction = Exit.UNDEFINED;
		this.leadsTo = null;
		this.directionName = dirName[UNDEFINED];
		this.shortDirectionName = shortDirName[UNDEFINED];
	}
	
	public Exit(int direction, Location leadsTo)
	{
		this.direction = direction;
		if (direction <= dirName.length)
		{
			this.directionName = dirName[direction];
		}
		if (direction <= shortDirName.length)
		{
			this.shortDirectionName = shortDirName[direction];
		}
		this.leadsTo = leadsTo;
	}
	
	public Location getLeadsTo() {return leadsTo;}
	public void setLeadsTo(Location leadsTo) {this.leadsTo = leadsTo;}
	public int getDirection() {return direction;}
	public void setDirection(int direction)
	{
		this.direction = direction;
		if (direction <= dirName.length)
		{
			this.directionName = dirName[direction];
		}
		if (direction <= shortDirName.length)
		{
			this.shortDirectionName = shortDirName[direction];
		}
	}
	
	public String getDirectionName() {return directionName;}
	public String getShortDirectionName() {return shortDirectionName;}
	
	public static int strToDir(String str)
	{
		for (int i = 0; i < dirName.length; i++)
		{
			if (str.equals(dirName[i]) || str.equals(shortDirName[i]))
			{
				return i;
			}
		}
		return 0;
	}
}
