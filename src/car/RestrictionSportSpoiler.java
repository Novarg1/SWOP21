package car;

/**
 * If a car has a sport body, a spoiler is mandatory.
 */
public class RestrictionSportSpoiler extends PartialRestriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		return !spec.hasPart(Body.SPORT) || spec.hasPart(Spoiler.class);
	}
}
