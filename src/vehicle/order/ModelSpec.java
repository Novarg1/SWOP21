package vehicle.order;

import util.TimeStamp;

public abstract class ModelSpec extends OrderSpecification {

	/**
	 * @return null; this kind of order cannot have a deadline.
	 */
	@Override
	public TimeStamp getDeadline() {
		return null;
	}

	/**
	 * throws IllegalArgumentException; this kind of order cannot have a
	 * deadline.
	 */
	@Override
	public void setDeadline(TimeStamp deadline) {
		throw new IllegalArgumentException(
				"this kind of order cannot have a deadline");
	}
}
