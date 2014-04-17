package car;

import java.util.List;
import java.util.Map;

import util.TimeStamp;

/**
 * Represents an order for a car.
 */
public class CarOrder {

	public final String CLIENT;
	public final ModelSpecification SPECIFICATION;
	private TimeStamp completionTime;
	private boolean finished = false;
	private Map<String, List<CarPart<?>>> productionScheme = null;

	public CarOrder(String client, ModelSpecification specification) {
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
	 * @return true if this order has been finished
	 */
	public boolean isFinished() {
		return finished;
	}

	@Override
	public String toString() {
		return SPECIFICATION.toString();
	}

	public void setProductionScheme(
			Map<String, List<CarPart<?>>> productionScheme) {
		this.productionScheme = productionScheme;
	}

	public List<CarPart<?>> getProductionSchemeFor(String workstationId) {
		if (this.productionScheme.containsKey(workstationId))
			return this.productionScheme.get(workstationId);
		return null;
	}

}
