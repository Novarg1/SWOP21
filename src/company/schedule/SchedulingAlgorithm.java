package company.schedule;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

/**
 * Represents a scheduling algorithm. Before using scheduling methods, users
 * must make sure to set the assemblylines before calling scheduling methods.
 */
public abstract class SchedulingAlgorithm {

	/**
	 * maps the currently used clones of assemblylines to their originals.
	 */
	private Map<Assemblyline, Assemblyline> originalAssemblylines;
	/**
	 * Maps the currently used clones of orders to their originals.
	 */
	private Map<Order, Order> originalOrders;
	/**
	 * A list containing clones of the original pending orders.
	 */
	private List<Order> pending = Collections.emptyList();
	/**
	 * A list containing all pending custom orders, that need be scheduled in
	 * the beginning and end of a day.
	 */
	private List<Order> customs = Collections.emptyList();
	/**
	 * maps clones of the original assemblylines to a sorted list of (cloned)
	 * orders.
	 */
	private Map<Assemblyline, List<Order>> schedule = Collections.emptyMap();
	/**
	 * maps original orders to their estimated completion times.
	 */
	private Map<Order, Timestamp> etas = Collections.emptyMap();

	/**
	 * Clones the given assemblylines all individually and keeps the clones for
	 * scheduling.
	 */
	public void setAssemblylines(Set<Assemblyline> als) {
		if (als == null) {
			throw new IllegalArgumentException("invalid set of assemblylines");
		}
		this.originalAssemblylines = new HashMap<>();
		for (Assemblyline al : als) {
			originalAssemblylines.put(al.clone(), al);
		}
		originalOrders = Collections.emptyMap();
		pending = Collections.emptyList();
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
		this.initialize(pending);
		this.filterCustoms();

		// schedule a first order for the assemblylines that are currently at
		// the beginning of a day.
		for (Assemblyline al : schedule.keySet()) {
			if (al.getCurrentTime().isAtBeginning()) {
				addFirst(al);
			}
		}

		// first schedule the orders that wouldn't meet there deadline otherwise
		this.handleDeadlines();

		// schedule all (remaining) orders
		while (scheduleNext())
			;

		// if there are no other orders left, schedule the remaining custom
		// orders
		this.pending = this.customs;
		while (scheduleNext())
			;

		return this.replaceWithOriginals();
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
		this.schedule(pending);
		return etas.get(order);
	}

	/**
	 * Takes out all custom orders, so they don't get scheduled in the middle of
	 * a day. Orders that would not meet there deadlines are scheduled
	 * immediately.
	 */
	private void handleDeadlines() {
		List<Order> list = new LinkedList<>(customs);
		Collections.sort(list, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getDeadline().compareTo(o2.getDeadline());
			}
		});
		int day = ((Assemblyline) this.schedule.keySet().toArray()[0])
				.getCurrentTime().getDay();
		for (; !list.isEmpty(); day++) {
			// TODO
		}
	}

	/**
	 * removes all orders with deadlines from the pending list and puts them in
	 * the customs list.
	 */
	private void filterCustoms() {
		this.customs = new LinkedList<>();
		Iterator<Order> it = pending.iterator();
		while (it.hasNext()) {
			Order order = it.next();
			if (order.getDeadline() != null) {
				it.remove();
				this.customs.add(order);
			}
		}
	}

	/**
	 * adds a custom order that should be scheduled on the end of a day to the
	 * given assemblyline's schedule. This method should be called whenever the
	 * end of a day must be scheduled.
	 * 
	 * @return The order that was scheduled, or null if no such order
	 */
	private Order addLast(Assemblyline al) {
		Iterator<Order> it = customs.iterator();
		while (it.hasNext()) {
			Order order = it.next();
			if (al.supports(order) && shouldBeScheduledAtEnd(al, order)) {
				schedule.get(al).add(order);
				it.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * adds a custom order that should be scheduled on the beginning of a day to
	 * the given assemblyline's schedule. This method should be called whenever
	 * the beginning of a day must be scheduled.
	 * 
	 * @return The order that was scheduled, or null if no such order
	 */
	private Order addFirst(Assemblyline al) {
		Iterator<Order> it = customs.iterator();
		while (it.hasNext()) {
			Order order = it.next();
			if (al.supports(order) && shouldBeScheduledAtStart(al, order)) {
				schedule.get(al).add(order);
				it.remove();
				return order;
			}
		}
		return null;
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
		this.performAllTasks();
		Assemblyline al = this.getOptimalAssemblyline(next);
		if (!hasTimeFor(al, next)) {
			if (al.isEmpty()) {
				Order last = this.addLast(al);
				if (last != null) {
					etas.put(last, al.getCurrentTime());
				}
				al.startNextDay();
				this.addFirst(al);
				return true;
			} // else
			Order completed = originalOrders.get(al.advance(null));
			if (completed != null) {
				etas.put(completed, al.getCurrentTime());
			}
			return true;
		} // else

		pending.remove(next);
		schedule.get(al).add(next);
		Order completed = originalOrders.get(al.advance(next));
		if (completed != null) {
			etas.put(completed, al.getCurrentTime());
		}
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
	 * creates new clones of the original assemblylines and clones of the given
	 * orders and initializes this algorithm for scheduling.
	 */
	private void initialize(List<Order> pending) {
		Collection<Assemblyline> original = originalAssemblylines.values();
		this.originalAssemblylines = new HashMap<>();
		this.originalOrders = new HashMap<>();
		this.schedule = new HashMap<>();
		this.pending = new LinkedList<Order>();
		this.etas = new HashMap<>();

		for (Assemblyline al : original) {
			Assemblyline clone = al.clone();
			originalAssemblylines.put(clone, al);
			schedule.put(clone, new LinkedList<Order>());
		}
		for (Order order : pending) {
			Order clone = order.clone();
			this.originalOrders.put(clone, order);
			this.pending.add(clone);
		}
	}

	/**
	 * returns a new schedule in which the clones of assemblylines and orders
	 * are replaced with their originals.
	 */
	private Map<Assemblyline, List<Order>> replaceWithOriginals() {
		Map<Assemblyline, List<Order>> newSchedule = new HashMap<>();
		for (Assemblyline clone : schedule.keySet()) {
			Assemblyline original = originalAssemblylines.get(clone);
			List<Order> newList = new LinkedList<>();
			for (Order clonedOrder : schedule.get(clone)) {
				newList.add(originalOrders.get(clonedOrder));
			}
			newSchedule.put(original, newList);
		}
		return newSchedule;
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
		while (!clone.isEmpty()) {
			performTasks(clone);
			clone.advance(null);
		}
		return !clone.getCurrentTime().isInOvertime();
	}

	/**
	 * Checks whether the given order is an order that should be scheduled at
	 * the end of a new day for the given assemblyline.
	 * 
	 * @return true if the given order has no tasks for the last workstation on
	 *         the given assemblyline.
	 */
	private static boolean shouldBeScheduledAtEnd(Assemblyline al, Order order) {
		Workstation[] ws = al.getWorkstations();
		return order.getNeededWorkstations().contains(
				ws[ws.length - 1].getClass());
	}

	/**
	 * Checks whether the given order is an order that should be scheduled at
	 * the beginning of a new day for the given assemblyline.
	 * 
	 * @return true if the given order has no tasks for the first workstation on
	 *         the given assemblyline.
	 */
	private static boolean shouldBeScheduledAtStart(Assemblyline al, Order order) {
		return order.getNeededWorkstations().contains(
				al.getWorkstations()[0].getClass());
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
