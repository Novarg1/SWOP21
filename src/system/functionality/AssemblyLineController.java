package system.functionality;

import company.Company;

import system.user.User;
import system.userinterface.UserInterface;
/**
 * Provides functionality to operate the assembly line
 */
public class AssemblyLineController implements FunctionalityController 
{	
	@Override
	/**
	 * @see system.functionality.FunctionalityController#provideFunctionality(system.userinterface.UserInterface, system.user.User, company.Company)
	 * @return true if the functionality has been successfully provided
	 */
	public boolean provideFunctionality(UserInterface userInterface, User user,Company company) {
		// TODO Auto-generated method stub
		int time = Integer.parseInt(userInterface.displayStringWithInput(
				"How much time (in minutes) has passed since the last advancement?."
				));
		
		// display the future state of the assembly line
		userInterface.displayString("This will show the future state of the assembly line");
		
		char c = userInterface.displayStringWithInput("Do you want to advance the line?").charAt(0);
		if(c == 'y')
		{
			// advance the line
			company.advanceAssemblyLine(time);
		}
		return true;
	}
}
