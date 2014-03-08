package system.user;

import company.Company;

import system.functionality.Functionality;
import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.userinterface.UserInterface;

public class MechanicController implements UserController{

	private Mechanic user;
	private Company company;
	
	public MechanicController(Mechanic user, Company company)
	{
		this.user = user;
		this.company = company;
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
			controller.provideFunctionality(ui, user, company);
		}
	}
}
