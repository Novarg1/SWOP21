package car.restrictions;

import car.Airco;
import car.Engine;
import car.OrderSpecification;

/**
 * If the ultra engine is selected, you can only fit the manual airco.
 */
public class EngineAirco extends StrictRestriction {

	@Override
	protected boolean isFulfilled(OrderSpecification spec) {
		return !spec.hasPart(Engine.ULTRA)
				|| !spec.hasPart(Airco.class)
				|| spec.hasPart(Airco.MANUAL);
	}
}
