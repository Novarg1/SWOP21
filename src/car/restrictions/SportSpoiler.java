package car.restrictions;

import car.Body;
import car.OrderSpecification;
import car.Spoiler;

/**
 * If a car has a sport body, a spoiler is mandatory.
 */
public class SportSpoiler extends PartialRestriction {

	@Override
	protected boolean isFulfilled(OrderSpecification spec) {
		return !spec.hasPart(Body.SPORT) || spec.hasPart(Spoiler.class);
	}
}
