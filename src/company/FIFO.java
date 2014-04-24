package company;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import util.TimeStamp;
import car.Order;

public class FIFO extends SchedulingAlgorithm {

	@Override
	public SortedMap<TimeStamp, Order> schedule(TimeStamp currentTime,
			List<Order> pending, List<Order> inAssembly) {

		if (currentTime == null || pending == null || inAssembly == null) {
			throw new IllegalArgumentException();
		}
		time = currentTime;
		this.orders = new LinkedList<>(pending);
		this.inAssembly = new LinkedList<>(inAssembly);
		
		SortedMap<TimeStamp, Order> result = new TreeMap<>();
		int nbFirstOrders = 0, nbLastOrders = 0;

		while (!orders.isEmpty()) {
			int i = 0;
			Order order = orders.get(0);
			while(order.getNeededWorkstations().size() < inAssembly.size()) {
				order = orders.get(++i);
			}
			if (inAssembly.isEmpty() && !nextIsToday(order)) {
				Order last = getLastOrder(nbLastOrders++);
				if (last != null) {
					time = time.increaseTime(last
							.getBuildingTimePerWorkstation());
					result.put(time, last);
				}
				time = time.getNextDay();
				Order first = getFirstOrder(nbFirstOrders++);
				if (first != null) {
					addToAssemblyList(first);
				}
			}
			Order next = null;
			if (nextIsToday(order)) {
				next = order;
				orders.remove(order);
			}
			Order completed = addToAssemblyList(next);
			time = time.increaseTime(getEstimatedCycleTime());
			if (completed != null) {
				result.put(time, completed);
			}
		}
		return result;
	}
}
