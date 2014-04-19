package car.restrictions;

import car.parts.Body;
import car.parts.CarpartsSet;
import car.parts.Spoiler;

/**
 * If a car has a sport body, a spoiler is mandatory.
 */
public class SportSpoilerRestriction extends PartialRestriction {

	@Override
	protected boolean isFulfilled(CarpartsSet spec) {
		return !spec.contains(Body.SPORT) || spec.containsType(Spoiler.class);
	}
}
