package supportingfiles;

import car.ModelASpec;
import car.OrderSpecification;
import car.parts.Body;
import controllers.SystemController;
import controllers.UseCaseController;
import controllers.UseCaseControllerAdaptSchedule;
import controllers.UseCaseControllerAssemblyTasks;
import controllers.UseCaseControllerCheckAssemblyLine;
import controllers.UseCaseControllerGarageHolder;
import controllers.UseCaseControllerLogIn;
import controllers.UseCaseControllerSingleTask;
import controllers.UseCaseControllerStatistics;
import user.User;
import util.LineReader;

public class ConveyerBeltProgram {
	private static SystemController s;

	public static void main(String[] args) 
	{	
		s = new SystemController();

		boolean running = true;
		
		while(running)
		{
			UseCaseController c = new UseCaseControllerLogIn();
			if(c.guideUseCase(s))
			{
				User user = s.getLoggedInUser();
				switch(user.getUserName())
				{
				case "Manager":	handleManager(); break;
				case "GarageHolder": 	handleGarageHolder();
										break;
				case "Mechanic": handleMechanic(); break;
				case "ShopHolder":handleShopManager(); break;
				}
			}
			
			System.out.println("Do you wish to quit?");
			if(LineReader.readLine().toLowerCase().startsWith("y"))
				running = false;
		}
	}
	
	public static void handleGarageHolder()
	{
		UseCaseController c = new UseCaseControllerGarageHolder();
		c.guideUseCase(s);
	}
	
	public static void handleManager()
	{
		System.out.println("What do you want to do?\n(1) check statistics\n"
				+ "(2) adapt scheduling algorithm");
		switch(LineReader.readInt())
		{
		case 1:(new UseCaseControllerStatistics()).guideUseCase(s);break;
		case 2:(new UseCaseControllerAdaptSchedule()).guideUseCase(s);break;
		}
	}
	
	public static void handleMechanic()
	{
		System.out.println("What do you want to do?\n(1) perform assembly tasks"
				+ "\n(2) check assembly line status");
		switch(LineReader.readInt())
		{
		case 1: (new UseCaseControllerAssemblyTasks()).guideUseCase(s);break;
		case 2: (new UseCaseControllerCheckAssemblyLine()).guideUseCase(s);break;
		}
	}
	
	public static void handleShopManager()
	{
		(new UseCaseControllerSingleTask()).guideUseCase(s);
	}
}
