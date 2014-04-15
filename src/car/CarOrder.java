package car;

import java.util.List;
import java.util.Map;

import company.Day;

/**
 * Represents an order for a car.
 */
public class CarOrder {

	public final String CLIENT;
	public final ModelSpecification SPECIFICATION;
	private Day completionTime;
	private int expectedCompletionTime;
	private boolean finished = false;
	private Map<String, List<CarPart<?>>> productionScheme = null;

	public CarOrder(String client, ModelSpecification specification) {
		if(specification == null ||
				!specification.isValid(false))
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
	 * @return true if this order has been finished
	 */
	public boolean isFinished() {
		return finished;
	}

	@Override
	public String toString() {
		return SPECIFICATION.toString();
	}
	
	public void setProductionScheme(Map<String, List<CarPart<?>>> productionScheme) {
		this.productionScheme = productionScheme;
	}
	
	public List<CarPart<?>> getProductionSchemeFor(String workstationId) {
		if(this.productionScheme.containsKey(workstationId))
			return this.productionScheme.get(workstationId);
		return null;
	}

}
