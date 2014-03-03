package system.user;

import system.functionality.AssemblyLineController;
import system.functionality.Functionality;
import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.userinterface.UserInterface;

public class ManagerController implements UserController {

	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		ui.displayString("Welcome to the manager menu");
		ui.displayString("");
		ui.displayString("1. Advance the assembly line");
		ui.displayString("");
		String input = ui.displayStringWithInput("Enter your choice:");
		
		if(input.equals("1")) // manager wants to advance assemblyline
		{
			FunctionalityController controller = 
					FunctionalityControllerFactory.getControllerFor(Functionality.FUNCTIONALITY_ASSEMBLY_LINE);
			controller.provideFunctionality(ui);
		}
	}

}
