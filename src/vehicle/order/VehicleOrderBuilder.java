package vehicle.order;

import util.TimeStamp;

public abstract class VehicleOrderBuilder extends OrderBuilder {

	/**
	 * @return null; this kind of order cannot have a deadline.
	 */
	@Override
	public TimeStamp getDeadline() {
		return null;
	}
}
