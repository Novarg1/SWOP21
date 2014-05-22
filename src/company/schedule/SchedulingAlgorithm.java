package company.schedule;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vehicle.assemblytasks.Task;
import vehicle.order.Order;

import company.assemblylines.Assemblyline;
import company.workstations.Workstation;

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

	/*
	 * eigenlijk is deze methode wat redundant, maar is efficiënt indien het
	 * systeem groot zou worden.
	 */
	/**
	 * Returns the next scheduled order for the given assemblyline.
	 * 
	 * @param ass
	 *            the assemblyline for which the next order will be returned.
	 * @param pending
	 *            the list of pending orders out of which the next order will be
	 *            selected.
	 * @return the next scheduled order for the given assemblyline or null if no
	 *         such order.
	 */
	public Order getNextFor(Assemblyline ass, List<Order> pending) {
		this.resetSchedule();
		this.pending = new LinkedList<>(pending);
		while (scheduleNext() && schedule.get(ass).isEmpty()) {
			;
		}
		List<Order> list = schedule.get(ass);
		return list.isEmpty() ? null : list.get(0);
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
	 * Performs all pending tasks of the given workstation and makes sure the
	 * total time taken equals the expected time.
	 */
	private void performTasks(Workstation ws) {
		Task[] tasks = (Task[]) ws.getPendingTasks().toArray();
		for (int i = 0; i < tasks.length - 1; i++) {
			tasks[i].perform(ws, 0);
		}
		if (tasks.length > 0) {
			tasks[tasks.length - 1].perform(ws, ws.getOrder()
					.getBuildingTimeFor(ws.getClass()));
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
	 * Checks whether the given order can be assembled on an assemblyline for
	 * which this algorithm is scheduling.
	 * 
	 * @return true if one or more of this algorithm's assemblylines supports
	 *         the given order.
	 */
	protected boolean canBeAssembled(Order order) {
		for (Assemblyline ass : schedule.keySet()) {
			if (ass.supports(order)) {
				return true;
			}
		}
		return false;
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
