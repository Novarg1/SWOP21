package user;

import java.util.LinkedList;
import java.util.List;

/**
 * Deze klasse helpt het systeem met het inloggen van gebruikers,
 * en weet ook telkens bij wie de huidige ingelogde gebruiker is
 * en tot slot ook een lijst van alle gebruikers van het systeem
 * maw elke gebruiker leeft enkel hier
 * @author jonathanlangens
 *
 */
public class UserManager 
{
	private List<User> users;
	private User loggedInUser = null;
	
	/**
	 * constructor, maakt alle gebruikers aan
	 */
	public UserManager()
	{
		users = new LinkedList<User>();
		users.add(new Manager("Manager"));
		users.add(new GarageHolder("GarageHolder"));
		users.add(new Mechanic("Mechanic"));
		users.add(new ShopHolder("ShopHolder"));
	}
	
	/**
	 * @return de huidige ingelogde gebruiker
	 */
	public User getLoggedInUser()
	{
		return loggedInUser;
	}
	
	/**
	 * logt this.users.get(n) in als ingelogde gebruiker als n niet
	 * in de lijst van users voorkomt wordt er een exception geworpen.
	 * @param n : de gebruiker die ingelogd moet worden
	 * @return de ingelogde gebruiker
	 */
	public User logInUser(int n)
	{
		if(n < 0 || n >= users.size())
		{
			throw new IllegalArgumentException();
		}
		loggedInUser = users.get(n);
		return getLoggedInUser();
	}
}
