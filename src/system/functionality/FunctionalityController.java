package system.functionality;

import company.Company;

import system.user.User;
import system.userinterface.UserInterface;

/* all classes that implement this must be able to guide the user through the
 * necessary steps to use the offered functionality
 */

public interface FunctionalityController 
{
	/*
	 * this function guides the user through the requested functionality, making sure
	 * everything that needs be initialized is done so correctly, all user input is ac-
	 * ceptable and the order in which the functionality is to be used is respected.
	 * 
	 * @return true if the requested functionality was successfully provided
	 */
	public boolean provideFunctionality(UserInterface userInterface, User user, Company company);
}
