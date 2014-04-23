package view;

import user.User;
import util.LineReader;
import controllers.SystemController;

public class ViewSystem extends View
{
	public ViewSystem(SystemController c) {
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
				case "Manager":	new ViewManager(systemController); break;
				case "GarageHolder": new ViewGarageHolder(systemController);break;
				case "Mechanic": new ViewMechanic(systemController); break;
				case "ShopHolder":new ViewCustomShop(systemController); break;
				}
			}
			
			System.out.println("Do you wish to quit?");
			if(LineReader.readLine().toLowerCase().startsWith("y"))
				running = false;
		}
		
		return true;
	}
	
}
