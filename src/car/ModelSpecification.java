package car;

import java.util.HashSet;
import java.util.Set;

/**
 * invariant: Van elke subklasse van carPart mag hoogstens 1 instantie in de
 * lijst van parts zitten
 */
public abstract class ModelSpecification {

	private Set<CarPart> parts;
	private Restriction restriction;

	private final int buildingTime;
	private static final int DEFAULT_BUILDING_TIME = 60;

	/**
	 * model with default building time per workstation
	 */
	public ModelSpecification() {
		this(DEFAULT_BUILDING_TIME);
	}

	/**
	 * model with given building time per workstation
	 */
	public ModelSpecification(int buildingTime) {
		restriction = getModelRestriction();
		this.buildingTime = buildingTime;
		parts = new HashSet<>();
	}

	/**
	 * @return The part of the given type that this specification contains. Null
	 *         if this specification does not contain a part of this type (yet).
	 */
	public CarPart getPart(Class<? extends CarPart> type) {
		for (CarPart part : parts) {
			if (type.equals(part.getClass())) {
				return part;
			}
		}
		return null;
	}

	/**
	 * adds the given part to this specification and removes the previous part
	 * of the same type, if this specification had such part. setPart(null) will
	 * removes this potential previous part and not set a new one.
	 */
	public void setPart(CarPart part) {
		parts.remove(getPart(part.getClass())); // respect class-invariant
		parts.add(part);
	}

	/**
	 * @return true if this specification contains the given part.
	 */
	public boolean hasPart(CarPart part) {
		return parts.contains(part);
	}

	/**
	 * @return true if this specification contains a part of the given type.
	 */
	public boolean hasPart(Class<? extends CarPart> type) {
		return getPart(type) == null;
	}

	/**
	 * @return All parts of the given type that (1) are supported by this model
	 *         and (2) would not render this specification invalid.
	 */
	public <Part extends CarPart> Set<CarPart> getViableOptions(Class<Part> type) {
		CarPart current = getPart(type);
		Set<CarPart> result = getAllOptions(type);
		for (CarPart part : result) {
			setPart(part);
			if (!this.isPartiallyValid()) {
				result.remove(part);
			}
		}
		setPart(current);
		return result;
	}

	/**
	 * @return All parts of the given type that are supported by this model.
	 */
	private <Part extends CarPart> Set<CarPart> getAllOptions(Class<Part> type) {
		Set<CarPart> result = new HashSet<>();
		for (CarPart part : getAllSupportedParts()) {
			if (part.getClass().equals(type)) {
				result.add(part);
			}
		}
		return result;
	}

	/**
	 * @return All types of carParts that are supported by this model.
	 */
	private Set<Class<? extends CarPart>> getAllPartTypes() {
		Set<Class<? extends CarPart>> result = new HashSet<>();
		for (CarPart part : getAllSupportedParts()) {
			result.add(part.getClass());
		}
		return result;
	}

	/**
	 * @return all CarParts that are supported by this model.
	 */
	protected abstract CarPart[] getAllSupportedParts();

	/**
	 * Adds the given restriction to the chain of restrictions of this
	 * specification
	 */
	public void addRestriction(Restriction restriction) {
		if (this.restriction == null) {
			this.restriction = restriction;
		} else {
			this.restriction.setSuccessor(restriction);
		}
	}

	/**
	 * Checks wheter all restrictions pass for this specification but does not
	 * take parts into account that have not (yet) been selected.
	 */
	protected boolean isPartiallyValid() {
		return restriction == null || restriction.checkPartialValidity(this);
	}

	/**
	 * @return true if this is a valid and complete modelspecification
	 */
	public boolean isValid() {
		return restriction == null || restriction.checkValidity(this);
	}

	/**
	 * @return Restriction that states that all chosen parts must be supported
	 *         by this model.
	 */
	private Restriction getModelRestriction() {
		return new Restriction() {

			@Override
			protected boolean isFulfilled(ModelSpecification spec) {
				for (Class<? extends CarPart> type : getAllPartTypes()) {
					CarPart part = getPart(type);
					if (part != null && !getAllOptions(type).contains(part)) {
						return false;
					}
				}
				return true;
			}

			@Override
			protected boolean isPartiallyFulfilled(ModelSpecification spec) {
				return this.isFulfilled(spec);
			}
		};
	}

	/**
	 * @return Estimated time in minutes that is spent in each workstation.
	 */
	public int getBuildingTimePerWorkstation() {
		return buildingTime;
	}

	/**
	 * returns a string representation for this object
	 * 
	 * @return a string representation for this object
	 */
	@Override
	public String toString() {
		String result = "";
		for (Class<? extends CarPart> type : getAllPartTypes()) {
			result += type.getName() + ": " + getPart(type) + "\n";
		}
		return result;
	}
}
