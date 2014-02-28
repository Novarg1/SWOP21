package session;

import userinterface.UserInterface;

public class UserSessionGarageHolder implements UserSession
{
	public UserSessionGarageHolder(String userName)
	{
		this.userName = userName;
	}
	
	@Override
	public boolean runSession(UserInterface userInterface) {
		// TODO Auto-generated method stub
		userInterface.displayString("Starting garage holder session...");
		return true;
	}

	private String userName;
}
