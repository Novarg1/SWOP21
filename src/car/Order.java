package car;

import util.TimeStamp;

/**
 * Represents an order for a car.
 */
public class Order {

	public final String CLIENT;
	public final OrderSpecification SPECIFICATION;
	private TimeStamp completionTime;
	private boolean finished = false;

	public Order(String client, OrderSpecification specification) {
		if (specification == null || !specification.isValid()) {
			throw new IllegalArgumentException("invalid specification");
		}
		CLIENT = client;
		SPECIFICATION = specification;
	}

	/**
	 * Sets this order as finished on the given time.
	 *
	 * @throws IllegalStateException if this order is already finished
	 */
	public void setFinished(TimeStamp time) {
		if (finished) {
			throw new IllegalStateException("this order is already finished");
		}
		completionTime = time;
		finished = true;
	}

	/**
	 * sets estimated completion time to the given time.
	 * 
	 * @throws IllegalStateException if this order is already finished
	 */
	public void setEstimatedCompletionTime(TimeStamp time) {
		if (finished) {
			throw new IllegalStateException("this order is already finished");
		}
		completionTime = time;
	}

	/**
	 * @return estimated completion time, or actual completion time if this
	 *         order is finished
	 */
	public TimeStamp getCompletionTime() {
		return completionTime;
	}

	/**
	 * @return true if this order has been finished
	 */
	public boolean isFinished() {
		return finished;
	}

	@Override
	public String toString() {
		return SPECIFICATION.toString();
	}
}
