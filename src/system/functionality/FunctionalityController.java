package system.functionality;

import system.userinterface.UserInterface;

/* all classes that implement this must be able to guide the user through the
 * necessary steps to use the offered functionality
 */

public interface FunctionalityController 
{
	public boolean provideFunctionality(UserInterface userInterface);
}
