package vehicle.restrictions;

import vehicle.parts.PartsSet;

/**
 * this class represents a setific restriction and a chain to manage all
 * restrictions
 */
public abstract class Restriction {

	private Restriction successor = null;

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
	 * given set.
	 */
	public boolean checkValidity(PartsSet set) {
		return this.isFulfilled(set)
				&& (successor == null || successor.checkValidity(set));
	}

	/**
	 * Checks whether this restriction and successors pass for given set but does
	 * not take into account whether all required parts have been selected or
	 * not.
	 */
	public boolean checkPartialValidity(PartsSet set) {
		return this.isPartiallyFulfilled(set)
				&& (successor == null || successor.checkPartialValidity(set));
	}

	/**
	 * @return true if this restriction is fulfilled for the given set.
	 */
	protected abstract boolean isFulfilled(PartsSet set);

	/**
	 * @return false if there is no way to complete the given set (without
	 *         changing any already chosen carparts) without it violating this
	 *         restriction.
	 */
	protected abstract boolean isPartiallyFulfilled(PartsSet set);

	/**
	 * A trivial restriction that is always fulfilled.
	 */
	public static Restriction TRIVIAL_RESTRICTION = new Restriction() {

		@Override
		protected boolean isFulfilled(PartsSet set) {
			return true;
		}

		@Override
		protected boolean isPartiallyFulfilled(PartsSet set) {
			return true;
		}
	};
}
