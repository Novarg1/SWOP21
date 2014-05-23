package user;

/**
 * abstracte klasse die een gebruiker voorstelt, elke klasse die deze
 * klasse extend moet de functie getrole implementeren
 * 
 * @author jonathanlangens
 *
 */
public abstract class User {
	
	private String username;
	
	/**
	 * constructor, elke user heeft een gebruikersnaam
	 * @param username
	 */
	public User(String username)
	{
		this.setUserName(username);
	}
	
	/**
	 * mutator voor de username
	 * @param username
	 */
	public void setUserName(String username)
	{
		this.username = username;
	}
	
	/**
	 * inspector voor de username
	 * @return
	 */
	public String getUserName()
	{
		return username;
	}
	
	/**
	 * gebruikt de username als string voorstelling van dit object
	 */
	public String toString()
	{
		return username;
	}
	
	/**
	 * abstracte methode die de role van de gebruiker als String
	 * teruggeef.
	 * @return de gebruikersrol
	 */
	public abstract String getRole();
}
