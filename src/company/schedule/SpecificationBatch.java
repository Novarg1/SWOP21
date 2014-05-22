package company.schedule;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vehicle.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {

	private Order batchSample;

	public SpecificationBatch(Order batchSample) {
		if (batchSample == null) {
			throw new IllegalArgumentException("invalid batch sample");
		}
		this.batchSample = batchSample;
	}

	@Override
	protected List<Order> getSortedPending() {
		List<Order> pending = super.getPending();
		List<Order> result = new LinkedList<Order>();
		Iterator<Order> it = pending.iterator();
		while(it.hasNext()) {
			Order next = it.next();
			if(next.matches(batchSample)) {
				it.remove();
				result.add(next);
			}
		}
		for(Order order : pending) {
			result.add(order);
		}
		return result;
	}
}
