package vehicle.parts;

import company.workstations.Workstation;

/**
 * interface for representing all carParts a carpart knows how to install itself
 * and whether or not it has been installed already on a given car.
 */
public interface Part {

	/**
	 * Returns the type of workstation in which this carpart can be installed.
	 */
	public abstract Class<? extends Workstation> getResponsibleWorkstation();
}
