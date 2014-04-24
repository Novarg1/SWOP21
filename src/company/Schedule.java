package company;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.TimeStamp;
import car.Order;

/**
 * Represents the schedule of the company. Contains a list of pending orders,
 * the current time and algorithms for scheduling the pending orders.
 */
public class Schedule {

	private static final int NB_WORKPOSTS = 3; // TODO nieuw plan: houd
												// reference
												// naar assemblyline bij
	private LinkedList<Order> finished;
	private LinkedList<Order> pending;
	private Order[] inAssembly;
	private TimeStamp currentTime;
	private SchedulingAlgorithm algorithm;

	/**
	 * creates new schedule with an empty list of carOrders and a current time
	 * at the beginning of the first day. The schedule uses the FIFO scheduling
	 * algorithm.
	 */
	public Schedule() {
		this.pending = new LinkedList<>();
		this.finished = new LinkedList<>();
		inAssembly = new Order[NB_WORKPOSTS];
		this.currentTime = TimeStamp.FirstDay();
		this.algorithm = new FIFO();
	}

	/**
	 * @return the current time
	 */
	public TimeStamp getCurrentTime() {
		return currentTime;
	}

	/**
	 * Sets current time to beginning of next day.
	 */
	private void startNewDay() {
		if (!assemblyLineIsEmpty()) {
			throw new IllegalStateException("assemblyline must be empty");
		}
		currentTime = currentTime.getNextDay();
	}

	private boolean assemblyLineIsEmpty() {
		for (Order order : inAssembly) {
			if (order != null) {
				return false;
			}
		}
		return true;
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

	/**
	 * Returns the next order that is scheduled to start assembly and removes it
	 * from the pending orders. This method should therefore only be called when
	 * the assemblyLine advances.
	 * 
	 * @param time
	 *            The time the previous cycle on the assemblyLine took.
	 * 
	 * @return the next order to be assembled, or null if there are no more
	 *         orders planned for today or if there are no more pending orders.
	 */
	public Order getNextOrder(int time) {
		TimeStamp prev = currentTime;
		currentTime = currentTime.increaseTime(time);
		if (nextIsTomorrow()) {
			if (assemblyLineIsEmpty()) {
				currentTime = prev;
				startNewDay();
			} else {
				advanceAssemblyLine(null);
				return null; // wait until assemblyLine is empty
			}
		}
		SortedMap<TimeStamp, Order> schedule = getSchedule();
		Order next = schedule.get(schedule.firstKey());
		pending.remove(next);
		advanceAssemblyLine(next);
		return next;
	}

	/**
	 * advances the representation of the assemblyLine in schedule.
	 */
	private void advanceAssemblyLine(Order order) {
		if (inAssembly[NB_WORKPOSTS - 1] != null) {
			finished.add(inAssembly[NB_WORKPOSTS - 1]);
		}
		for (int i = NB_WORKPOSTS - 1; i > 0; i--) {
			inAssembly[i] = inAssembly[i - 1];
		}
		inAssembly[0] = order;
	}

	/**
	 * @return All pending orders, in the order they were placed.
	 */
	public List<Order> getPendingOrders() {
		return new LinkedList<>(pending);
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
	 * @return An ordered map containing all pending orders as keys and their
	 *         estimated completion times as values, in the order they are
	 *         scheduled.
	 */
	private SortedMap<TimeStamp, Order> getSchedule() {
		SortedMap<TimeStamp, Order> schedule = algorithm.schedule(currentTime,
				pending, Arrays.asList(inAssembly));
		if (schedule == null) {
			setAlgorithm(new FIFO());
			schedule = algorithm.schedule(currentTime, pending,
					Arrays.asList(inAssembly));
		}
		return schedule;
	}

	/**
	 * @return estimated completion time of the given order, or null if the
	 *         given order is not present in the list of pending orders of this
	 *         schedule.
	 */
	public TimeStamp getETA(Order order) {
		for (Map.Entry<TimeStamp, Order> entry : getSchedule().entrySet()) {
			if (order == entry.getValue()) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * @return true if the next scheduled order is scheduled for tomorrow. If
	 *         there are no orders left, returns false.
	 */
	public boolean nextIsTomorrow() {
		if (pending.isEmpty()) {
			return false;
		}
		return getSchedule().firstKey().getDay() == currentTime.getDay() + 1;
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
	public int getNumberOfOperationalDays() {
		return currentTime.getNumberOfOperationalDays();
	}
}
