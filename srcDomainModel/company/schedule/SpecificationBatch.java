package company.schedule;

import vehicle.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {

	private Order batchSample;

	public SpecificationBatch(Order batchSample) {
		if (batchSample == null) {
			throw new IllegalArgumentException("invalid batch sample");
		}
		this.batchSample = batchSample;
	}

//	/**
//	 * @return null when there are no orders with the specification of this
//	 *         specification batch algorithm in the given list of orders.
//	 */
//	@Override
//	public SortedMap<TimeStamp, Order> schedule(TimeStamp currentTime,
//			List<Order> pending, List<Order> inAssembly) {
//		if (currentTime == null || pending == null || inAssembly == null) {
//			throw new IllegalArgumentException();
//		}
//		time = currentTime;
//		this.orders = new LinkedList<>(pending);
//		this.inAssembly = new LinkedList<>(inAssembly);
//
//		SortedMap<TimeStamp, Order> result = new TreeMap<>();
//		LinkedList<Order> matching = getMatchingOrders(pending);
//		if (matching.isEmpty()) {
//			return null;
//		}
//		int nbFirstOrders = 0, nbLastOrders = 0;
//
//		while (!matching.isEmpty()) {
//			if (inAssembly.isEmpty() && !nextIsToday(matching.peek())) {
//				Order last = getLastOrder(nbLastOrders++);
//				if (last != null) {
//					time = time.increaseTime(last
//							.getBuildingTimePerWorkstation());
//					result.put(time, last);
//				}
//				time = time.getNextDay();
//				Order first = getFirstOrder(nbFirstOrders++);
//				if (first != null) {
//					addToAssemblyList(first);
//				}
//			}
//			Order next = null;
//			if (nextIsToday(matching.peek())) {
//				next = matching.poll();
//			}
//			Order completed = addToAssemblyList(next);
//			time = time.increaseTime(getEstimatedCycleTime());
//			if (completed != null) {
//				result.put(time, completed);
//			}
//		}
//		// when matching sets are scheduled, let FIFO do the rest
//		result.putAll(new FIFO().schedule(time, pending, inAssembly));
//		return result;
//	}
//
//	/**
//	 * returns orders
//	 * 
//	 * @return list of orders with a carpartsSet that matches the specification
//	 *         of this specificationBatch algorithm, in the order they were
//	 *         placed.
//	 */
//	private LinkedList<Order> getMatchingOrders(List<Order> orders) {
//		LinkedList<Order> result = new LinkedList<>();
//		for (Order order : orders) {
//			if (order.getTasks().equals(spec)) {
//				result.add(order);
//				orders.remove(order);
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * @return all carpartsSet that appear three or more times in the given list
//	 *         of orders.
//	 */
//	public static Set<CarpartsSet> getAcceptableSets(List<Order> orders) {
//		Map<CarpartsSet, Integer> map = new HashMap<>();
//		for (Order order : orders) {
//			CarpartsSet key = order.getTasks();
//			if (map.containsKey(key)) {
//				map.put(key, map.get(key) + 1);
//			} else {
//				map.put(key, 1);
//			}
//		}
//		Set<CarpartsSet> result = map.keySet();
//		for (CarpartsSet set : result) {
//			if (map.get(set) < 3) {
//				result.remove(set);
//			}
//		}
//		return result;
//	}
}
