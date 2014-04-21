package supportingfiles;

import car.ModelASpec;
import car.OrderSpecification;
import car.parts.Body;
import controllers.SystemController;
import controllers.UseCaseController;
import controllers.UseCaseControllerGarageHolder;
import controllers.UseCaseControllerLogIn;
import user.User;
import util.LineReader;

public class ConveyerBeltProgram {
	private static SystemController s;

	public static void main(String[] args) 
	{
		/**
		 * the following generates a nasty nullpointer exception
		 */
		OrderSpecification spec = new ModelASpec();
		spec.getViableOptions(Body.class);
		
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
				case "Manager":	break;
				case "GarageHolder": 	handleGarageHolder();
										break;
				case "Mechanic":break;
				case "CustomShopController":break;
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
}
