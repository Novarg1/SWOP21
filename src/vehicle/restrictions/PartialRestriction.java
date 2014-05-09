package vehicle.restrictions;

import vehicle.parts.CarpartsSet;

/**
 * A partialrestriction is always 'partially fulfilled'.
 */
public abstract class PartialRestriction extends Restriction {

	@Override
	protected boolean isPartiallyFulfilled(CarpartsSet set) {
		return true;
	}
}
