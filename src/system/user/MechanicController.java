package system.user;

import system.functionality.Functionality;
import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.userinterface.UserInterface;

public class MechanicController implements UserController{

	private Mechanic user;
	
	public MechanicController(Mechanic user)
	{
		this.user = user;
	}

	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		ui.displayString("Welcome to the mechanic menu");
		ui.displayString("");
		ui.displayString("1. Perform workstation jobs");
		ui.displayString("");
		String input = ui.displayStringWithInput("Enter your choice:");
		
		if(input.equals("1"))
		{
			FunctionalityController controller = 
					FunctionalityControllerFactory.getControllerFor(Functionality.FUNCTIONALITY_WORK_STATION);
			controller.provideFunctionality(ui);
		}
	}
}
