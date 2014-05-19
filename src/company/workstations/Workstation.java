package company.workstations;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import vehicle.assemblytasks.InstallToolStorage;
import vehicle.assemblytasks.Task;
import vehicle.order.Order;

/**
 * Represents any workstation in this company. A workstation can contain an
 * order and a log, showing which tasks were already performed during this cycle
 * and how long it took to perform them.
 */
public abstract class Workstation extends Observable implements Cloneable {

	private Order current = null;
	private Map<Task, Integer> log = new HashMap<>();
	
	/**
	 * Sets the current order in this workstation to the given order.
	 */
	public void setOrder(Order current) {
		this.current = current;
		log.clear();
	}

	/**
	 * @return the current job of this workstation
	 */
	public Order getOrder() {
		return current;
	}

	/**
	 * performs the given task.
	 * 
	 * @param task
	 *            the task that was performed.
	 * @param time
	 *            the time it took to perform the given task.
	 * @throws IllegalArgumentException
	 *             if the given task cannot be performed in this workstation or
	 *             if the given time is negative.
	 * @throws IllegalStateException
	 *             if there is currently no order being assembled in this
	 *             workstation.
	 */
	public void perform(Task task, int time) {
		if (current == null) {
			throw new IllegalStateException("No car in this workstation");
		}
		if (!getPendingTasks().contains(task)) {
			throw new IllegalArgumentException("invalid task");
		}
		if (time < 0) {
			throw new IllegalArgumentException("invalid time");
		}
		task.perform();
		log.put(task, time);
		notifyObservers();
	}

	/**
	 * @return true if all tasks are completed for the current order in this
	 *         workstation
	 */
	public boolean isReady() {
		return this.getPendingTasks().isEmpty();
	}

	/**
	 * @return the time in minutes that has been spent performing tasks this
	 *         cycle.
	 */
	public int getWorktime() {
		int result = 0;
		for (int time : log.values()) {
			result += time;
		}
		return result;
	}

	/**
	 * @return a set of tasks that still need to be completed for the current
	 *         car. If no car is present, returns an empty set.
	 */
	public Set<Task> getPendingTasks() {
		if (current == null) {
			return Collections.emptySet();
		}
		Set<Task> pendingTasks = new HashSet<>();
		for (Task task : current.getTasks()) {
			if (task.getResponsibleWorkstation().equals(this.getClass())) {
				pendingTasks.add(task);
			}
		}
		return pendingTasks;
	}

	/**
	 * Returns a copy of this workstation. changes made to the clone will not
	 * affect the original.
	 * @throws CloneNotSupportedException 
	 */
	@Override
	public Workstation clone() {
		try {
			Workstation clone = (Workstation) super.clone();
//			clone.log = new HashMap TODO
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); //unreachable
			return null;
		}
	}

	public static void main(String[] args) {
		Workstation ws = new BodyPost();
		Workstation ws2 = ws.clone();
		
		ws2.log.put(new InstallToolStorage(), 5);
		
		System.out.println(ws.log.isEmpty());
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
}
