package view;

import user.User;
import util.LineReader;
import controllers.SystemController;

/**
 * Displays the base view and delegate to relevant views
 * @author jonathanlangens
 *
 */
public class ViewMain extends View
{
	public ViewMain(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean show() 
	{		
		ViewLogIn login = new ViewLogIn(systemController);
		boolean running = true;
	
		while(running)
		{
			if(login.show())
			{
				User user = systemController.getLoggedInUser();
				switch(user.getUserName())
				{
				case "Manager":	new ViewManager(systemController).show(); break;
				case "GarageHolder": new ViewGarageHolder(systemController).show();break;
				case "Mechanic": new ViewMechanic(systemController).show(); break;
				case "ShopHolder":new ViewCustomShop(systemController).show(); break;
				}
			}
			
			System.out.println("Do you wish to quit?");
			if(LineReader.readLine().toLowerCase().startsWith("y"))
				running = false;
		}
		
		return true;
	}
	
}
