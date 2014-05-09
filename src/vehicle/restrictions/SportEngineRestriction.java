package vehicle.restrictions;

import vehicle.parts.Body;
import vehicle.parts.CarpartsSet;
import vehicle.parts.Engine;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class SportEngineRestriction extends StrictRestriction {

	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		return !set.contains(Body.SPORT)
				|| set.contains(Engine.PERFORMANCE_25DL_V6)
				|| set.contains(Engine.ULTRA_3L_V8);
	}
}
