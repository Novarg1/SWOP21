package system.functionality;

import company.Company;

import system.user.User;
import system.userinterface.UserInterface;

public class AssemblyLineController implements FunctionalityController 
{	
	@Override
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
