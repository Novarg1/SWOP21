package car;

/**
 * A partialrestriction is always 'partially fulfilled'.
 */
public abstract class PartialRestriction extends Restriction {

	@Override
	protected boolean isPartiallyFulfilled(ModelSpecification spec) {
		return true;
	}
}
