package system;

import system.functionality.FunctionalityController;
import system.functionality.FunctionalityControllerFactory;
import system.user.UserController;
import system.user.UserControllerFactory;
import system.user.UserRole;
import system.userinterface.UserInterface;

public class SystemController 
{
	public SystemController(UserInterface userInterface)
	{
		this.userInterface = userInterface;
	}
	
	public void displayWelcomeMessage()
	{
		userInterface.displayString("Welcome to 'Conveyer Belt'");
	}
	
	public void displayGoodByeMessage()
	{
		userInterface.displayString("\nClosing up, good gye.");
	}
	
	public UserController displayLogin() throws Exception
	{
		String userName = "";
		String pwd = "";
		userName = userInterface.displayStringWithInput("please enter your username: ");
		pwd = userInterface.displayStringWithInput("please enter your password: ");
		
		UserRole role = getRole(userName, pwd);
		
		UserController controller = UserControllerFactory.getUserController(role);
		return controller;
	}
	
	private UserRole getRole(String userName, String pwd) throws Exception 
	{
		if(userName.equals("user1") && pwd.equals(" "))return UserRole.USERROLE_MANAGER;
		if(userName.equals("user2") && pwd.equals(" "))return UserRole.USERROLE_GARAGEHOLDER;
		if(userName.equals("user3") && pwd.equals(" "))return UserRole.USERROLE_MECHANIC;
		throw new Exception();
	}
	
	protected UserInterface userInterface;
}