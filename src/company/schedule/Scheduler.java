package company.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;

import company.assemblylines.Assemblyline;
import company.workstations.Workstation;
import util.Timestamp;
import vehicle.order.Order;

/**
 * Represents the scheduler of the company. Contains a list of pending and
 * finished orders, a set of assemblylines and the algorithms for scheduling the
 * pending orders.
 */
public class Scheduler implements Observer {

	private LinkedList<Order> finished;
	private LinkedList<Order> pending;
	private Set<Assemblyline> assemblylines;
	private SchedulingAlgorithm algorithm;
	private static final SchedulingAlgorithm DEFAULT_ALGORITHM = new FIFO();

	/**
	 * creates new schedule with the given set of assemblylines and empty lists
	 * of pending and finished orders. The schedule uses the FIFO scheduling
	 * algorithm.
	 */
	public Scheduler(Set<Assemblyline> assemblylines) {
		this(assemblylines, Collections.<Order> emptyList(), Collections
				.<Order> emptyList());
	}

	/**
	 * Creates a new schedule with the given initial details. The scheduler will
	 * initially use the FIFO algorithm.
	 * 
	 * @param assemblylines
	 *            The assemblylines this scheduler will control.
	 * @param pending
	 *            The initial list of pending orders. The order of this list
	 *            will be kept.
	 * @param finished
	 *            The initial list of finished orders.
	 */
	public Scheduler(Set<Assemblyline> assemblylines, List<Order> pending,
			List<Order> finished) {
		if (assemblylines == null || assemblylines.isEmpty()) {
			throw new IllegalArgumentException("invalid set of assemblylines");
		}
		this.pending = new LinkedList<>(pending);
		this.finished = new LinkedList<>(finished);
		this.algorithm = DEFAULT_ALGORITHM;

		this.assemblylines = new HashSet<>();
		for (Assemblyline ass : assemblylines) {
			this.assemblylines.add(ass);
			ass.addObserver(this);
		}
	}

	/**
	 * notifies this scheduler that one of his assemblylines has changed. This
	 * method handles the changes.
	 * 
	 * @param obs
	 *            the assemblyline that notified this schedule. this parameter
	 *            will be ignored though.
	 * @param obj
	 *            this parameter will be ignored.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		if (this.shouldStartNewDay()) {
			this.startNewDay();
		}
		for (Assemblyline al : assemblylines) {
			Order next = algorithm.getNextFor(al, pending);
			if (this.getETA(next).getDay() == al.getCurrentTime().getDay()) {
				Order completed = al.advance(next);
				if (completed != null) {
					finished.add(completed);
				}
			}
		}
	}

	/**
	 * @return true if all assemblylines for which this schedule schedules
	 *         orders are empty.
	 */
	private boolean assemblyLinesAreEmpty() {
		for (Assemblyline line : assemblylines) {
			if (!line.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checks whether a new day should be started.
	 * 
	 * @return true if all assemblylines are empty, there are no more orders
	 *         scheduled for this day, and there is at least one order scheduled
	 *         for the next day.
	 */
	private boolean shouldStartNewDay() {
		if (!this.assemblyLinesAreEmpty()) {
			return false;
		}
		Map<Assemblyline, List<Order>> schedule = algorithm.schedule(pending);
		List<Order> nextOrders = new ArrayList<>();
		for (List<Order> list : schedule.values()) {
			if (!list.isEmpty()) {
				nextOrders.add(list.get(0));
			}
		}
		boolean result = false;
		for (Order next : nextOrders) {
			int etaDay = algorithm.getETA(next, pending).getDay();
			if (etaDay == this.getDay()) {
				return false;
			}
			if (etaDay == this.getDay() + 1) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Makes all its assemblylines set their current time to the beginning of
	 * the next day.
	 * 
	 * @throws IllegalStateException
	 *             if one or more assemblylines are not empty.
	 */
	private void startNewDay() {
		if (!assemblyLinesAreEmpty()) {
			throw new IllegalStateException("assemblylines must be empty");
		}
		for (Assemblyline ass : assemblylines) {
			ass.startNextDay();
		}
	}

	/**
	 * adds given order to the list of pending orders.
	 */
	public void placeOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("invalid order");
		}
		this.pending.addLast(order);
	}

	/**
	 * @return All pending orders, in the order they were placed.
	 */
	public List<Order> getPendingOrders() {
		LinkedList<Order> result = new LinkedList<>(pending);
		for (Assemblyline ass : assemblylines) {
			for (Workstation ws : ass.getWorkstations()) {
				if (ws.getOrder() != null) {
					result.addFirst(ws.getOrder());
				}
			}
		}
		return result;
	}

	/**
	 * @return All finished orders, in the order they were completed.
	 */
	public List<Order> getFinishedOrders() {
		return new LinkedList<>(finished);
	}

	/**
	 * @return the currently used scheduling algorithm.
	 */
	public SchedulingAlgorithm getCurrentAlgorithm() {
		return algorithm;
	}

	/**
	 * sets the scheduling algorithm to the given algorithm.
	 */
	public void setAlgorithm(SchedulingAlgorithm algorithm) {
		if (algorithm == null) {
			throw new IllegalArgumentException(
					"scheduling algorithm can't be null");
		}
		this.algorithm = algorithm;
		this.algorithm.setAssemblylines(this.assemblylines);
	}

	/**
	 * @return estimated completion time of the given order, or null if the
	 *         given order is not present in the list of pending orders of this
	 *         schedule.
	 */
	public Timestamp getETA(Order order) {
		return algorithm.getETA(order, this.pending);
	}

	/**
	 * @return true if there are no pending orders.
	 */
	public boolean isOutOfOrders() {
		return pending.isEmpty();
	}

	/**
	 * @return the current day
	 */
	public int getDay() {
		for (Assemblyline al : assemblylines) {
			return al.getCurrentTime().getDay();
		}
		// should be unreachable
		throw new IllegalStateException(
				"A scheduler should have at least one assemblyline");
	}

	/**
	 * @return the assembly lines
	 */
	public Set<Assemblyline> getAssemblyLines() {
		return new HashSet<>(assemblylines);
	}
}
