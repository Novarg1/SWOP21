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
		
		c.displayWelcomeMessage();
		
		
		UserController u = null;
		try
		{
			while((u = c.displayLogin()) != null)
			{
				u.provideUI(ui);
			}
		}
		catch(Exception e)
		{
		}
		c.displayGoodByeMessage();
	}

}
