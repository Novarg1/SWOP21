package session;

public class UserSessionFactory 
{

	public static UserSession getSession(String userName, String pwd)
	{
		switch(UserSessionFactory.getUserType(userName,pwd))
		{
		case USERROLE_MANAGER:
			return new UserSessionManager(userName);
		case USERROLE_GARAGEHOLDER:
			return new UserSessionGarageHolder(userName);
		case USERROLE_MECHANIC:
			return new UserSessionMechanic(userName);
		default:
			throw new IllegalArgumentException();
		}
	}
	
	private static UserRole getUserType(String userName, String pwd)
	{
		if(userName.equals("user1") && pwd.equals(" "))
		{
			return UserRole.USERROLE_MANAGER;
		}
		if(userName.equals("user2") && pwd.equals(" "))
		{
			return UserRole.USERROLE_GARAGEHOLDER;
		}
		if(userName.equals("user3") && pwd.equals(" "))
		{
			return UserRole.USERROLE_MECHANIC;
		}
		return null;
	}
}
