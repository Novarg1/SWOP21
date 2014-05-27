package vehicle.assemblytasks;

import company.workstations.Workstation;

/**
 * Represents an assembly task that can be performed on a vehicle.
 */
public abstract class Task implements Cloneable {

	private boolean isPerformed = false;
	private int time = -1;

	/**
	 * true if this task has been performed.
	 */
	public boolean isPerformed() {
		return isPerformed;
	}

	/**
	 * Sets this task to performed. This change can not be undone.
	 * 
	 * @param ws
	 *            the workstation in which this task is being performed.
	 * @param time
	 *            the time it took to complete this task.
	 * @throws IllegalStateException
	 *             if this task is not a currently pending task of the given
	 *             workstation.
	 */
	public void perform(Workstation ws, int time) {
		if (!ws.getPendingTasks().contains(this)) {
			throw new IllegalStateException(
					"this task cannot be performed in the given workstation");
		}
		if (time < 0) {
			throw new IllegalArgumentException("cannot set negative time");
		}
		isPerformed = true;
		this.time = time;
		ws.notifyObservers();
	}

	/**
	 * Returns the time this task took to be performed, or throws an
	 * IllegalStateException if this task is not yet performed.
	 */
	public int getTime() {
		if (!this.isPerformed()) {
			throw new IllegalStateException("task is not yet performed");
		}
		return time;
	}

	/**
	 * Returns the type of workstation in which this task can be performed.
	 */
	public abstract Class<? extends Workstation> getResponsibleWorkstation();

	/**
	 * Returns this assembly task instructions in string format.
	 */
	public abstract String toString();

	@Override
	public Task clone() {
		try {
			return (Task) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); // unreachable
			return null;
		}
	}
}
