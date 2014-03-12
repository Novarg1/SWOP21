package supportingfiles;

import system.SystemController;
import system.usecases.UseCase;
import system.usecases.UseCaseDemo;
import system.usecases.UseCaseGH1;
import system.usecases.UseCaseGH2;
import system.usecases.UseCaseGH3;
import system.user.UserController;
import system.userinterface.UserInterface;
import system.userinterface.UserInterfaceTerminal;
import system.userinterface.UserInterfaceUseCaseTester;

public class ConveyerBeltProgram {

	public static void main(String[] args) 
	{
		// change this to testing to use the use case tester interface
		boolean testing = true;
		
		if(testing==true)
		{
			// Test 1. - testing use case 1 under good circumstances
			runTests(new UseCaseGH1());
			
			// Test 2. - testing use case 1 under alternate flow 1
			runTests(new UseCaseGH2());
			
			// Test 2. - testing use case 1 under alternate flow 2
			runTests(new UseCaseGH3());
		}
		
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
	
	public static void runTests(UseCase uc)
	{
		UserInterface ui = new UserInterfaceUseCaseTester(uc);
		SystemController c = new SystemController(ui);

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
		uc.passTest(c);
	}

}
