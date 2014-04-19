package car;

import java.util.HashSet;
import java.util.Set;

import car.restrictions.Restriction;

/*
 * invariant: Van elke subklasse van carPart mag hoogstens 1 instantie in de
 * lijst van parts zitten
 */

/**
 * Represents the specification of an order.
 */
public abstract class OrderSpecification {

	private Set<CarPart> parts;
	private Restriction restriction;

	private final String name;
	private final int buildingTime;
	private static final int DEFAULT_BUILDING_TIME = 60;

	/**
	 * model with default building time per workstation
	 */
	public OrderSpecification(String modelName) {
		this(modelName, DEFAULT_BUILDING_TIME);
	}

	/**
	 * model with given building time per workstation
	 */
	public OrderSpecification(String modelName, int buildingTime) {
		name = modelName;
		restriction = getModelRestriction();
		this.buildingTime = buildingTime;
		parts = new HashSet<>();
	}

	/**
	 * @return A set containing all parts this specification has.
	 */
	public Set<CarPart> getParts() {
		return new HashSet<>(parts);
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
	public Set<CarPart> getViableOptions(Class<? extends CarPart> type) {
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
	private Set<CarPart> getAllOptions(Class<? extends CarPart> type) {
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
	 * @return true if this and other have the same set of carParts and are the
	 *         same type of orderspecification.
	 */
	public boolean matches(OrderSpecification other) {
		return this.getClass().equals(other.getClass())
				&& this.parts.equals(other.parts);
	}

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
			protected boolean isFulfilled(OrderSpecification spec) {
				for (Class<? extends CarPart> type : getAllPartTypes()) {
					CarPart part = getPart(type);
					if (part != null && !getAllOptions(type).contains(part)) {
						return false;
					}
				}
				return true;
			}

			@Override
			protected boolean isPartiallyFulfilled(OrderSpecification spec) {
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
	 * @return the name of this orderSpecification (e.g. "modelA")
	 */
	public String getModelName() {
		return name;
	}

	/**
	 * returns a string representation for this object
	 * 
	 * @return a string representation for this object
	 */
	@Override
	public String toString() {
		String result = getModelName();
		for (Class<? extends CarPart> type : getAllPartTypes()) {
			result += "\n" + type.getName() + ": " + getPart(type);
		}
		return result;
	}
}
