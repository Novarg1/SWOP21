package vehicle.assemblytasks;

import company.workstations.Workstation;

/**
 * Represents an assembly task that can be performed on a vehicle.
 */
public abstract class Task {
	
	private boolean isPerformed = false;
	
	protected Task() {
		
	}
	
	/**
	 * true if this task has been performed.
	 */
	public boolean isPerformed() {
		return isPerformed;
	}

	/**
	 * Sets this task to performed. This change can not be undone.
	 */
	public void perform() {
		isPerformed = true;
	}

	/**
	 * Returns the type of workstation in which this task can be performed.
	 */
	public abstract Class<? extends Workstation> getResponsibleWorkstation();

	/**
	 * Returns this assembly task instructions in string format.
	 */
	public abstract String toString();
}
