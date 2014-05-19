package vehicle.order;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import company.workstations.Workstation;
import user.User;
import util.Timestamp;
import vehicle.assemblytasks.Task;

public class Order implements Cloneable {

	private final User client;
	private final Set<Task> tasks;
	private final Timestamp deadline;
	private final Map<Class<? extends Workstation>, Integer> buildtimes;
	private final Class<? extends OrderBuilder> type;
	private Timestamp completionTime = null;

	/**
	 * Makes a new order based on the given specification.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given orderspecification is not valid.
	 */
	public Order(OrderBuilder builder) {
		if (builder == null || !builder.isValid()) {
			throw new IllegalArgumentException("invalid specification");
		}
		this.client = builder.getClient();
		this.tasks = builder.getTasks();
		this.buildtimes = builder.getBuildTimePerWorkstation();
		this.deadline = builder.getDeadline();
		this.type = builder.getClass();
	}

//	/**
//	 * creates a clone of the given order.
//	 */
//	private Order(Order other) {
//		this.client = other.client;
//		//TODO
//	}

	/**
	 * @return the client associated with this order.
	 */
	public User getClient() {
		return client;
	}

	/**
	 * Sets this order as finished on the given time. After this method is
	 * called, the completion time can not be changed again.
	 * 
	 * @throws IllegalStateException
	 *             if this order is already finished
	 */
	public void setFinished(Timestamp time) {
		if (isFinished()) {
			throw new IllegalStateException("this order is already finished");
		}
		completionTime = time;
	}

	/**
	 * @return completion time, or null time if this order is not yet finished
	 */
	public Timestamp getCompletionTime() {
		return completionTime;
	}

	/**
	 * @return deadline of this order or null if this order has no deadline.
	 */
	public Timestamp getDeadline() {
		return deadline;
	}

	/**
	 * Returns the type of this order (e.g. ModelA)
	 */
	public Class<? extends OrderBuilder> getType() {
		return type;
	}

	/**
	 * @return true if this order has been finished
	 */
	public boolean isFinished() {
		return completionTime != null;
	}

	/**
	 * @return A copy of the set containing all assembly tasks that need to be
	 *         performed in order to complete this order.
	 */
	public Set<Task> getTasks() {
		return new HashSet<>(tasks);
	}

	/**
	 * @return true if this order and the given order have the same set of
	 *         tasks.
	 */
	public boolean matches(Order other) {
		return this.tasks.equals(other.tasks);
	}

	/**
	 * @return time in minutes that is spent in the given workstation in normal
	 *         circumstances.
	 */
	public int getBuildingTimeFor(Class<? extends Workstation> ws) {
		Integer result = buildtimes.get(ws);
		return (result == null || result < 0) ? 0 : result;
	}

	/**
	 * @return a set containing the workstation types in which parts should be
	 *         installed for the this order.
	 */
	public Set<Class<? extends Workstation>> getNeededWorkstations() {
		Set<Class<? extends Workstation>> result = new HashSet<>();
		for (Task task : this.tasks) {
			result.add(task.getResponsibleWorkstation());
		}
		return result;
	}

	@Override
	public String toString() {
		return "Order"; // TODO
	}
}
