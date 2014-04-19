package car;

import util.TimeStamp;
import car.parts.Carpart;
import car.parts.CarpartsSet;
import car.parts.Seats;

/**
 * An order for installing custom seats in a car.
 */
public class CustomSeats extends CustomOrder {

	public CustomSeats(String client, TimeStamp deadline, Seats seats) {
		super(client, deadline, new CarpartsSet(new Carpart[] { seats }));
	}

	@Override
	protected boolean isValidCarpartsSet(CarpartsSet parts) {
		return (getParts().size() == 1) && getParts().containsType(Seats.class);
	}

	@Override
	public String toString() {
		return "Order for custom seats\n" + getParts();
	}
}
