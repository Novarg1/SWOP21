package tests;

import org.junit.Test;

import system.SystemController;
import system.user.UserController;
import system.userinterface.UserInterfaceUseCaseTester;

/**
 * Test class for the managers use cases
 * @author jonathanlangens
 *
 */

public class TestUseCaseManager 
{
	/**
	 * Manager - standard use case
	 */
	@Test
	public void managerUseCaseStandard()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// login in user 1 - the manager
		ui.addInput("user1");
		ui.addInput(" ");
		
		// tell the system you want to advance the assembly line
		ui.addInput("1");
		
		// tell the time in minutes
		ui.addInput("60");
		
		// yes i want to go through with it
		ui.addInput("yes");
		
		// exit the thing
		ui.addInput("");
		ui.addInput("");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		uc.provideUI(ui);
	}

}
