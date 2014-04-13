package company;

import java.util.LinkedList;

public class UserManager 
{
	private LinkedList<String> users;
	private String loggedInUser = null;
	
	public UserManager()
	{
		users = new LinkedList<String>();
		users.add("Manager");
		users.add("garageHolder");
		users.add("Mechanic");
		users.add("CustomShopController");
	}
	
	public String getLoggedInUser()
	{
		return loggedInUser;
	}
	
	public String logInUser(int n)
	{
		if(n < 0 || n >= users.size())
		{
			throw new IllegalArgumentException();
		}
		loggedInUser = users.get(n);
		return getLoggedInUser();
	}
}
