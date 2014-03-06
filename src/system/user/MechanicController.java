package system.user;

import system.functionality.Functionality;
import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.userinterface.UserInterface;

public class MechanicController implements UserController{
	public MechanicController(String uname)
	{
		this.userName = uname;
	}

	@Override
	public void provideUI(UserInterface ui) {
		// TODO Auto-generated method stub
		ui.displayString("Welcome to the mechanic menu");
		ui.displayString("");
		ui.displayString("1. Perform workstation jobs");
		ui.displayString("");
		String input = ui.displayStringWithInput("Enter your choice:");
		
		if(input.equals("1")) // manager wants to advance assemblyline
		{
			FunctionalityController controller = 
					FunctionalityControllerFactory.getControllerFor(Functionality.FUNCTIONALITY_WORK_STATION);
			controller.provideFunctionality(ui);
		}
	}

	private String userName;
}
