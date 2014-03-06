package supportingfiles;

import system.SystemController;
import system.functionality.FunctionalityController;
import system.usecases.UseCaseDemo;
import system.user.UserController;
import system.userinterface.UserInterface;
import system.userinterface.UserInterfaceTerminal;
import system.userinterface.UserInterfaceUseCaseTester;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{
		// change this to testing to use the use case tester interface
		boolean testing = false;
		
		UserInterface f = (testing? new UserInterfaceUseCaseTester(new UseCaseDemo()):
								 new UserInterfaceTerminal());
		
		// initialize the controller
		SystemController c = new SystemController(f);
		
		c.displayWelcomeMessage();
		
		
		UserController u = null;
		try
		{
			while((u = c.displayLogin()) != null)
			{
				u.provideUI(f);
			}
		}
		catch(Exception e)
		{
		}
		c.displayGoodByeMessage();
	}

}
