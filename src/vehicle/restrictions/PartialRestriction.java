package vehicle.restrictions;

import vehicle.parts.PartsSet;

/**
 * A partialrestriction is always 'partially fulfilled'.
 */
public abstract class PartialRestriction extends Restriction {

	@Override
	protected boolean isPartiallyFulfilled(PartsSet set) {
		return true;
	}
}
