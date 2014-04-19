package car;

import util.TimeStamp;
import car.parts.CarpartsSet;

public abstract class CustomOrder extends Order {

	private TimeStamp deadline;

	/**
	 * Creates a new custom order.
	 * 
	 * @param client
	 *            The client that is associated with this order.
	 * @param deadline
	 *            The deadline for this order, or null if no deadline was given.
	 * @param parts
	 *            The parts that were ordered to be installed.
	 */
	public CustomOrder(String client, TimeStamp deadline, CarpartsSet parts) {
		super(client, parts);
		if(!isValidCarpartsSet(parts)) {
			throw new IllegalArgumentException("invalid parts for this order");
		}
	}

	@Override
	public TimeStamp getDeadline() {
		return deadline;
	}

	/**
	 * @return true if the given carpartsSet is valid for this order.
	 */
	protected abstract boolean isValidCarpartsSet(CarpartsSet parts);
}
