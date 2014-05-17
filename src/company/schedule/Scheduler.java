package company.schedule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import company.assemblylines.Assemblyline;
import company.workstations.Workstation;
import util.Timestamp;
import vehicle.order.Order;

/**
 * Represents the schedule of the company. Contains a list of pending orders,
 * the current time and algorithms for scheduling the pending orders.
 */
public class Scheduler implements Observer {

	private LinkedList<Order> finished;
	private LinkedList<Order> pending;
	private Map<Assemblyline, Timestamp> assemblyLines;
	private SchedulingAlgorithm algorithm;

	/**
	 * creates new schedule with the given set of assemblylines and empty lists
	 * of pending and finished orders. The schedule starts at the beginning of
	 * day 0. The schedule uses the FIFO scheduling algorithm.
	 */
	public Scheduler(Set<Assemblyline> assemblylines) {
		this(assemblylines, Collections.<Order> emptyList(), Collections
				.<Order> emptyList(), 0);
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
	 * @param day
	 *            The day at which this schedule will start.
	 */
	public Scheduler(Set<Assemblyline> assemblylines, List<Order> pending,
			List<Order> finished, int day) {
		if (assemblylines == null || assemblylines.isEmpty()) {
			throw new IllegalArgumentException("invalid set of assemblylines");
		}
		this.pending = new LinkedList<>(pending);
		this.finished = new LinkedList<>(finished);
		this.algorithm = new FIFO();

		Timestamp startTime = Timestamp.beginningOfDay(day);
		this.assemblyLines = new HashMap<>();
		for (Assemblyline ass : assemblylines) {
			this.assemblyLines.put(ass, startTime);
		}
	}

	/**
	 * This method is called whenever an assemblyline is ready to advance. Does
	 * nothing if this schedule does not contain the given assemblyline.
	 * 
	 * @param obs
	 *            The assemblyline that is ready to advance.
	 * @param obj
	 *            The time it took the given assemblyline to get ready to
	 *            advance. If this parameter is null, not an integer, or less
	 *            than or equal to zero, the schedule will assume the
	 *            assemblyline took zero minutes.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		throw new IllegalStateException("not implemented"); // TODO
	}

	/**
	 * @return true if all assemblylines for which this schedule schedules
	 *         orders are empty.
	 */
	private boolean assemblyLinesAreEmpty() {
		for (Assemblyline line : assemblyLines.keySet()) {
			if (!line.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sets the time to the beginning of the next day.
	 */
	private void startNewDay() {
		if (!assemblyLinesAreEmpty()) {
			throw new IllegalStateException("assemblylines must be empty");
		}
		for (Assemblyline ass : assemblyLines.keySet()) {
			assemblyLines.put(ass, assemblyLines.get(ass).getNextDay());
		}
	}

	/**
	 * adds given order to the list of pending orders and updates the schedule.
	 * Estimated completion time will be set in order.
	 */
	public void placeOrder(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("invalid order");
		}
		this.pending.addLast(order);
	}

	// /**
	// * Returns the next order that is scheduled to start assembly and removes
	// it
	// * from the pending orders. This method should therefore only be called
	// when
	// * the assemblyLine advances.
	// *
	// * @param time
	// * The time the previous cycle on the assemblyLine took.
	// *
	// * @return the next order to be assembled, or null if there are no more
	// * orders planned for today or if there are no more pending orders.
	// */
	// public Order getNextOrder(int time) {
	// Timestamp prev = currentTime;
	// currentTime = currentTime.increaseTime(time);
	// if (nextIsTomorrow()) {
	// if (assemblyLinesAreEmpty()) {
	// currentTime = prev;
	// startNewDay();
	// } else {
	// advanceAssemblyLine(null);
	// return null; // wait until assemblyLine is empty
	// }
	// }
	// SortedMap<Timestamp, Order> schedule = getSchedule();
	// Order next = schedule.get(schedule.firstKey());
	// pending.remove(next);
	// advanceAssemblyLine(next);
	// return next;
	// }

	/**
	 * @return All pending orders, in the order they were placed.
	 */
	public List<Order> getPendingOrders() {
		LinkedList<Order> result = new LinkedList<>(pending);
		for (Assemblyline ass : assemblyLines.keySet()) {
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
	}

	/**
	 * @return estimated completion time of the given order, or null if the
	 *         given order is not present in the list of pending orders of this
	 *         schedule.
	 */
	public Timestamp getETA(Order order) {
		throw new IllegalStateException("not implemented");
	}

	/**
	 * @return true if the next scheduled order is scheduled for tomorrow. If
	 *         there are no orders left, returns false.
	 */
	public boolean nextIsTomorrow() {
		throw new IllegalStateException("not implemented");
	}

	/**
	 * @return true if there are no pending orders.
	 */
	public boolean isOutOfOrders() {
		return pending.isEmpty();
	}

	/**
	 * @return the number of days that have passed since the system was
	 *         initiated
	 */
	public int getDay() {
		for (Timestamp time : assemblyLines.values()) {
			return time.getDay();
		}
		throw new IllegalStateException(
				"A scheduler should have at least one assemblyline");
	}
	
	/**
	 * @param n
	 * @return the assembly line that sits at position n in the list
	 */
	
	public Assemblyline getAssmeblyLine(int n)
	{
		return (Assemblyline) (this.assemblyLines.entrySet().toArray())[n];
	}
}
