package car;

import util.TimeStamp;

public class CarOrder {

	private CarModelSpecification specification;
	private TimeStamp completionTime;
	private boolean finished = false;

	public CarOrder(CarModelSpecification specification)
	{
		this.specification = specification;
	}

	/**
	 * set estimated completion time of this order to given time
	 * @param time
	 */
	public void setCompletionTime(TimeStamp time) {
		if(finished) {
			throw new IllegalArgumentException("this order is already finished");
		}
		completionTime = time;
	}

	/**
	 * @return
	 * 		estimated completion time, or actual completion time if this
	 *		order is finished
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
	 * @return
	 * 		true if this order has been finished
	 */
	public boolean isFinished() {
		return finished;
	}
	
	@Override
	public String toString() {
		return this.specification.toString();
	}

}
