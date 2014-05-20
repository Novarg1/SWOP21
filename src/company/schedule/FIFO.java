package company.schedule;

public class FIFO extends SchedulingAlgorithm {

//	@Override
//	public SortedMap<Timestamp, Order> schedule(Timestamp currentTime,
//			List<Order> pending, List<Order> inAssembly) {
//
//		if (currentTime == null || pending == null || inAssembly == null) {
//			throw new IllegalArgumentException();
//		}
//		time = currentTime;
//		this.orders = new LinkedList<>(pending);
//		this.inAssembly = new LinkedList<>(inAssembly);
//
//		List<Order> deadlines = getDeadlines();
//
//		SortedMap<Timestamp, Order> result = new TreeMap<>();
//		int nbFirstOrders = 0, nbLastOrders = 0;
//
//		while (!orders.isEmpty()) {
//			int i = 0;
//			Order order = deadlines.get(0);
//			if(order.getDeadline().getDay() > time.getDay() + 1) {
//				order = orders.get(0);
//				while(order.getNeededWorkstations().size() < inAssembly.size()) {
//					order = orders.get(++i);
//				}
//			} else {
//				deadlines.remove(0);
//			}
//			if (inAssembly.isEmpty() && !nextIsToday(order)) {
//				Order last = getLastOrder(nbLastOrders++);
//				if (last != null) {
//					time = time.increaseTime(last
//							.getBuildingTimeFor());
//					result.put(time, last);
//				}
//				time = time.getNextDay();
//				Order first = getFirstOrder(nbFirstOrders++);
//				if (first != null) {
//					addToAssemblyList(first);
//				}
//			}
//			Order next = null;
//			if (nextIsToday(order)) {
//				next = order;
//				orders.remove(order);
//			}
//			Order completed = addToAssemblyList(next);
//			time = time.increaseTime(getEstimatedCycleTime());
//			if (completed != null) {
//				result.put(time, completed);
//			}
//		}
//		return result;
//	}
}
