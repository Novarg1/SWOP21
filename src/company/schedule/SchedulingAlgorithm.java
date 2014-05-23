package company.schedule;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Timestamp;
import vehicle.assemblytasks.Task;
import vehicle.order.Order;

import company.assemblylines.Assemblyline;
import company.workstations.Workstation;

/*
 * TODO advance assemblyline;
 * start new days;
 * custom orders;
 */

/**
 * Represents a scheduling algorithm. Before using scheduling methods, users
 * must make sure to set the assemblylines before calling scheduling methods.
 */
public abstract class SchedulingAlgorithm {

	private Set<Assemblyline> originalAssemblylines;
	private List<Order> pending = Collections.emptyList();
	private Map<Assemblyline, List<Order>> schedule = Collections.emptyMap();

	/**
	 * Clones the given assemblylines all individually and keeps the clones for
	 * scheduling.
	 */
	public void setAssemblylines(Set<Assemblyline> als) {
		if (als == null) {
			throw new IllegalArgumentException("invalid set of assemblylines");
		}
		this.originalAssemblylines = new HashSet<>(als);
		this.resetSchedule();
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
		do {
			this.performAllTasks();
		} while (scheduleNext());
		return new HashMap<>(schedule);
	}

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
		List<Order> list = this.schedule(pending).get(ass);
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * calculates the estimated time of completion of the given order
	 * 
	 * @param order
	 * @param pending2
	 * @return
	 */
	public Timestamp getETA(Order order, List<Order> pending) {
		throw new IllegalStateException("not implemented"); // TODO
	}

	/**
	 * performs one step in the scheduling process: an order will be removed
	 * from the pending list, added to the schedule, and an assemblyline will be
	 * advanced.
	 * 
	 * @return true if the schedule was changed by calling this method.
	 */
	private boolean scheduleNext() {
		Order next = this.getNextSupportedOrder();
		if (next == null || !pending.contains(next)) {
			return false;
		}
		pending.remove(next);
		Assemblyline al = this.getOptimalAssemblyline(next);
		schedule.get(al).add(next);
		return true;
	}

	/**
	 * Returns the first order in the sorted pending orders that is supported by
	 * at least one assemblyline, or null if no such order exists.
	 */
	private Order getNextSupportedOrder() {
		for (Order order : this.getSortedPending()) {
			if (canBeAssembled(order)) {
				return order;
			}
		}
		return null;
	}

	/**
	 * sets a new schedule with the clones of the originalAssemblylines as keys
	 * and empty lists as values.
	 */
	private void resetSchedule() {
		schedule = new HashMap<>();
		for (Assemblyline key : originalAssemblylines) {
			schedule.put(key.clone(), new LinkedList<Order>());
		}
	}

	/**
	 * Performs all pending tasks of all the workstations on all assemblylines
	 * for which this algorithm is scheduling and makes sure the total time
	 * taken in each workstation equals the expected time.
	 */
	private void performAllTasks() {
		for (Assemblyline al : schedule.keySet()) {
			performTasks(al);
		}
	}

	/**
	 * Performs all pending tasks of all the workstations on the given
	 * assemblyline and makes sure the total time taken in each workstation
	 * equals the expected time.
	 */
	private static void performTasks(Assemblyline al) {
		for (Workstation ws : al.getWorkstations()) {
			Task[] tasks = (Task[]) ws.getPendingTasks().toArray();
			for (int i = 0; i < tasks.length - 1; i++) {
				tasks[i].perform(ws, 0);
			}
			if (tasks.length > 0) {
				tasks[tasks.length - 1].perform(ws, ws.getOrder()
						.getBuildingTimeFor(ws.getClass()));
			}
		}
	}

	/**
	 * Checks whether the given order can be assembled on an assemblyline for
	 * which this algorithm is scheduling.
	 * 
	 * @return true if one or more of this algorithm's assemblylines supports
	 *         the given order.
	 */
	private boolean canBeAssembled(Order order) {
		return !this.getSupportingAssemblylines(order).isEmpty();
	}

	/**
	 * returns a set containing all assemblylines for which this algorithm is
	 * scheduling that support the given order.
	 */
	private List<Assemblyline> getSupportingAssemblylines(Order order) {
		List<Assemblyline> result = new LinkedList<>(schedule.keySet());
		Iterator<Assemblyline> it = result.iterator();
		while (it.hasNext()) {
			if (!it.next().supports(order)) {
				it.remove();
			}
		}
		return result;
	}

	/**
	 * Determines on which assemblyline the given order should be scheduled if
	 * the given order is the next one.
	 * 
	 * @return the assemblyline on which the given order should be scheduled if
	 *         the given order is the next one, or null if there are no
	 *         assemblylines that support the given order.
	 */
	private Assemblyline getOptimalAssemblyline(Order order) {
		List<Assemblyline> supporting = this.getSupportingAssemblylines(order);
		if (supporting.isEmpty()) {
			return null;
		}
		Assemblyline result = supporting.get(0);
		for (int i = 1; i < supporting.size(); i++) {
			Assemblyline other = supporting.get(i);
			if (other.getCurrentTime().compareTo(result.getCurrentTime()) < 0) {
				result = other;
			}
		}
		return result;
	}

	/**
	 * Checks whether the given order can be completed on the given assemblyline
	 * without doing overtime.
	 * 
	 * @return true if the given order can be put on the given assemblyline and
	 *         its expected completion time would not exceed this day's work
	 *         hours.
	 */
	private static boolean hasTimeFor(Assemblyline al, Order order) {
		Assemblyline clone = al.clone();
		clone.advance(order);
		while(!clone.isEmpty()) {
			performTasks(clone);
			clone.advance(null);
		}
		return !clone.getCurrentTime().isInOvertime();
	}

	/**
	 * @return A copy of the list containing all orders that have not (yet) been
	 *         scheduled.
	 */
	protected List<Order> getPending() {
		return new LinkedList<>(pending);
	}

	/**
	 * determines the order in which the pending orders must be processed. The
	 * implementing method can obtain a copy of the list of pending orders by
	 * calling super.getPending().
	 * 
	 * @return a list containing the orders in the order they should be
	 *         processed.
	 */
	protected abstract List<Order> getSortedPending();
}
