package car;

import java.util.LinkedList;
import java.util.Map;

import company.Day;

import system.user.User;
import util.TimeStamp;

/**
 * Represents an order for a car.
 */
public class CarOrder {

	public final String CLIENT;
	public final CarModelSpecification SPECIFICATION;
	private Day completionTime;
	private int expectedCompletionTime;
	private boolean finished = false;
	private Map<String, LinkedList<CarPart>> productionScheme = null;

	public CarOrder(String client, CarModelSpecification specification) {
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
	public void setCompletionTime(Day time) {
		if (finished) {
			throw new IllegalStateException("this order is already finished");
		}
		completionTime = time;
	}
	
	public void setExpectedCompletionTime(int time)
	{
		expectedCompletionTime = time;
	}
	
	public int getExpectedCompletionTime()
	{
		return expectedCompletionTime;
	}

	/**
	 * @return estimated completion time, or actual completion time if this
	 *         order is finished
	 */
	public Day getCompletionTime() {
		return completionTime;
	}

	/**
	 * set this order to finished, with given actual completion time
	 */
	private void setFinished(Day time) {
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
