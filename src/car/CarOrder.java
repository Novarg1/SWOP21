package car;

import car.models.CarModel;
import car.parts.CarpartsSet;
import util.TimeStamp;

/**
 * Represents an order for a complete car. Contains a model and a selection of
 * carParts.
 */
public class CarOrder extends Order {

	private CarModel model;

	/**
	 * Creates a new order for a complete car.
	 * 
	 * @param client
	 *            The client associated with this order.
	 * @param model
	 *            The model of the ordered car.
	 * @param parts
	 *            The parts that should be installed on the ordered car.
	 */
	public CarOrder(String client, CarModel model, CarpartsSet parts) {
		super(client, parts);
		this.model = model;
		if (!model.isValid(parts)) {
			throw new IllegalArgumentException(
					"Given parts are not supported by given model");
		}
	}

	/**
	 * this method always returns null, since it is not possible to set a
	 * deadline for a carOrder.
	 * 
	 * @return null
	 */
	@Override
	public TimeStamp getDeadline() {
		return null;
	}

	@Override
	public String toString() {
		return "Order for complete car:\nModel: " + model + "\n"
				+ super.getParts();
	}
}
