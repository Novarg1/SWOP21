package company;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import util.TimeStamp;
import car.Order;
import car.parts.Carpart;

public abstract class SchedulingAlgorithm {

	protected static final int NB_WORKPOSTS = 3;

	public abstract SortedMap<TimeStamp, Order> schedule(TimeStamp currentTime,
			List<Order> orders);

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
	protected static Order getFirstOrder(int n, List<Order> orders) {
		int ordersFound = 0;
		for (Order order : orders) {
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
	protected static Order getLastOrder(int n, List<Order> orders) {
		int ordersFound = 0;
		for (Order order : orders) {
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
	 * @return A list containing all pending orders with a deadline, sorted by
	 *         their deadlines.
	 */
	protected static List<Order> getDeadlines(List<Order> orders) {
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

	/**
	 * @return a set containing the id's of all workstations in which parts
	 *         should be installed for the given order.
	 */
	protected static Set<Integer> getNeededWorkstations(Order order) {
		Set<Integer> result = new HashSet<>();
		for (Carpart part : order.getParts()) {
			result.add(part.getWorkStationID());
		}
		return result;
	}
}
