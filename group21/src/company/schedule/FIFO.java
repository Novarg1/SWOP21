package company.schedule;

import java.util.List;

import vehicle.order.Order;

public class FIFO extends SchedulingAlgorithm {

	@Override
	protected List<Order> getSortedPending() {
		return super.getPending();
	}
}
