package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import company.workstations.Workstation;
import user.User;
import util.TimeStamp;
import vehicle.assemblytasks.Task;

public class Order {

	private final User client;
	private final Set<Task> tasks;
	private final int buildingtime;
	private final TimeStamp deadline;
	private TimeStamp completionTime = null;
//	private Vehicle vehicle;

	/**
	 * Makes a new order based on the given specification.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given orderspecification is not valid.
	 */
	public Order(OrderBuilder spec) {
		if (spec == null || !spec.isValid()) {
			throw new IllegalArgumentException("invalid specification");
		}
		this.client = spec.getClient();
		this.tasks = spec.getTasks();
		this.buildingtime = spec.getBuildingTimePerWorkstation();
		this.deadline = spec.getDeadline();
//		this.vehicle = spec.getVehicle();
	}

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
	public void setFinished(TimeStamp time) {
		if (isFinished()) {
			throw new IllegalStateException("this order is already finished");
		}
		completionTime = time;
	}

	/**
	 * @return completion time, or null time if this order is not yet finished
	 */
	public TimeStamp getCompletionTime() {
		return completionTime;
	}

	/**
	 * @return deadline of this order or null if this order has no deadline.
	 */
	public TimeStamp getDeadline() {
		return deadline;
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
	 * @return time in minutes that is spent in each workstation in normal
	 *         circumstances.
	 */
	public int getBuildingTimePerWorkstation() {
		return buildingtime;
	}

	/**
	 * @return a set containing the id's of all workstations in which parts
	 *         should be installed for the this order.
	 */
	public Set<Class<? extends Workstation>> getNeededWorkstations() {
		Set<Class<? extends Workstation>> result = new HashSet<>();
		for (Task task : this.getTasks()) {
			result.add(task.getResponsibleWorkstation());
		}
		return result;
	}

	@Override
	public String toString() {
		return "Order"; // TODO
	}
}
