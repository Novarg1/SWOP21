package car.restrictions;

import car.parts.Body;
import car.parts.CarpartsSet;
import car.parts.Engine;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class SportEngineRestriction extends StrictRestriction {

	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		return !set.contains(Body.SPORT)
				|| set.contains(Engine.PERFORMANCE)
				|| set.contains(Engine.ULTRA);
	}
}
