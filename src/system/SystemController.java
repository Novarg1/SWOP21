package system;

import java.util.HashSet;
import java.util.Set;

import system.user.User;
import system.user.UserController;
import system.user.UserControllerFactory;
import system.user.UserRole;
import system.userinterface.UserInterface;

/* System Controller
 * 
 * Manages everything while no user is logged in
 * 
 */

public class SystemController {

	protected Set<User> users;
	protected UserInterface userInterface;

	public SystemController(UserInterface userInterface)
	{
		this.userInterface = userInterface;
		users = new HashSet<User>();
		//TODO add testing users
	}

	public void displayWelcomeMessage()
	{
		userInterface.displayString("Welcome to 'Conveyer Belt'");
	}

	public void displayGoodByeMessage()
	{
		userInterface.displayString("\nClosing up, good bye.");
	}

	/**  
	 * shows a login interface
	 * @return a controller dedicated to the usertype
	 */
	public UserController displayLogin() {
		User user = null;
		do {
			String userName = userInterface.displayStringWithInput("please enter your username: ");
			String pwd = userInterface.displayStringWithInput("please enter your password: ");
			user = getUser(userName, pwd);
		} while(user == null);

		return user.getController();
	}

	/**
	 * @return user with given username and password;
	 * 		null if no such user exists
	 */
	private User getUser(String userName, String pwd) {
		for(User user : users) {
			if(user.USERNAME.equals(userName) && user.PASSWORD.equals(pwd)) {
				return user;
			}
		}
		return null;
	}
}
