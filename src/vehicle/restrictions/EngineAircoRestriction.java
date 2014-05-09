package vehicle.restrictions;

import vehicle.parts.Airco;
import vehicle.parts.CarpartsSet;
import vehicle.parts.Engine;

/**
 * If the ultra engine is selected, you can only fit the manual airco.
 */
public class EngineAircoRestriction extends StrictRestriction {

	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		return !set.contains(Engine.ULTRA_3L_V8)
				|| !set.containsType(Airco.class)
				|| set.contains(Airco.MANUAL);
	}
}
