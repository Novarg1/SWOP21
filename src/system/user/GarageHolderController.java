package system.user;

import system.functionality.Functionality;
import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.userinterface.UserInterface;

public class GarageHolderController implements UserController {

	private GarageHolder user;
	
	public GarageHolderController(GarageHolder user) {
		this.user = user;
	}
	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		
		// first show the garage holder what he has already ordered
		
		ui.displayString("Welcome to the garage holder menu");
		ui.displayString("");
		ui.displayString("1. Place a new order");
		ui.displayString("");
		String input = ui.displayStringWithInput("Enter your choice:");
		
		if(input.equals("1"))
		{
			FunctionalityController controller = 
					FunctionalityControllerFactory.getControllerFor(Functionality.FUNCTIONALITY_ORDER_PROCESS);
			controller.provideFunctionality(ui);
		}
		
	}
}
