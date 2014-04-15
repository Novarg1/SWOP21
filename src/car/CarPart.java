package car;

/**
 * class for representing all carParts
 * a carpart knows how to install itself, how to present itself
 * ins stringform and wheter or not it has been installed.
 * 
 * Subclasses should define an enumeration of specific options.
 */
public abstract class CarPart<Option extends Enum<?>> {

	private final Option option;
	private boolean installed;
	private int time;
	
	protected CarPart(Option option) {
		this.option = option;
		installed = false;
		time = -1;
	}
	
	/**
	 * sets this carpart's status to 'installed'.
	 * @param time The time it took to install this part.
	 */
	public void install(int time) {
		this.installed = true;
		this.time = time;
	}
	
	/**
	 * @return The type of this carPart
	 */
	public Option getOptionType() {
		return option;
	}
	
	/**
	 * @return true if this carpart has been installed.
	 */
	public boolean isInstalled() {
		return installed;
	}
	
	/**
	 * @return The time it took to install this carpart. The result
	 * 		is a negative number if this part has not been installed yet.
	 */
	public int getTimeTaken() {
		return isInstalled() ? time : -1;
	}
	
	@Override
	public String toString() {
		return option.toString();
	}
	
	/**
	 * @return Instructions in text-format for installing this part.
	 */
	public abstract String getAssemblyInstructions();
}
