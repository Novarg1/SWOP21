package company.assemblylines;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import company.workstations.Workstation;
import util.Timestamp;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public abstract class Assemblyline extends Observable implements Observer {

	private Workstation[] workstations;
	private Status status = Status.OPERATIONAL;
	private Timestamp currentTime;
	private Set<Class<? extends OrderBuilder>> ignored;

	public enum Status {
		OPERATIONAL, MAINTENANCE, BROKEN;

		/**
		 * TODO comments
		 */
		private void check(Assemblyline ass) {
			switch (this) {
			case MAINTENANCE:
				if (ass.isEmpty()) {
					ass.currentTime = ass.currentTime.increaseTime(4 * 60);
					ass.setStatus(OPERATIONAL, -1);
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * 
	 * @param workstations
	 */
	protected Assemblyline(Workstation[] workstations, int day) {
		if (workstations == null) {
			throw new IllegalArgumentException();
		}
		this.workstations = workstations;
		for (Workstation ws : workstations) {
			ws.addObserver(this);
		}
		ignored = new HashSet<>();
		currentTime = Timestamp.beginningOfDay(day);
	}

	/**
	 * Returns the current time of this assemblyline.
	 */
	public Timestamp getCurrentTime() {
		return currentTime;
	}

	/**
	 * Returns the current status of this assembly line.
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Changes the status of this assemblyline to the given status.
	 * 
	 * @param status
	 *            the new status of the assemblyline.
	 * @param time
	 *            if the current status of this assemblyline is broken, this
	 *            parameter represents the time (in minutes) that this
	 *            assemblyline was broken. Otherwise this parameter will be
	 *            ignored.
	 */
	public void setStatus(Status status, int time) {
		if (status == null || (this.status == Status.BROKEN && time < 0)) {
			throw new IllegalArgumentException();
		}
		if (this.status == Status.BROKEN) {
			currentTime.increaseTime(time);
		}
		this.status = status;
		status.check(this);
		notifyObservers();
	}

	/**
	 * makes this assemblyline remember not to accept orders of the given type.
	 */
	protected void ignore(Class<? extends OrderBuilder> ordertype) {
		ignored.add(ordertype);
	}

	/**
	 * Checks whether this assemblyline accepts the given order or not.
	 * 
	 * @return false if this assemblyline does not contain the needed
	 *         workstations for assembly of the given order or if the type of
	 *         the given order appears in the ignore-list of this assemblyline.
	 *         Otherwise returns true.
	 */
	public boolean supports(Order order) {
		for (Class<? extends Workstation> req : order.getNeededWorkstations()) {
			if (!hasWorkstation(req)) {
				return false;
			}
		}
		return !ignored.contains(order.getClass());
	}

	/**
	 * Checks whether this assemblyline has a workstation of the given type.
	 * 
	 * @return true if this assemblyline has a workstation of the given type.
	 */
	private boolean hasWorkstation(Class<? extends Workstation> arg) {
		for (Workstation ws : this.workstations) {
			if (ws.getClass().equals(arg)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is called whenever a carpart is installed in one of the
	 * workstations on this assemblyLine. The assemblyline will notify the
	 * schedule.
	 * 
	 * @param ws
	 *            this parameter will be ignored.
	 * @param obj
	 *            this parameter will be ignored.
	 */
	@Override
	public void update(Observable ws, Object obj) {
		notifyObservers();
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
		if (!isReadyToAdvance() || this.status == Status.BROKEN) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		Order result = workstations[workstations.length - 1].getOrder();
		for (int i = workstations.length - 1; i > 0; i--) {
			workstations[i].setOrder(workstations[i - 1].getOrder());
		}
		workstations[0].setOrder(next);
		notifyObservers();
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
	 * Method to check whether no workstation on the assembly line currently has
	 * an order
	 * 
	 * @return true if no workstation on this assemblyline currently has an
	 *         order.
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
	 * @return the highest of all total worktimes of workstations on this
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
