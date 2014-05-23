package vehicle.order;

import java.util.Collections;
import java.util.Set;

import util.Timestamp;
import vehicle.assemblytasks.Task;

public abstract class CustomOrderBuilder extends OrderBuilder {

	private Timestamp deadline = null;

	@Override
	public Timestamp getDeadline() {
		return deadline;
	}

	@Override
	protected Set<Task> getModelSpecificTasks() {
		return Collections.emptySet();
	}
	
	/**
	 * Sets the deadline for this custom order to the given timestamp.
	 */
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
}
