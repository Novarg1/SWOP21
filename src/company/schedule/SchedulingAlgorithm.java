package company.schedule;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vehicle.order.Order;

import company.assemblylines.Assemblyline;

/**
 * Represents a scheduling algorithm. Before using scheduling methods, users
 * must make sure to set the assemblylines before calling scheduling methods.
 */
public abstract class SchedulingAlgorithm {

	private List<Order> pending = Collections.emptyList();
	private Map<Assemblyline, List<Order>> schedule = Collections.emptyMap();

	/**
	 * Clones the given assemblylines all individually and keeps the clones for
	 * scheduling.
	 */
	public void setAssemblylines(Set<Assemblyline> argLines) {
		if (argLines == null) {
			throw new IllegalArgumentException("invalid set of assemblylines");
		}
		this.schedule = new HashMap<>();
		for (Assemblyline argLine : argLines) {
			this.schedule.put(argLine.clone(), new LinkedList<Order>());
		}
	}

	/**
	 * schedules all given orders over the assemblylines that are currently set
	 * for this algorithm. If an order in the pending list cannot be scheduled,
	 * this order will simply be ignored.
	 * 
	 * @param pending
	 *            the orders that will be scheduled
	 * @return A map containing the assemblylines for which orders were
	 *         scheduled as keys, and their respective lists of scheduled orders
	 *         as values.
	 */
	public Map<Assemblyline, List<Order>> schedule(List<Order> pending) {
		this.resetSchedule();
		this.pending = new LinkedList<>(pending);
		while (scheduleNext()) {
			;
		}
		return new HashMap<>(schedule);
	}

	/**
	 * Keeps the assemblylines, but sets all associated lists of orders to new,
	 * empty lists.
	 */
	private void resetSchedule() {
		Set<Assemblyline> keys = schedule.keySet();
		schedule = new HashMap<>();
		for (Assemblyline key : keys) {
			schedule.put(key, new LinkedList<Order>());
		}
	}

	/**
	 * @return A set containing all assemblylines for which this algorithm is
	 *         currently scheduling.
	 */
	protected Set<Assemblyline> getAssemblylines() {
		return new HashSet<>(schedule.keySet());
	}

	/**
	 * @return A copy of the list containing all orders that have not (yet) been
	 *         scheduled.
	 */
	protected List<Order> getPending() {
		return new LinkedList<>(pending);
	}

	/**
	 * Adds the given order to the schedule associated with the given
	 * assemblyline and removes it from the pending orders.
	 * 
	 * @return true if the schedule was changed by this method. Returns false if
	 *         the given order was not contained in the list of pending orders,
	 *         or if the given assemblyline is not a key in the schedule.
	 */
	protected boolean addToSchedule(Order order, Assemblyline assemblyline) {
		if (!pending.contains(order)
				|| !schedule.keySet().contains(assemblyline)) {
			return false;
		}
		pending.remove(order);
		schedule.get(assemblyline).add(order);
		return true;
	}

	/**
	 * performs one step in the scheduling process. The implementing method
	 * should end with, and return the call to the addToSchedule() method of
	 * this class.
	 * 
	 * @return true if the schedule was changed by calling this method.
	 */
	protected abstract boolean scheduleNext();
}
