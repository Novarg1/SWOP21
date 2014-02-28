package controller;

import session.UserSession;
import session.UserSessionFactory;
import userinterface.UserInterface;

public class Controller 
{
	public Controller(UserInterface userInterface)
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
	
	public UserSession displayLogin()
	{
		String userName = "";
		String pwd = "";
		userName = userInterface.displayStringWithInput("please enter your username: ");
		pwd = userInterface.displayStringWithInput("please enter your password: ");
		return UserSessionFactory.getSession(userName, pwd);
	}
	
	protected UserInterface userInterface;
}
