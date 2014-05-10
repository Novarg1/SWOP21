package vehicle;

import vehicle.parts.Carpart;
import vehicle.parts.CarpartsSet;

/**
 * Represents a vehicle. A vehicle has a set of installed vehicleparts
 * order.
 */
public class Vehicle {

	private CarpartsSet parts;

	/**
	 * Creates a new vehicle without any parts or specification.
	 * 
	 * @param order
	 */
	public Vehicle() {
		parts = new CarpartsSet();
	}

	/**
	 * @return a copy of the set containing the parts installed on this vehicle.
	 */
	public CarpartsSet getParts() {
		return parts.clone();
	}

	/**
	 * installs the given part on this vehicle if no part of this type is installed
	 * already.
	 * 
	 * @return true if the given part was succesfully installed; false if this
	 *         vehicle already had a part of the given type installed.
	 */
	public boolean install(Carpart part) {
		if (parts.containsType(part.getClass())) {
			return false;
		}
		parts.add(part);
		return true;
	}
}
