package vehicle.order;

import util.Timestamp;

public abstract class VehicleOrderBuilder extends OrderBuilder {

	/**
	 * @return null; this kind of order cannot have a deadline.
	 */
	@Override
	public Timestamp getDeadline() {
		return null;
	}
}
