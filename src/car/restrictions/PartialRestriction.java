package car.restrictions;

import car.parts.CarpartsSet;

/**
 * A partialrestriction is always 'partially fulfilled'.
 */
public abstract class PartialRestriction extends Restriction {

	@Override
	protected boolean isPartiallyFulfilled(CarpartsSet set) {
		return true;
	}
}
