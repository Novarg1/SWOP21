package car;

/**
 * interface for representing all carParts
 * a carpart knows how to install itself, how to present itself
 * ins stringform and wheter or not it has been installed
 * 
 * @author jonathanlangens
 */
public interface CarPart {
	
	public void install();
	
	public String toString();
	
	public boolean isInstalled();
}
