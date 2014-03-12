package tests;

import org.junit.Test;

import system.SystemController;
import system.user.GarageHolder;
import system.user.User;
import system.user.UserController;
import system.userinterface.UserInterfaceUseCaseTester;

/**
 * Tests for the system controller that don't apply to specific
 * usecases
 * @author jonathanlangens
 *
 */
public class TestSystemController 
{
	/**
	 * what if the user that logs in doesn't exist
	 */
	@Test
	public void testNonExistentUser()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// try to log in with a non existent user
		ui.addInput("usernonexistent");
		ui.addInput(" ");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		
		// the user controller should be null
		assert(uc == null);
	}
	/**
	 * what if the user tries to log in without knowing the password
	 */
	@Test
	public void testWrongPassword()
	{
		UserInterfaceUseCaseTester ui = new UserInterfaceUseCaseTester();

		// try to log in with a non existent user
		ui.addInput("user1");
		ui.addInput("wrong password");
		
		// adding the controller and running the program
		SystemController c = new SystemController(ui);
		UserController uc = c.displayLogin();
		
		// the user controller should be null
		assert(uc == null);
	}
}
