package system;

import java.util.HashSet;
import java.util.Set;

import company.Company;
import system.user.GarageHolder;
import system.user.Manager;
import system.user.Mechanic;
import system.user.User;
import system.user.UserController;
import system.userinterface.UserInterface;

/* System Controller
 * 
 * Manages everything while no user is logged in, also keeps the ui, the userdb and the
 * company alive.
 */

public class SystemController {

	protected Set<User> users;
	protected UserInterface userInterface;
	protected Company company;

	public SystemController(UserInterface userInterface)
	{
		this.userInterface = userInterface;
		this.company = new Company();
		users = new HashSet<User>();
		users.add(new Manager("user1"," "));
		users.add(new GarageHolder("user2"," "));
		users.add(new Mechanic("user3"," "));
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
		
		String userName = userInterface.displayStringWithInput("please enter your username: ");
		String pwd = userInterface.displayStringWithInput("please enter your password: ");
		user = getUser(userName, pwd);
		
		if(user == null)return null;

		return user.getController(this.company);
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
	
	public Company getCompany()
	{
		return this.company;
	}
}
