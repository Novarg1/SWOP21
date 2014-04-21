package company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import util.TimeStamp;
import car.Order;
import car.parts.CarpartsSet;

public class SpecificationBatch extends SchedulingAlgorithm {

	private CarpartsSet spec;

	public SpecificationBatch(CarpartsSet specification) {
		if (spec == null) {
			throw new IllegalArgumentException("invalid specification");
		}
		spec = specification.clone();
	}

	/**
	 * @return null when there are no orders with the specification of this
	 *         specification batch algorithm in the given list of orders.
	 */
	@Override
	public SortedMap<TimeStamp, Order> schedule(TimeStamp currentTime,
			List<Order> orders, List<Order> inAssembly) {
		if(currentTime==null || orders==null || inAssembly==null) {
			throw new IllegalArgumentException();
		}
		nbWorkstations = inAssembly.size();
		SortedMap<TimeStamp, Order> result = new TreeMap<>();
		TimeStamp time = currentTime;
		LinkedList<Order> matching = getMatchingOrders(orders);
		if (matching.isEmpty()) {
			return null;
		}
		while (!matching.isEmpty()) {

			if(inAssembly.isEmpty() && !nextIsToday(matching.peek())) {
				time = time.getNextDay();
			}
			//TODO can linkedList contain multipe nulls?
			Order next = null;
			if(nextIsToday(matching.peek())) {
				next = matching.poll();
			}
			Order completed = addToAssemblyList(matching.poll());
			time = time.increaseTime(getEstimatedCycleTime());
			result.put(time, completed);
		}
		result.putAll(new FIFO().schedule(time, orders, inAssembly));
		return result;
	}

	/**
	 * returns orders
	 * 
	 * @return list of orders with a carpartsSet that matches the specification
	 *         of this specificationBatch algorithm, in the order they were
	 *         placed.
	 */
	private LinkedList<Order> getMatchingOrders(List<Order> orders) {
		LinkedList<Order> result = new LinkedList<>();
		for (Order order : orders) {
			if (order.getParts().equals(spec)) {
				result.add(order);
				orders.remove(order);
			}
		}
		return result;
	}

	/**
	 * @return all carpartsSet that appear three or more times in the given list
	 *         of orders.
	 */
	public static Set<CarpartsSet> getAcceptableSets(List<Order> orders) {
		Map<CarpartsSet, Integer> map = new HashMap<>();
		for (Order order : orders) {
			CarpartsSet key = order.getParts();
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		Set<CarpartsSet> result = map.keySet();
		for (CarpartsSet set : result) {
			if (map.get(set) < 3) {
				result.remove(set);
			}
		}
		return result;
	}
}
