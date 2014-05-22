package company.schedule;

import java.util.List;

import vehicle.order.Order;

public class FIFO extends SchedulingAlgorithm {

	@Override
	protected boolean scheduleNext() {
		List<Order> pending = super.getPending();
		if(pending.isEmpty()) {
			return false;
		}
		Order next = pending.get(0);
		throw new IllegalStateException("not implemented");
	}
}
