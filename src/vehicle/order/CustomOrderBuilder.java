package vehicle.order;

import java.util.Collections;
import java.util.Set;

import util.TimeStamp;
import vehicle.assemblytasks.Task;

public abstract class CustomOrderBuilder extends OrderBuilder {

	private TimeStamp deadline = null;

	@Override
	public TimeStamp getDeadline() {
		return deadline;
	}

	@Override
	protected Set<Task> getModelSpecificTasks() {
		return Collections.emptySet();
	}
	
	/**
	 * Sets the deadline for this custom order to the given timestamp.
	 */
	public void setDeadline(TimeStamp deadline) {
		this.deadline = deadline;
	}
}
