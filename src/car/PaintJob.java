package car;

import util.TimeStamp;
import car.parts.Carpart;
import car.parts.CarpartsSet;
import car.parts.Color;

/**
 * An order for spray painting a car.
 */
public class PaintJob extends CustomOrder {

	public PaintJob(String client, TimeStamp deadline, Color color) {
		super(client, deadline, new CarpartsSet(new Carpart[] { color }));
	}

	@Override
	protected boolean isValidCarpartsSet(CarpartsSet parts) {
		return (getParts().size() == 1) && getParts().containsType(Color.class);
	}

	@Override
	public String toString() {
		return "Order for spray painting\n" + getParts();
	}
}
