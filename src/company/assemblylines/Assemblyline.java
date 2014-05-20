package company.assemblylines;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import company.workstations.Workstation;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public abstract class Assemblyline extends Observable implements Observer,
		Cloneable {

	private Workstation[] workstations;
	private Status status = Status.OPERATIONAL;
	private Set<Class<? extends OrderBuilder>> ignored;

	public enum Status {
		OPERATIONAL, MAINTENANCE, BROKEN;
	}

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * 
	 * @param workstations
	 */
	protected Assemblyline(Workstation[] workstations) {
		if (workstations == null) {
			throw new IllegalArgumentException();
		}
		this.workstations = workstations;
		for (Workstation ws : workstations) {
			ws.addObserver(this);
		}
		ignored = new HashSet<>();
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
	 * makes this assemblyline remember not to accept orders of the given type.
	 */
	protected void ignore(Class<? extends OrderBuilder> ordertype) {
		ignored.add(ordertype);
	}

	public boolean accepts(Order order) {
		for (Class<? extends Workstation> req : order.getNeededWorkstations()) {
			if (!hasWorkstation(req)) {
				return false;
			}
		}
		return !ignored.contains(order.getClass());
	}

	/**
	 * Checks whether this assemblyline accepts the given order or not.
	 * 
	 * @return false if this assemblyline does not contain the needed
	 *         workstations for assembly of the given order or if the type of
	 *         the given order appears in the ignore-list of this assemblyline.
	 *         Otherwise returns true.
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

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	@Override
	public Assemblyline clone() {
		try {
			Assemblyline clone = (Assemblyline) super.clone();
			clone.ignored = new HashSet<>(this.ignored);
			clone.workstations = new Workstation[this.workstations.length];
			for(int i = 0; i < this.workstations.length; i++) {
				clone.workstations[i] = this.workstations[i].clone();
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); //unreachable
			return null;
		}
	}
}
