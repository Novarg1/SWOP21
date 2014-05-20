package vehicle.order;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import company.workstations.Workstation;
import user.User;
import util.Timestamp;
import vehicle.assemblytasks.InstallPart;
import vehicle.assemblytasks.Task;
import vehicle.parts.Part;
import vehicle.parts.PartsSet;
import vehicle.restrictions.MandatoryPartsRestriction;
import vehicle.restrictions.PlatformBodyWheelsRestriction;
import vehicle.restrictions.EngineAircoRestriction;
import vehicle.restrictions.Restriction;
import vehicle.restrictions.SportEngineRestriction;
import vehicle.restrictions.SportSpoilerRestriction;
import vehicle.restrictions.SupportedPartsRestriction;

public abstract class OrderBuilder {

	private static final int DEFAULT_BUILDTIME = 60;
	protected Restriction restriction = Restriction.trivialRestriction();
	private PartsSet parts;
	private User client;

	protected OrderBuilder() {
		parts = new PartsSet();
		setDefaultRestrictions();
	}

	/**
	 * Initializes the default restrictions for this orderbuilder
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
	 * Returns the user that built this orderbuilder.
	 * 
	 * @return
	 */
	public User getClient() {
		return client;
	}

	/**
	 * Sets the user building this orderbuilder to the given user.
	 */
	public void setClient(User client) {
		this.client = client;
	}

	/**
	 * @return The deadline for the order this class specifies.
	 */
	public abstract Timestamp getDeadline();

	/**
	 * Returns a map containing all needed workstations as key and the estimated
	 * time that will be spent at that workstation as value.
	 */
	public final Map<Class<? extends Workstation>, Integer> getBuildTimePerWorkstation() {
		Map<Class<? extends Workstation>, Integer> result = new HashMap<>();
		for (Task task : getTasks()) {
			Class<? extends Workstation> ws = task.getResponsibleWorkstation();
			int time = getBuildTimeFor(ws);
			result.put(ws, (time > 0) ? time : DEFAULT_BUILDTIME);
		}
		return result;
	}

	/**
	 * Returns the estimated building time that will be spent in the given
	 * workstation. If not overrided, this will always return the default
	 * building time.
	 */
	protected int getBuildTimeFor(Class<? extends Workstation> ws) {
		return DEFAULT_BUILDTIME;
	}

	/**
	 * @return all CarParts that are supported by this specification.
	 */
	public abstract Set<Part> getSupportedCarparts();

	/**
	 * @return all carparts of the given type that are supported by this
	 *         specification.
	 */
	public Set<Part> getSupportedCarparts(Class<? extends Part> type) {
		Set<Part> result = new HashSet<>();
		for (Part part : getSupportedCarparts()) {
			if (part.getClass().equals(type)) {
				result.add(part);
			}
		}
		return result;
	}

	/**
	 * @return all types of carparts that are supported by this specification
	 */
	public Set<Class<? extends Part>> getSupportedTypes() {
		Set<Class<? extends Part>> result = new HashSet<>();
		for (Part part : getSupportedCarparts()) {
			result.add(part.getClass());
		}
		return result;
	}

	/**
	 * @return All parts of the given type that (1) are supported by this
	 *         specification and (2) would not render this specification
	 *         invalid.
	 */
	public Set<Part> getViableOptions(Class<? extends Part> type) {
		Set<Part> result = getSupportedCarparts(type);
		Iterator<Part> it = result.iterator();
		while (it.hasNext()) {
			Part next = it.next();
			if (!isViableOption(next)) {
				it.remove();
			}
		}
		return result;
	}

	/**
	 * checks whether adding the given part would render this orderbuilder
	 * invalid.
	 * 
	 * @return true if the given part can be added to this orderbuilder without
	 *         making it invalid.
	 */
	public boolean isViableOption(Part part) {
		boolean result;
		Part current = parts.get(part.getClass());
		parts.add(part);
		result = this.isPartiallyValid();
		parts.remove(part);
		parts.add(current);
		return result;
	}

	/**
	 * Adds the given part to this specification.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given part is null or not viable.
	 */
	public void add(Part part) {
		if (part == null || !getViableOptions(part.getClass()).contains(part)) {
			System.out.println(getViableOptions(part.getClass()));
			throw new IllegalArgumentException("invalid carpart");
		}
		parts.add(part);
	}

	/**
	 * @return a copy of the carpartsSet containing the parts of this
	 *         specification.
	 */
	public PartsSet getParts() {
		return parts.clone();
	}

	/**
	 * The set of assembly tasks that the order in construction has.
	 */
	public Set<Task> getTasks() {
		Set<Task> tasks = new HashSet<>(getModelSpecificTasks());
		for (Part part : getParts()) {
			tasks.add(new InstallPart(part));
		}
		return tasks;
	}

	/**
	 * returns the tasks that are specific to this type of orderbuilder.
	 */
	protected abstract Set<Task> getModelSpecificTasks();

	/**
	 * Checks whether all restrictions pass for the given set but does not take
	 * parts into account that have not (yet) been selected.
	 */
	private boolean isPartiallyValid() {
		return restriction.checkPartialValidity(parts);
	}

	/**
	 * Adds the given restriction to the chain of restrictions of this model.
	 */
	protected void addRestriction(Restriction restriction) {
		this.restriction.setSuccessor(restriction);
	}

	/**
	 * @return true if the given set is a valid set of carParts for this model
	 *         and all necessary fields have been set.
	 */
	public final boolean isValid() {
		return restriction.checkValidity(parts) && client != null;
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

	/**
	 * @param type
	 * @return true if this orders carparts set already contains an item of the
	 *         passed type
	 */
	public boolean containsPart(Class<? extends Part> type) {
		return this.parts.contains(type);
	}

	@Override
	public abstract String toString();
}
