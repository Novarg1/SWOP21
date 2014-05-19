package company.schedule;

import java.util.Collections;
import java.util.HashSet;
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
 * Represents the schedule of the company. Contains a list of pending and
 * finished orders, a set of assemlylines and the algorithms for scheduling the
 * pending orders.
 */
public class Scheduler implements Observer {

	private LinkedList<Order> finished;
	private LinkedList<Order> pending;
	private Set<Assemblyline> assemblyLines;
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

		this.assemblyLines = new HashSet<>();
		for (Assemblyline ass : assemblylines) {
			this.assemblyLines.add(ass);
			ass.addObserver(this);
		}
	}

	/**
	 * TODO comment
	 */
	@Override
	public void update(Observable obs, Object obj) {
		
	}

	/**
	 * @return true if all assemblylines for which this schedule schedules
	 *         orders are empty.
	 */
	private boolean assemblyLinesAreEmpty() {
		for (Assemblyline line : assemblyLines) {
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
		// if (!assemblyLinesAreEmpty()) {
		// throw new IllegalStateException("assemblylines must be empty");
		// //TODO
		// }
		// for (Assemblyline ass : assemblyLines.keySet()) {
		// assemblyLines.put(ass, assemblyLines.get(ass).getNextDay());
		// }
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
		for (Assemblyline ass : assemblyLines) {
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
		// for (Timestamp time : assemblyLines) { TODO
		// return time.getDay();
		// }
		// should be unreachable
		throw new IllegalStateException(
				"A scheduler should have at least one assemblyline");
	}

	/**
	 * @param n
	 * @return the assembly line that sits at position n in the list
	 */
	public Assemblyline getAssemblyLine(int n) {
		return (Assemblyline) (this.assemblyLines.toArray())[n];
	}
}
