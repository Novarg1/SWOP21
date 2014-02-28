package session;

import userinterface.UserInterface;

public class UserSessionMechanic implements UserSession
{
	public UserSessionMechanic(String userName)
	{
		this.userName = userName;
	}

	@Override
	public boolean runSession( UserInterface userInterface) {
		// TODO Auto-generated method stub
		userInterface.displayString("Starting mechanic session...");
		return true;
	}
	
	private String userName;
}
