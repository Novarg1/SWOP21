package car;

/**
 * interface for representing all carParts
 * a carpart knows how to install itself, how to present itself
 * ins stringform and wheter or not it has been installed
 * 
 * @author jonathanlangens
 */
public interface CarPart {
	
	public void install(int time);
	
	public String toString();
	
	public boolean isInstalled();
	
	public String getAssemblyInstructions();
	
	public int getTimeTaken();
}
