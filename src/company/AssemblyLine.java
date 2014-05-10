package company;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import company.schedule.Schedule;
import company.workstations.Workstation;
import vehicle.Vehicle;
import vehicle.order.Order;

/*
 * TODO
 * Idee: schedule observeert assemblyline maar niet andersom --> minder koppeling
 * 
 */

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public class AssemblyLine extends Observable implements Observer {

	private Schedule schedule;
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
	public AssemblyLine(Schedule schedule, Workstation[] workstations) {
		if (schedule == null || workstations == null) {
			throw new IllegalArgumentException();
		}
		this.schedule = schedule;
		this.workstations = workstations;
		schedule.addAssemblyLine(this);
		for (Workstation ws : workstations) {
			ws.addObserver(this);
		}
		this.addObserver(schedule);
	}

	/**
	 * Returns the schedule that is responsible for scheduling the orders for
	 * this assembly line.
	 */
	public Schedule getSchedule() {
		return schedule;
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
	 * workstations on this assemblyLine, and whenever schedule receives a new
	 * order.
	 */
	@Override
	public void update(Observable ws, Object obj) {
		while (isReadyToAdvance() && !schedule.isOutOfOrders()) {
			advance();
		}
	}

	/**
	 * advances the assembly line. The assemblyLine calls its schedule for
	 * getting the next order.
	 */
	private void advance() {
		if (!isReadyToAdvance()) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		Order next = schedule.getNextOrder(getHighestWorkTime());
		for (int i = workstations.length - 1; i > 0; i--) {
			workstations[i].setOrder(workstations[i - 1].getOrder());
		}
		workstations[0].setOrder(next);
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
