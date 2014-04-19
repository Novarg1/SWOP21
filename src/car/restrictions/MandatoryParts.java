package car.restrictions;

import car.Body;
import car.Color;
import car.Engine;
import car.Gearbox;
import car.OrderSpecification;
import car.Seats;
import car.Wheels;

/**
 * Body, color, engine, gearbox, seats and wheels are mandatory.
 */
public class MandatoryParts extends PartialRestriction {

	@Override
	protected boolean isFulfilled(OrderSpecification spec) {
		return spec.hasPart(Body.class)
				&& spec.hasPart(Color.class)
				&& spec.hasPart(Engine.class)
				&& spec.hasPart(Gearbox.class)
				&& spec.hasPart(Seats.class)
				&& spec.hasPart(Wheels.class);
	}
}
