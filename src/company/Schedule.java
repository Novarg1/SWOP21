package company;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import util.TimeStamp;
import car.Order;
import car.parts.Carpart;

//TODO getNeededWorkStations wordt heel vaak aangeroepen --> bijhouden per order?
/**
 * Represents the schedule of the company. Contains a list of pending orders,
 * the current time and algorithms for scheduling the pending orders.
 */
public class Schedule {

	/*
	 * alternative: make every algorithm a subclass of the same supertype and
	 * implement the actual scheduling algorithm in those classes. Not really
	 * necessary, but could be nice when the amount of algorithms gets high.
	 */
	public enum Algorithm {
		FIFO, SPECIFICATION_BATCH;
	}

	private static final int NB_WORKPOSTS = 3;
	private LinkedList<Order> finished;
	private LinkedList<Order> pending;
	private LinkedList<Order> schedule;
	private Order[] inAssembly;
	private TimeStamp currentTime;
	private Algorithm algorithm;

	/**
	 * creates new schedule with an empty list of carOrders and a current time
	 * at the beginning of the first day. The schedule uses the FIFO scheduling
	 * algorithm.
	 */
	public Schedule() {
		this.pending = new LinkedList<>();
		this.schedule = new LinkedList<>();
		this.finished = new LinkedList<>();
		inAssembly = new Order[NB_WORKPOSTS];
		this.currentTime = TimeStamp.FirstDay();
		this.algorithm = Algorithm.FIFO;
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
	public void startNewDay() {
		if (!assemblyLineIsEmpty()) {
			throw new IllegalStateException(
					"assemblyline must be empty before new day can start");
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
	 * increases the current time with given time.
	 * 
	 * @param time
	 *            increase in time (minutes)
	 */
	public void increaseDayTime(int time) {
		currentTime = currentTime.increaseTime(time);
	}

	/**
	 * adds given order to the list of pending orders and updates the schedule.
	 * Estimated completion time will be set in order.
	 */
	public void placeOrder(Order order) {
		this.pending.addLast(order);
		updateSchedule();
	}

	/**
	 * Returns the next order that is scheduled to start assembly and removes it
	 * from the pending orders.
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
		updateSchedule();
		if (assemblyLineIsEmpty() && !nextIsToday()) {
			currentTime = prev;
			updateSchedule();
			throw new IllegalStateException("A new day must start");
		}
		advanceAssemblyLine(schedule.peek());
		return nextIsToday() ? schedule.poll() : null;
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
	 * @return All upcoming orders, in the order they are scheduled.
	 */
	public List<Order> getUpcomingOrders() {
		return new LinkedList<>(schedule);
	}

	/**
	 * @return All finished orders, in the order they were completed.
	 */
	public List<Order> getFinishedOrders() {
		return new LinkedList<>(finished);
	}

	/**
	 * @return a set containing the id's of all workstations in which parts
	 *         should be installed for the given order.
	 */
	private static Set<Integer> getNeededWorkstations(Order order) {
		Set<Integer> result = new HashSet<>();
		for (Carpart part : order.getParts()) {
			result.add(part.getWorkStationID());
		}
		return result;
	}

	/**
	 * @return the currently used scheduling algorithm.
	 */
	public Algorithm getCurrentAlgorithm() {
		return algorithm;
	}

	/**
	 * sets the scheduling algorithm to the given algorithm.
	 */
	public void setAlgorithm(Algorithm algorithm) {
		if (algorithm == null) {
			throw new IllegalArgumentException(
					"scheduling algorithm can't be null");
		}
		this.algorithm = algorithm;
		updateSchedule();
	}

	/**
	 * updates the schedule, using the currently selected scheduling algorithm.
	 */
	private void updateSchedule() {
		switch (algorithm) {
		case FIFO:
			scheduleFIFO();
			break;
		case SPECIFICATION_BATCH:
			scheduleSpecificationBatch();
			break;
		}
	}

	/**
	 * updates the schedule using the FIFO algorithm
	 */
	private void scheduleFIFO() {
		schedule.clear();
		TimeStamp time = currentTime;
		for (Order order : pending) {

			// TODO complete
		}
	}

	/**
	 * updates the schedule using the specification batch algorithm
	 */
	private void scheduleSpecificationBatch() {
		schedule.clear();
		// TODO
	}

	// TODO getFirstOrders en getLastorders dynamisch maken (nu: fixed aantal
	// workstations)
	/**
	 * returns the first order for a new day, if any. This is calculated in such
	 * way that the amount of idle workstations at the start of the day is
	 * minimal. The order will therefore be an order for which there is no work
	 * in the first workstation. This method applies FIFO in finding fitting
	 * orders and returns the n-th one.
	 * 
	 * @param n
	 *            if n is 0, returns the first order that meets the requirement.
	 *            If n is 1, the second one is returned, and so on
	 * 
	 * @return the n-th first order for a new day or null if no such order
	 *         exists.
	 */
	private Order getFirstOrders(int n) {
		int ordersFound = 0;
		for (Order order : pending) {
			Set<Integer> ws = getNeededWorkstations(order);
			if (ws.size() < NB_WORKPOSTS && !ws.contains(0)) {
				if (++ordersFound > n) {
					return order;
				}
			}
		}
		return null;
	}

	/**
	 * returns the last order for an ending day, if any. This is calculated in
	 * such way that the amount of idle workstations at the ending of the day is
	 * minimal. The order will therefore be an order for which there is no work
	 * in the last workstation. This method applies FIFO in finding fitting
	 * orders and returns the n-th one.
	 * 
	 * @param n
	 *            if n is 0, returns the first order that meets the requirement.
	 *            If n is 1, the second one is returned, and so on
	 * 
	 * @return the (n+1)th first order for a new day or null if no such order
	 *         exists.
	 */
	private Order getLastOrders(int n) {
		int ordersFound = 0;
		for (Order order : pending) {
			Set<Integer> ws = getNeededWorkstations(order);
			if (ws.size() < NB_WORKPOSTS && !ws.contains(NB_WORKPOSTS - 1)) {
				if (++ordersFound > n) {
					return order;
				}
			}
		}
		return null;
	}

	/**
	 * @return Total expected time the car associated with the next scheduled
	 *         order will spend on the assemblyLine.
	 */
	public int getWorktimeForNext() {
		return -1; // TODO
	}

	/**
	 * @return true if the next scheduled order is scheduled for today.
	 */
	public boolean nextIsToday() {
		return schedule.peek().getCompletionTime().getDay() == currentTime
				.getDay();
	}
}
