package vehicle.restrictions;

import vehicle.parts.PartsSet;

/**
 * A strict restriction is partially fulfilled for a given specification if and
 * only if it is completely fulfilled for that specification.
 */
public abstract class StrictRestriction extends Restriction {

	@Override
	public boolean isPartiallyFulfilled(PartsSet	set) {
		return this.isFulfilled(set);
	}
}
