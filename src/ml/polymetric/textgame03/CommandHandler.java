package ml.polymetric.textgame03;

import java.util.ArrayList;

public class CommandHandler
{
	public static boolean execute(String[] command)
	{
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		ArrayList<String> commandList = new ArrayList<String>();
		String goCommand = "go";
		
		// Temporary variables.
		// TODO remember what these were for (lol) and implement it
		boolean up = false;
		boolean put = false;
		boolean at = false;
		boolean go = false;
		boolean look = false;
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// If user didn't enter anything in, print error and return.
		if (command == null)
		{
			System.out.println("Sorry, I don't understand that.");
			return false;
		}
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Add command array to command ArrayList.
		for (int i = 0; i < command.length; i++)
		{
			commandList.add(command[i].toLowerCase());
		}
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// If first word in command is go/move/walk/run, remove it from command.
		if (commandList.get(0).equals("go")
				|| commandList.get(0).equals("move")
				|| commandList.get(0).equals("walk")
				|| commandList.get(0).equals("run"))
		{
			go = true;
			Debug.print("command equals go");
			goCommand = commandList.get(0);
			commandList.remove(0);
		}
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		/* Go command
		 *
		 * Summary of the following code:
		 *
		 * 1. Check if the command size is less than three
		 *
		 * 2. For every direction name that exists, check if the command
		 * equals that direction name or its short alias. (Direction ID = i)
		 *
		 * 3. For every exit in the current location, check if the command
		 * equals the direction ID of i (the direction ID the user entered)
		 *
		 * 4. Set the current location to the location that the exit that has the specified location ID
		 *
		 */
		try
		{
			if (commandList.size() < 3)
			{
				if (commandList.get(0).equals("left") || commandList.get(0).equals("right"))
				{
					System.out.println("You must specify a compass direction. (1)");
					return false;
				}
				for (int i = 0; i < Exit.dirName.length; i++)
				{
					if (commandList.get(0).equals(Exit.dirName[i].toLowerCase())
							|| commandList.get(0).equals(Exit.shortDirName[i].toLowerCase()))
					{
						go = true;
						for (int i1 = 0; i1 < Launcher.getGame().getCurrentLocation().getExits().size(); i1++)
						{
							if (Launcher.getGame().getCurrentLocation().getExits().get(i1).getDirection() == Exit.strToDir(Exit.shortDirName[i]))
							{
								Launcher.getGame().setCurrentLocation(Launcher.getGame().getCurrentLocation().getExits().get(i1).getLeadsTo());
								return true;
							}
						}
						System.out.println("You can't " + goCommand + " that way.");
						return false;
					}
				}
			}
			if (go)
			{
				Debug.print("go equals true");
				System.out.println("You must specify a compass direction. (2)");
				return false;
			}
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Catch possible exception but don't do anything
		catch (IndexOutOfBoundsException e) {}
		// End go command
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		// If command is "exit," set exit to true and return
		if (commandList.get(0).equals("exit")) 
		{
			Launcher.getGame().exit(true);
			return false;
		}
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// COMMAND: LOOK/INSPECT/L
		if (commandList.get(0).equals("look")
				|| commandList.get(0).equals("inspect"))
		{
			if (commandList.size() < 2)
			{
				return true;
			}
			try
			{
				if (commandList.get(1).equals("at"))
				{
					commandList.remove(1);
					at = true;
				}
			}
			catch (IndexOutOfBoundsException e) {}
			
			if (commandList.get(0).equals("look"))
			{
				look = true;
			}
			
			try
			{
				for (int i = 0; i < Launcher.getGame().getCurrentLocation().getItems().size(); i++)
				{
					String[] itemTitleArray = Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle().toLowerCase().split(" ");
					// TODO Fix this statement, it only gets the first word in the string even though there might be multiple
					if (commandList.get(1).equals(Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle().toLowerCase()))
					{
						System.out.println(Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle());
						System.out.println(Launcher.getGame().getCurrentLocation().getItems().get(i).getDescription());
						return false;
					}
					
					for (int i1 = 0; i1 < itemTitleArray.length; i1++)
					{
						if (commandList.get(i).equals(itemTitleArray[i1]))
						{
							System.out.println(Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle());
							System.out.println(Launcher.getGame().getCurrentLocation().getItems().get(i).getDescription());
							return false;
						}
					}
				}
			}
			catch (IndexOutOfBoundsException e)
			{
				// TODO This part behaves weird, try to figure out why and fix it
				if (at)
				{
					System.out.println("What do you want to look at?");
					return false;
				}
			}
			return true;
		}
		// End command: LOOK/INSPECT/L
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		// COMMAND: TAKE/PICK(UP)/GRAB/GET
		if (commandList.get(0).equals("take")
				|| commandList.get(0).equals("pick")
				|| commandList.get(0).equals("grab")
				|| commandList.get(0).equals("get"))
		{
			if (commandList.get(1).equals("up") && commandList.size() > 2 && commandList.get(0).equals("pick"))
			{
				commandList.remove(1);
				up = true;
			}
			if (Launcher.getGame().getCurrentLocation().getItems().size() < 1)
			{
				return false;
			}
			
			commandList.remove(0);
			
			// Actual command code
			for (int i = 0; i < Launcher.getGame().getCurrentLocation().getItems().size(); i++)
			{
				for (int i1 = 0; i1 < commandList.size(); i1++)
				{
					for (int i2 = 0; i2 < Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle().split(" ").length; i2++)
					{
						if (Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle().split(" ")[i2].equals(commandList.get(i1)))
						{
							if (Launcher.getGame().getCurrentLocation().getItems().get(i).isTakeable())
							{
								Launcher.getGame().addInventoryItem(Launcher.getGame().getCurrentLocation().getItems().get(i));
								Launcher.getGame().getCurrentLocation().removeItem(Launcher.getGame().getCurrentLocation().getItems().get(i));
							}
							System.out.println("You can't take the " + Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle() + "!");
						}
					}
				}
			}
			
			// Try to print error message with item name
			try
			{
				StringBuilder sb = new StringBuilder();
				String wholeCommand;
				for (int i = 0; i < commandList.size(); i++)
				{
					sb.append(commandList.get(i) + " ");
				}
				wholeCommand = sb.toString().trim().toLowerCase();
				System.out.println("You can't see a " + wholeCommand + " here.");
				return false;
			}
			catch (IndexOutOfBoundsException e) {} // Catch the possible exception but don't do anything
			
			if (commandList.get(1).equals("up") && commandList.size() < 3)
			{
				System.out.println("What do you want to pick up?");
				return false;
			}
			System.out.println("What do you want to " + commandList.get(0) + "?");
			return false;
		}
		// End command: TAKE/PICK(UP)/GRAB/GET
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		// COMMAND: DROP/PUT(DOWN)/LEAVE
		if (commandList.get(0).equals("drop")
				|| commandList.get(0).equals("put")
				|| commandList.get(0).equals("leave"))
		{
			if (commandList.get(1).equals("up") && commandList.size() > 2)
			{
				commandList.remove(1);
				up = true;
			}
			if (Launcher.getGame().getCurrentLocation().getItems().size() < 1)
			{
				return false;
			}
			
			// Actual command code
			for (int i = 0; i < Launcher.getGame().getCurrentLocation().getItems().size(); i++)
			{
				for (int i1 = 0; i1 < Launcher.getGame().getCurrentLocation().getItems().get(i1).getTitle().split(" ").length; i1++)
				{
					// If the item in the command is found
					if (commandList.get(i1 + 1).equals(Launcher.getGame().getCurrentLocation().getItems().get(i).getTitle()))
					{
						// If item is takeable
						if (Launcher.getGame().getCurrentLocation().getItems().get(i).isTakeable())
						{
							// Add item to inventory
							Launcher.getGame().removeInventoryItem(Launcher.getGame().getInventory().get(i));
							// Remove item from location
							Launcher.getGame().getCurrentLocation().addItem(Launcher.getGame().getInventory().get(i));
							return false;
						}
					}
				}
			}
			
			try // Try to print error message with item name
			{
				System.out.println("You can't see a " + commandList.get(1) + " here.");
				return false;
			}
			catch (IndexOutOfBoundsException e) {} // Catch the possible exception but don't do anything
			
			if (commandList.get(1).equals("down") && commandList.size() < 3)
			{
				System.out.println("What do you want to put down?");
				return false;
			}
			System.out.println("What do you want to " + commandList.get(0) + "?");
			return false;
		}
		// End command: DROP/PUT(DOWN)/LEAVE
		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		// If nothing above happens, print error and return.
		System.out.println("Sorry, I don't understand that.");
		return false;
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	}
}
