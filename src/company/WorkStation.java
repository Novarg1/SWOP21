package company;

import car.Car;
import car.parts.Carpart;
import car.parts.CarpartsSet;

/**
 * Represents any workstation in this company.
 */
public abstract class WorkStation {

	private Car current = null;
	private final int id;

	protected WorkStation(int id) {
		this.id = id;
	}

	/**
	 * Sets the current Car in this workstation to the given car.
	 */
	public void setCurrentCar(Car current) {
		this.current = current;
	}

	/**
	 * @return the current job of this workstation
	 */
	public Car getCurrentCar() {
		return current;
	}

	/**
	 * Installs the given part to the car currently in this WorkStation.
	 */
	public void install(Carpart part) {
		if (current == null) {
			throw new IllegalStateException("No car in this workstation");
		}
		current.install(part);
	}

	/**
	 * @return true if all tasks are completed for the current car in this
	 *         workstation
	 */
	public boolean isReady() {
		return this.getPendingTasks().isEmpty();
	}

	/**
	 * @return a set of tasks that still need to be completed for the current
	 *         car. If no car is present, returns an empty set.
	 */
	public CarpartsSet getPendingTasks() {
		CarpartsSet pendingTasks = new CarpartsSet();
		if (current == null) {
			return pendingTasks; // empty set
		}
		for (Carpart part : current.getOrder().getParts()) {
			if ((part.getWorkStationID() == this.getId())
					&& !(current.getParts().contains(part))) {
				pendingTasks.add(part);
			}
		}
		return pendingTasks;
	}

	/**
	 * @return id number of this workstation
	 */
	public int getId() {
		return this.id;
	}
}
