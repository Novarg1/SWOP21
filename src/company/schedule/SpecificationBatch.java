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

	@Override
	protected boolean scheduleNext() {
		throw new IllegalStateException("not implemented");
	}
}
