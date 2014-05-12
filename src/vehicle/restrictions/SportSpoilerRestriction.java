package vehicle.restrictions;

import vehicle.parts.Body;
import vehicle.parts.PartsSet;
import vehicle.parts.Spoiler;

/**
 * If a car has a sport body, a spoiler is mandatory.
 */
public class SportSpoilerRestriction extends PartialRestriction {

	@Override
	protected boolean isFulfilled(PartsSet spec) {
		return !spec.contains(Body.SPORT) || spec.contains(Spoiler.class);
	}
}
