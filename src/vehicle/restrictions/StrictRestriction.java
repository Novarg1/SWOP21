package vehicle.restrictions;

import vehicle.parts.CarpartsSet;

/**
 * A strict restriction is partially fulfilled for a given specification if and
 * only if it is completely fulfilled for that specification.
 */
public abstract class StrictRestriction extends Restriction {

	@Override
	public boolean isPartiallyFulfilled(CarpartsSet	set) {
		return this.isFulfilled(set);
	}
}
