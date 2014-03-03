package system.functionality;

import system.userinterface.UserInterface;

public class AssemblyLineController implements FunctionalityController 
{	
	@Override
	public boolean provideFunctionality(UserInterface userInterface) {
		// TODO Auto-generated method stub
		char c = userInterface.displayStringWithInput("Do you want to advance the line?").charAt(0);
		if(c == 'y')
		{
			// advance the line
		}
		return true;
	}
}
