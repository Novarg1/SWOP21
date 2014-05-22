package company.workstations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import vehicle.assemblytasks.Task;
import vehicle.order.Order;

/**
 * Represents any workstation in this company. A workstation can contain an
 * order and a log, showing which tasks were already performed during this cycle
 * and how long it took to perform them.
 */
public abstract class Workstation extends Observable implements Cloneable {

	private Order current = null;

	/**
	 * Sets the current order in this workstation to the given order.
	 */
	public void setOrder(Order current) {
		this.current = current;
	}

	/**
	 * @return the current job of this workstation
	 */
	public Order getOrder() {
		return current;
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
		for (Task task : this.getAllTasks()) {
			if (task.isPerformed()) {
				result += task.getTime();
			}
		}
		return result;
	}

	/**
	 * @return a set of tasks that still need to be completed for the current
	 *         car. If no car is present, returns an empty set.
	 */
	public Set<Task> getPendingTasks() {
		Set<Task> tasks = this.getAllTasks();
		if (tasks == null) {
			return Collections.emptySet();
		}
		Iterator<Task> it = tasks.iterator();
		while (it.hasNext()) {
			if (it.next().isPerformed()) {
				it.remove();
			}
		}
		return tasks;
	}

	/**
	 * @return a set containing all tasks for the current order, finished or
	 *         not, that can be performed in this workstation, or an empty set
	 *         if there is no current order in this workstation.
	 */
	public Set<Task> getAllTasks() {
		if (current == null) {
			return Collections.emptySet();
		}
		Set<Task> tasks = new HashSet<>();
		for (Task task : current.getTasks()) {
			if (task.getResponsibleWorkstation().equals(this.getClass())) {
				tasks.add(task);
			}
		}
		return tasks;
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	@Override
	public Workstation clone() {
		try {
			return (Workstation) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); // unreachable
			return null;
		}
	}
}
