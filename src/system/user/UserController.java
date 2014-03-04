package system.user;

import system.userinterface.UserInterface;
/*
 * All classes that implement UserController have to be able to portray a
 * menu stating the functionality that is available for that user
 */
public interface UserController {
	public void provideUI(UserInterface ui);
}
