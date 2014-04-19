package car.restrictions;

import car.Body;
import car.Engine;
import car.OrderSpecification;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class SportEngine extends StrictRestriction {

	@Override
	protected boolean isFulfilled(OrderSpecification spec) {
		return !spec.hasPart(Body.SPORT)
				|| spec.hasPart(Engine.PERFORMANCE)
				|| spec.hasPart(Engine.ULTRA);
	}
}
