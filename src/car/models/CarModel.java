package car.models;

import java.util.HashSet;
import java.util.Set;

import car.parts.Carpart;
import car.parts.CarpartsSet;
import car.restrictions.Restriction;

/**
 * A car model contains a set of supported carparts and the worktime that is
 * spent in each workstation in order to build a car of this model.
 */
public abstract class CarModel {

	private static final int DEFAULT_BUILDING_TIME = 60;
	private Restriction restriction;

	public CarModel() {
		addRestriction(getModelRestriction());
		addRestriction(Restriction.getDefaultRestrictions());
	}

	/**
	 * @return time in minutes that is spent in each workstation in normal
	 *         circumstances.
	 */
	public int getBuildingTimePerWorkstation() {
		return DEFAULT_BUILDING_TIME;
	}

	/**
	 * @return Restriction that states that all chosen parts must be supported
	 *         by this model.
	 */
	private Restriction getModelRestriction() {
		return new Restriction() {

			@Override
			protected boolean isFulfilled(CarpartsSet set) {
				for (Carpart part : set) {
					if (!getSupportedCarparts().contains(part)) {
						return false;
					}
				}
				return true;
			}

			@Override
			protected boolean isPartiallyFulfilled(CarpartsSet set) {
				return this.isFulfilled(set);
			}
		};
	}

	/**
	 * @return all CarParts that are supported by this model.
	 */
	public abstract Set<Carpart> getSupportedCarparts();

	/**
	 * @return all carparts of the given type that are supported by this model.
	 */
	public Set<Carpart> getSupportedCarparts(Class<? extends Carpart> type) {
		Set<Carpart> result = new HashSet<>();
		for (Carpart part : getSupportedCarparts()) {
			if (part.getClass().equals(type)) {
				result.add(part);
			}
		}
		return result;
	}

	/**
	 * @return All parts of the given type that (1) are supported by this model
	 *         and (2) would not render the given set of parts invalid.
	 */
	public Set<Carpart> getViableOptions(Class<? extends Carpart> type,
			CarpartsSet set) {
		Carpart current = set.get(type);
		Set<Carpart> result = getSupportedCarparts(type);
		for (Carpart part : result) {
			set.add(part);
			if (!restriction.checkPartialValidity(set)) {
				result.remove(part);
			}
		}
		set.add(current);
		return result;
	}

	/**
	 * Checks wheter all restrictions pass for the given set but does not take
	 * parts into account that have not (yet) been selected.
	 */
	protected boolean isPartiallyValid(CarpartsSet set) {
		return restriction == null || restriction.checkPartialValidity(set);
	}

	/**
	 * Adds the given restriction to the chain of restrictions of this model.
	 */
	private void addRestriction(Restriction restriction) {
		if (this.restriction == null) {
			this.restriction = restriction;
		} else {
			this.restriction.setSuccessor(restriction);
		}
	}

	/**
	 * @return true if the given set is a valid set of carParts for this model.
	 */
	public boolean isValid(CarpartsSet set) {
		return restriction == null || restriction.checkValidity(set);
	}

	@Override
	public abstract String toString();
}