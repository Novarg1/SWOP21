package session;

import userinterface.UserInterface;

public class UserSessionManager implements UserSession 
{
	public UserSessionManager(String userName)
	{
		this.userName = userName;
	}
	
	public boolean runSession(UserInterface userInterface) 
	{
		// TODO Auto-generated method stub
		userInterface.displayString("Starting manager session...");
		return true;
	}
	
	String userName;
}
