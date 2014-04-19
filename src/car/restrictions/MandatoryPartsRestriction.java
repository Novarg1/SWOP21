package car.restrictions;

import car.parts.Body;
import car.parts.CarpartsSet;
import car.parts.Color;
import car.parts.Engine;
import car.parts.Gearbox;
import car.parts.Seats;
import car.parts.Wheels;

/**
 * Body, color, engine, gearbox, seats and wheels are mandatory.
 */
public class MandatoryPartsRestriction extends PartialRestriction {

	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		return set.containsType(Body.class)
				&& set.containsType(Color.class)
				&& set.containsType(Engine.class)
				&& set.containsType(Gearbox.class)
				&& set.containsType(Seats.class)
				&& set.containsType(Wheels.class);
	}
}
