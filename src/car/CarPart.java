package car;

/**
 * interface for representing all carParts a carpart knows how to install itself
 * and whether or not it has been installed already on a given car.
 */
public interface CarPart {

	/**
	 * installs the carpart represented by this objected on the given car.
	 */
	public void install(Car car);

	/**
	 * @return true if this part is installed on the given car.
	 */
	public boolean isInstalled(Car car);

	/**
	 * @return Instructions in text-format for installing this part.
	 */
	public abstract String getAssemblyInstructions();
}
