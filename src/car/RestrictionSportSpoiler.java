package car;

/**
 * If a car has a sport body, a spoiler is mandatory.
 */
public class RestrictionSportSpoiler extends Restriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		try {
			return ! spec.getBody().equals(Body.SPORT)
					|| spec.spoilerChosen();
		} catch(NullPointerException e) {
			return false;
		}
	}

	@Override
	protected boolean isPartiallyFulfilled(ModelSpecification spec) {
		return true;
	}
}
