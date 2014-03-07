package car;

import system.user.GarageHolder;
import util.TimeStamp;

public class CarOrder implements Comparable {

	private GarageHolder client;
	private CarSpecification specification;
	private TimeStamp completionTime;
	private boolean finished = false;

	public CarOrder(GarageHolder client, CarSpecification specification) {
		this.client = client;
		this.specification = specification;
	}

	/**
	 * @return the garageHolder who placed this order
	 */
	public GarageHolder getClient() {
		return client;
	}
	
	/**
	 * set estimated completion time of this order to given time
	 * 
	 * @param time
	 */
	public void setCompletionTime(TimeStamp time) {
		if (finished) {
			throw new IllegalArgumentException("this order is already finished");
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
		return this.specification.toString();
	}

	@Override
	public int compareTo(Object other) {
		try {
			return this.completionTime.isBefore(((CarOrder) other)
					.getCompletionTime()) ? -1 : 1;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException();
		}
	}

}
