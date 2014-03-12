package car;

import system.user.User;
import util.TimeStamp;

/**
 * Represents an order for a car.
 */
public class CarOrder {

	public final User CLIENT;
	public final CarSpecification SPECIFICATION;
	private TimeStamp completionTime;
	private boolean finished = false;

	public CarOrder(User client, CarSpecification specification) {
		CLIENT = client;
		SPECIFICATION = specification;
	}

	/**
	 * set estimated completion time of this order to given time
	 * 
	 * @param time
	 */
	public void setCompletionTime(TimeStamp time) {
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
	 * set this order to finished, with given actual completion time
	 */
	public void setFinished(TimeStamp time) {
		setCompletionTime(time);
		finished = true;
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
