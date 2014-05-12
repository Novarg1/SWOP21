package vehicle.restrictions;

import vehicle.parts.Body;
import vehicle.parts.PartsSet;
import vehicle.parts.Wheels;

/**
 * If the platform body is selected, the heavy duty wheels must be selected as
 * well.
 */
public class PlatformBodyWheelsRestriction extends StrictRestriction {

	@Override
	protected boolean isFulfilled(PartsSet set) {
		return !set.contains(Body.PLATFORM)
				|| set.contains(Wheels.HEAVY_DUTY);
	}
}
