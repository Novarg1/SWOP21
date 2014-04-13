package car;

import java.util.LinkedList;
import java.util.Map;

import system.user.User;
import util.TimeStamp;

/**
 * Represents an order for a car.
 */
public class CarOrder {

	public final User CLIENT;
	public final CarModelSpecification SPECIFICATION;
	private TimeStamp completionTime;
	private boolean finished = false;
	private Map<String, LinkedList<CarPart>> productionScheme = null;

	public CarOrder(User client, CarModelSpecification specification) {
		if(specification == null ||
				specification.isValid(false))
		{
			throw new IllegalArgumentException();
		}
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
	
	public void setProductionScheme(Map<String, LinkedList<CarPart>> productionScheme)
	{
		this.productionScheme = productionScheme;
	}
	
	public LinkedList<CarPart> getProductionSchemeFor(String workstationId)
	{
		if(this.productionScheme.containsKey(workstationId))
			return this.productionScheme.get(workstationId);
		return null;
	}

}
