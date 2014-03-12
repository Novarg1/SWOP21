package tests;

import org.junit.Test;

import system.SystemController;
import system.user.GarageHolder;
import system.user.User;
import system.user.UserController;
import system.userinterface.UserInterfaceUseCaseTester;

public class TestUseCaseMechanic 
{
	/**
	 * Mechanic Standard use case
	 */
	@Test
	public void mechanicStandard()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// login in user 3 - the mechanic
		ui.addInput("user3");
		ui.addInput(" ");
		
		// tell the system you want to work at a work station
		ui.addInput("1");
		ui.addInput("1");
		
		// ask the system to show pending tasts
		ui.addInput("1");
		
		// you want to leave
		ui.addInput("y");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		uc.provideUI(ui);
	}
}