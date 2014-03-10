package supportingfiles;

import company.Company;

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
		boolean testing = true;
		
		UserInterface ui = (testing? new UserInterfaceUseCaseTester(new UseCaseDemo()):
								 new UserInterfaceTerminal());
		
		// initialize the controller
		SystemController c = new SystemController(ui);
		
		// display a welcome message
		c.displayWelcomeMessage();
		
		// main program loop as long as correct user/pwd combinations are given
		// c.displayLogin will provide a user and the user object will provide
		// the functionality for his usertype
		UserController u = null;
		try
		{
			while((u = c.displayLogin()) != null)
			{
				// provide an overview of all functionalities this usertype can use
				u.provideUI(ui);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// cleaning up, notifying the user
		c.displayGoodByeMessage();
	}

}
