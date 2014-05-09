package vehicle;

import vehicle.order.Order;
import vehicle.parts.Carpart;
import vehicle.parts.CarpartsSet;

/**
 * Represents a vehicle. A vehicle has a set of installed vehicleparts and an associated
 * order.
 */
public class Vehicle {

	private Order order;
	private CarpartsSet parts;

	/**
	 * Creates a new vehicle, associated with the given order.
	 * 
	 * @param order
	 */
	public Vehicle(Order order) {
		this.order = order;
		parts = new CarpartsSet();
	}

	/**
	 * @return The order associated with this vehicle.
	 */
	public Order getOrder() {
		return order;
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

	/**
	 * @return true if all parts specified on the order are installed on this
	 *         vehicle, and all parts installed are specified on the order.
	 */
	public boolean matchesOrder() {
		return parts.equals(order.getParts());
	}
}
