package car.restrictions;

import car.OrderSpecification;

/**
 * A strict restriction is partially fulfilled for a given specification if and
 * only if it is completely fulfilled for that specification.
 */
public abstract class StrictRestriction extends Restriction {

	@Override
	public boolean isPartiallyFulfilled(OrderSpecification spec) {
		return this.isFulfilled(spec);
	}
}
