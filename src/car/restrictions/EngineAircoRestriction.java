package car.restrictions;

import car.parts.Airco;
import car.parts.CarpartsSet;
import car.parts.Engine;

/**
 * If the ultra engine is selected, you can only fit the manual airco.
 */
public class EngineAircoRestriction extends StrictRestriction {

	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		return !set.contains(Engine.ULTRA)
				|| !set.containsType(Airco.class)
				|| set.contains(Airco.MANUAL);
	}
}
