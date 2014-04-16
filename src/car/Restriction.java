package car;

/**
 * this class represents a specific restriction and a chain to manage all
 * restrictions
 */
public abstract class Restriction {

	protected Restriction successor = null;

	/**
	 * adds the successor to the chain of restrictions
	 */
	public void setSuccessor(Restriction successor) {
		if (this.successor == null) {
			this.successor = successor;
		} else {
			this.successor.setSuccessor(successor);
		}
	}

	/**
	 * checks whether this constraint and all successors are fulfilled for the
	 * given specification
	 */
	public boolean checkValidity(ModelSpecification spec) {
		return this.isFulfilled(spec)
				&& (successor == null || successor.checkValidity(spec));
	}

	/**
	 * Checks wheter this restriction and successors pass for given
	 * specification but does not take into account whether all required parts
	 * have been selected or not
	 */
	public boolean checkPartialValidity(ModelSpecification spec) {
		return this.isPartiallyFulfilled(spec)
				&& (successor == null || successor.checkPartialValidity(spec));
	}

	/**
	 * checks whether this constraint is fulfilled.
	 */
	protected abstract boolean isFulfilled(ModelSpecification spec);

	/**
	 * @return false if there is no way to complete the given specification,
	 * 		without it violating this restriction.
	 */
	protected abstract boolean isPartiallyFulfilled(ModelSpecification spec);

	/**
	 * @return trivial restriction that is always fulfilled
	 */
	public static Restriction getEmptyRestriction() {
		return new Restriction() {

			@Override
			protected boolean isFulfilled(ModelSpecification spec) {
				return true;
			}

			@Override
			protected boolean isPartiallyFulfilled(ModelSpecification spec) {
				return true;
			}
		};
	}

	/**
	 * @return default chain of restrictions
	 */
	public static Restriction getDefaultRestrictions() {
		return null; // TODO
	}
}
