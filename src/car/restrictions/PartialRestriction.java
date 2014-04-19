package car.restrictions;

import car.OrderSpecification;

/**
 * A partialrestriction is always 'partially fulfilled'.
 */
public abstract class PartialRestriction extends Restriction {

	@Override
	protected boolean isPartiallyFulfilled(OrderSpecification spec) {
		return true;
	}
}
