package view;

import util.LineReader;
import controllers.SystemController;

/**
 * Display the manager view and delegate to other views
 * @author jonathanlangens
 *
 */
public class ViewManager extends View
{
	public ViewManager(SystemController c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean show() 
	{
		boolean running = true;
		while(running)
		{
			showMenu();
			System.out.println("Do you want to log out?");
			if(LineReader.readLine().toLowerCase().startsWith("y"))
				running = false;
		}
		
		return true;
	}
	
	private void showMenu()
	{
		System.out.println("What do you want to do?\n(1) check statistics\n"
				+ "(2) adapt scheduling algorithm\n"
				+ "(3) change the assembly lines operational status");
		switch(LineReader.readInt())
		{
		case 1:new ViewStatistics(systemController).show();break;
		case 2:new ViewAdaptSchedule(systemController).show();break;
		case 3:new ViewAssemblyLineStatus(systemController).show();break;
		}
	}
}
