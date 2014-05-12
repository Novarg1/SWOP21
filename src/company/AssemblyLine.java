package company;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import company.workstations.Workstation;
import vehicle.order.Order;

/*
 * TODO
 * assemblylines should support specified set of models --> inheritance?
 */

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public class AssemblyLine extends Observable implements Observer {

	private Workstation[] workstations;
	private Status status = Status.OPERATIONAL;

	public enum Status {
		OPERATIONAL, MAINTENANCE, BROKEN;
	}

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * 
	 * @param workstations
	 */
	public AssemblyLine(Workstation[] workstations) {
		if (workstations == null) {
			throw new IllegalArgumentException();
		}
		this.workstations = workstations;
		for (Workstation ws : workstations) {
			ws.addObserver(this);
		}
	}

	/**
	 * Returns the current status of this assembly line.
	 */
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		if (status == null) {
			throw new IllegalArgumentException();
		}
		this.status = status; // TODO
	}

	/**
	 * This method is called whenever a carpart is installed in one of the
	 * workstations on this assemblyLine. The assemblyline will check if it's
	 * ready to advance and, if so, notify the schedule.
	 * 
	 * @param ws
	 *            the workstation that notified this assemblyline. For now,
	 *            however, this parameter will be ignored.
	 * @param obj
	 *            this parameter will be ignored.
	 */
	@Override
	public void update(Observable ws, Object obj) {
		if (isReadyToAdvance()) {
			notifyObservers(getHighestWorkTime());
		}
	}

	/**
	 * advances the assembly line. Putting the given order on the first
	 * workstation and returning the order on the last workstation.
	 * 
	 * @param order
	 *            The order that will be put in the first workstation. May be
	 *            null if no new order should be started this cycle.
	 * 
	 * @throws IllegalStateException
	 *             if not all workstations on this line are ready yet.
	 * 
	 * @return The order that was on the last workstation and is now finished,
	 *         or null if no order was being assembled on the last workstation.
	 */
	public synchronized Order advance(Order next) {
		if (!isReadyToAdvance()) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		Order result = workstations[workstations.length - 1].getOrder();
		for (int i = workstations.length - 1; i > 0; i--) {
			workstations[i].setOrder(workstations[i - 1].getOrder());
		}
		workstations[0].setOrder(next);

		if (isReadyToAdvance()) {
			notifyObservers(0);
		}
		return result;
	}

	/**
	 * Method to check whether the assemblyline can be advanced
	 * 
	 * @return true if all workstations on this line are ready
	 */
	public boolean isReadyToAdvance() {
		for (Workstation workstation : workstations) {
			if (!workstation.isReady()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return The list of workstations this assemblyLine contains
	 */
	public Workstation[] getWorkstations() {
		return Arrays.copyOf(workstations, workstations.length);
	}

	/**
	 * Method to check whether no workstation of the assembly line has a car
	 * 
	 * @return true if no workstation on this assemblyline has a car.
	 */
	public boolean isEmpty() {
		for (Workstation w : workstations)
			if (w.getOrder() != null)
				return false;
		return true;
	}

	/**
	 * Helper method to find out how long it takes to be able to advance the
	 * assembly line
	 * 
	 * @return the highest of all worktimes of workstations on this
	 *         assemblyline.
	 */
	private int getHighestWorkTime() {
		int highest = 0;
		for (Workstation ws : workstations) {
			int worktime = ws.getWorktime();
			if (worktime > highest) {
				highest = worktime;
			}
		}
		return highest;
	}
}
