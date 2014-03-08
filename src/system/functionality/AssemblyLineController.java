package system.functionality;

import company.Company;

import system.user.User;
import system.userinterface.UserInterface;

public class AssemblyLineController implements FunctionalityController 
{	
	@Override
	public boolean provideFunctionality(UserInterface userInterface, User user,Company company) {
		// TODO Auto-generated method stub
		
		// display the future state of the assembly line
		
		char c = userInterface.displayStringWithInput("Do you want to advance the line?").charAt(0);
		if(c == 'y')
		{
			// advance the line
		}
		return true;
	}
}
