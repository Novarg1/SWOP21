package car;

import car.parts.Carpart;
import car.parts.CarpartsSet;

/**
 * Represents a car. A car has a set of installed carparts and an associated
 * order.
 */
public class Car {

	private Order order;
	private CarpartsSet parts;

	/**
	 * Creates a new car, associated with the given order.
	 * 
	 * @param order
	 */
	public Car(Order order) {
		this.order = order;
		parts = new CarpartsSet();
	}

	/**
	 * @return The order associated with this car.
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @return a copy of the set containing the parts installed on this car.
	 */
	public CarpartsSet getParts() {
		return parts.clone();
	}

	/**
	 * installs the given part on this car if no part of this type is installed
	 * already.
	 * 
	 * @return true if the given part was succesfully installed; false if this
	 *         car already had a part of the given type installed.
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
	 *         car, and all parts installed are specified on the order.
	 */
	public boolean matchesOrder() {
		return parts.equals(order.getParts());
	}
}
