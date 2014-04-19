package car.parts;

/**
 * interface for representing all carParts a carpart knows how to install itself
 * and whether or not it has been installed already on a given car.
 */
public interface Carpart {

	/**
	 * @return Instructions in text-format for installing this part.
	 */
	public abstract String getAssemblyInstructions();

	/**
	 * @return id-number of the workstation in which this carPart is supposed to
	 *         be installed
	 */
	public abstract int getWorkStationID();
}
