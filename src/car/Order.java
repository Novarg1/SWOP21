package car;

import car.parts.CarpartsSet;
import util.TimeStamp;

public class Order {

	private final User client; //TODO import
	private final CarpartsSet parts;
	private final int buildingtime;
	private final TimeStamp deadline;
	private TimeStamp completionTime = null;
	private boolean finished = false;

	protected Order(OrderSpecification spec) {
		if(spec == null || !spec.isValid()) {
			throw new IllegalArgumentException("invalid specification");
		}
		this.parts = spec.getParts();
		this.buildingtime = spec.getBuildingTimePerWorkstation();
		this.deadline = spec.getDeadline();
	}

	/**
	 * @return the client associated with this order.
	 */
	public User getClient() {
		return client;
	}
	
	/**
	 * Sets this order as finished on the given time.
	 * 
	 * @throws IllegalStateException
	 *             if this order is already finished
	 */
	public void setFinished(TimeStamp time) {
		if (finished) {
			throw new IllegalStateException("this order is already finished");
		}
		completionTime = time;
		finished = true;
	}

	/**
	 * @return completion time, or null time if this order is not yet finished
	 */
	public TimeStamp getCompletionTime() {
		return completionTime;
	}

	/**
	 * @return deadline of this order or null if this order has no deadline.
	 */
	public TimeStamp getDeadline() {
		return deadline;
	}

	/**
	 * @return true if this order has been finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @return A copy of the set containing all parts this specification has.
	 */
	public CarpartsSet getParts() {
		return parts.clone();
	}

	/**
	 * @return true if this order and the given order have the same set of
	 *         carParts (not necessarily the same model/task).
	 */
	public boolean matches(Order other) {
		return this.parts.equals(other.parts);
	}

	/**
	 * @return time in minutes that is spent in each workstation in normal
	 *         circumstances.
	 */
	public int getBuildingTimePerWorkstation() {
		return buildingtime;
	}

	@Override
	public String toString() {
		return "Order"; //TODO
	}
}
