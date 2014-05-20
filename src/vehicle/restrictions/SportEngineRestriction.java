package vehicle.restrictions;

import vehicle.parts.Body;
import vehicle.parts.PartsSet;
import vehicle.parts.Engine;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class SportEngineRestriction extends Restriction {

	@Override
	protected boolean isFulfilled(PartsSet set) {
		return !set.contains(Body.SPORT)
				|| set.contains(Engine.PERFORMANCE_25DL_V6)
				|| set.contains(Engine.ULTRA_3L_V8);
	}

	@Override
	protected boolean isPartiallyFulfilled(PartsSet set) {
		return this.isFulfilled(set) || !set.contains(Engine.class);
	}
}
