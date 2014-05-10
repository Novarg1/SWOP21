package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import user.User;
import util.TimeStamp;
import vehicle.Vehicle;
import vehicle.assemblytasks.InstallPart;
import vehicle.assemblytasks.Task;
import vehicle.parts.Carpart;
import vehicle.parts.CarpartsSet;
import vehicle.restrictions.MandatoryPartsRestriction;
import vehicle.restrictions.PlatformBodyWheelsRestriction;
import vehicle.restrictions.EngineAircoRestriction;
import vehicle.restrictions.Restriction;
import vehicle.restrictions.SportEngineRestriction;
import vehicle.restrictions.SportSpoilerRestriction;
import vehicle.restrictions.SupportedPartsRestriction;

public abstract class OrderBuilder {

	private static final int DEFAULT_BUILDING_TIME = 60;
	protected Restriction restriction = Restriction.TRIVIAL_RESTRICTION;
	private CarpartsSet parts;
	private User client;
	private Vehicle vehicle = new Vehicle();

	protected OrderBuilder() {
		parts = new CarpartsSet();
		setDefaultRestrictions();
	}

	/**
	 * Initializes the default restrictions for this orderspecification
	 */
	private void setDefaultRestrictions() {
		addRestriction(new EngineAircoRestriction());
		addRestriction(new SportEngineRestriction());
		addRestriction(new SportSpoilerRestriction());
		addRestriction(new PlatformBodyWheelsRestriction());
		addRestriction(new MandatoryPartsRestriction(this));
		addRestriction(new SupportedPartsRestriction(this));
	}

	/**
	 * Returns the user that built this orderspecification.
	 * @return
	 */
	public User getClient() {
		return client;
	}

	/**
	 * Sets the user building this orderspecification to the given user.
	 */
	public void setClient(User client) {
		this.client = client;
	}
	
	/**
	 * Returns the vehicle associated with the order being built.
	 * @return
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	/**
	 * @return The deadline for the order this class specifies.
	 */
	public abstract TimeStamp getDeadline();

	/**
	 * @return time in minutes that is spent in each workstation in normal
	 *         circumstances.
	 */
	public int getBuildingTimePerWorkstation() {
		return DEFAULT_BUILDING_TIME;
	}

	/**
	 * @return all CarParts that are supported by this specification.
	 */
	public abstract Set<Carpart> getSupportedCarparts();

	/**
	 * @return all carparts of the given type that are supported by this
	 *         specification.
	 */
	private Set<Carpart> getSupportedCarparts(Class<? extends Carpart> type) {
		Set<Carpart> result = new HashSet<>();
		for (Carpart part : getSupportedCarparts()) {
			if (part.getClass().equals(type)) {
				result.add(part);
			}
		}
		return result;
	}

	/**
	 * @return all types of carparts that are supported by this specification
	 */
	public Set<Class<? extends Carpart>> getSupportedTypes() {
		Set<Class<? extends Carpart>> result = new HashSet<>();
		for (Carpart part : getSupportedCarparts()) {
			result.add(part.getClass());
		}
		return result;
	}

	/**
	 * @return All parts of the given type that (1) are supported by this
	 *         specification and (2) would not render this specification
	 *         invalid.
	 */
	public Set<Carpart> getViableOptions(Class<? extends Carpart> type) {
		Carpart current = parts.get(type);
		Set<Carpart> result = getSupportedCarparts(type);
		for (Carpart part : result) {
			parts.add(part);
			if (!isPartiallyValid()) {
				result.remove(part);
			}
			parts.remove(part);
		}
		parts.add(current);
		return result;
	}

	/**
	 * Adds the given part to this specification.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given part is null or not viable.
	 */
	public void add(Carpart part) {
		if (part == null || !getViableOptions(part.getClass()).contains(part)) {
			throw new IllegalArgumentException("invalid carpart");
		}
		parts.add(part);
	}

	/**
	 * @return a copy of the carpartsSet containing the parts of this
	 *         specification.
	 */
	public CarpartsSet getParts() {
		return parts.clone();
	}

	/**
	 * The set of assembly tasks that the order in construction has.
	 */
	public Set<Task> getTasks() {
		Set<Task> tasks = new HashSet<>(getModelSpecificTasks());
		for (Carpart part : getParts()) {
			tasks.add(new InstallPart(part, vehicle));
		}
		return tasks;
	}
	
	/**
	 * returns the tasks that are specific to this type of orderspecification.
	 */
	protected abstract Set<Task> getModelSpecificTasks();
	
	/**
	 * Checks wheter all restrictions pass for the given set but does not take
	 * parts into account that have not (yet) been selected.
	 */
	protected boolean isPartiallyValid() {
		return restriction == null || restriction.checkPartialValidity(parts);
	}

	/**
	 * Adds the given restriction to the chain of restrictions of this model.
	 */
	protected void addRestriction(Restriction restriction) {
		if (this.restriction == null) {
			this.restriction = restriction;
		} else {
			this.restriction.setSuccessor(restriction);
		}
	}

	/**
	 * @return true if the given set is a valid set of carParts for this model.
	 */
	public boolean isValid() {
		return restriction == null || restriction.checkValidity(parts);
	}

	/**
	 * @return an order with this specification.
	 * 
	 * @throws IllegalStateException
	 *             if this specification is not valid.
	 */
	public Order extractOrder() {
		try {
			return new Order(this);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException(
					"must be valid before order can be extracted");
		}
	}

	public boolean containsPart(Class<? extends Carpart> type) {
		return this.getParts().containsType(type);
	}

	@Override
	public abstract String toString();
}
