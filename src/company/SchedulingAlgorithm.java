package company;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import util.TimeStamp;
import vehicle.order.Order;

public abstract class SchedulingAlgorithm {

	protected List<Order> orders;
	protected List<Order> inAssembly;
	protected TimeStamp time;

	/**
	 * schedules the given list of orders. This method will not alter its
	 * parameters.
	 * 
	 * @param currentTime
	 *            The current time.
	 * @param pending
	 *            The pending orders that need to be scheduled.
	 * @param inAssembly
	 *            The orders that are currently being assembled on the
	 *            assemblyLine. The first order in this list should be
	 *            associated with the first workstation.
	 * @return A sorted map, containing all the orders contained by the given
	 *         list of pending orders, sorted in the order they are scheduled.
	 *         The keys in this map are the times that the corresponding orders
	 *         (values) are scheduled to be completed. The result includes the
	 *         orders that are currently in assembly.
	 */
	public abstract SortedMap<TimeStamp, Order> schedule(TimeStamp currentTime,
			List<Order> pending, List<Order> inAssembly);

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
	protected Order getFirstOrder(int n) {
		int ordersFound = 0;
		for (Order order : orders) {
			Set<Integer> ws = order.getNeededWorkstations();
			if (ws.size() < inAssembly.size() && !ws.contains(0)) {
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
	protected Order getLastOrder(int n) {
		int ordersFound = 0;
		for (Order order : orders) {
			Set<Integer> ws = order.getNeededWorkstations();
			if (ws.size() < inAssembly.size()
					&& !ws.contains(inAssembly.size())) {
				if (++ordersFound > n) {
					return order;
				}
			}
		}
		return null;
	}

	/**
	 * @return A list containing all pending orders with a deadline, sorted by
	 *         their deadlines.
	 */
	protected List<Order> getDeadlines() {
		List<Order> result = new LinkedList<>();
		for (Order order : orders) {
			if (order.getDeadline() != null) {
				result.add(order);
			}
		}
		Collections.sort(result, new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getDeadline().compareTo(o2.getDeadline());
			}
		});
		return result;
	}

	protected int getEstimatedCycleTime() {
		int result = 0;
		for (Order order : inAssembly) {
			if (order.getBuildingTimePerWorkstation() > result) {
				result = order.getBuildingTimePerWorkstation();
			}
		}
		return 0;
	}

	protected Order addToAssemblyList(Order next) {
		inAssembly.add(0, next);
		return inAssembly.remove(inAssembly.size() - 1);
	}

	protected boolean nextIsToday(Order next) {
		LinkedList<Order> assembly = new LinkedList<>(inAssembly);
		addToAssemblyList(next);
		TimeStamp nextTime = time.increaseTime(getEstimatedCycleTime());
		while (addToAssemblyList(null) != next) {
			nextTime = nextTime.increaseTime(getEstimatedCycleTime());
		}
		inAssembly = assembly; // undo changes
		return !nextTime.shouldBeFinished();
	}
}
