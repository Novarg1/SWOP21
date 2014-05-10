package vehicle.assemblytasks;

import company.workstations.Workstation;
import vehicle.Vehicle;

/**
 * Represents an assembly task that can be performed on a vehicle.
 */
public abstract class Task {
	
	protected Vehicle vehicle;
	
	protected Task(Vehicle vehicle) {
		if(vehicle == null) {
			throw new IllegalArgumentException();
		}
		this.vehicle = vehicle;
	}
	
	/**
	 * true if this task has been performed.
	 */
	public abstract boolean isPerformed();

	/**
	 * performs this task.
	 */
	public abstract void perform();

	/**
	 * Returns the type of workstation in which this task can be performed.
	 */
	public abstract Class<? extends Workstation> getResponsibleWorkstation();

	/**
	 * Returns this assembly task instructions in string format.
	 */
	public abstract String toString();
}
