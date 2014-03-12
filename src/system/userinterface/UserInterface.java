package system.userinterface;


/**
 *  An interface has to implement 2 methods, 1 for showing a string
 *  and 1 for showing a string and returning the user input
 * @author jonathanlangens
 *
 */
public interface UserInterface 
{
	public void displayString(String s);
	public String displayStringWithInput(String s);
}
