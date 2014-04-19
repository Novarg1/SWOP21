package car;

import car.parts.Carpart;
import car.parts.CarpartsSet;
import util.TimeStamp;

public abstract class Order {

	private String client;
	private TimeStamp completionTime;
	private boolean finished = false;
	private CarpartsSet parts;

	protected Order(String client, CarpartsSet parts) {
		if (client == null) {
			throw new IllegalArgumentException("invalid client");
		}
		this.client = client;
		this.parts = parts.clone();
	}

	/**
	 * @return the client that placed this order.
	 */
	public String getClient() {
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
	 * sets estimated completion time to the given time.
	 * 
	 * @throws IllegalStateException
	 *             if this order is already finished
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
	 * @return deadline of this order or null if this order has no deadline.
	 */
	public abstract TimeStamp getDeadline();

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
	 * @return The part of the given type that this specification contains. Null
	 *         if this specification does not contain a part of this type (yet).
	 */
	public Carpart getPart(Class<? extends Carpart> type) {
		for (Carpart part : parts) {
			if (type.equals(part.getClass())) {
				return part;
			}
		}
		return null;
	}

	/**
	 * @return true if this order and the given order have the same set of
	 *         carParts and are the same type of order.
	 */
	public boolean matches(Order other) {
		return this.getClass().equals(other.getClass())
				&& this.parts.equals(other.parts);
	}

	@Override
	public abstract String toString();
}
