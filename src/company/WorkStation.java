package company;

import java.util.Map;

import car.Car;
import car.parts.Carpart;
import car.parts.CarpartsSet;

/**
 * Represents any workstation in this company. A workstation can contain a car
 * and a log, showing which carparts were already installed during this cycle
 * and how long it took to install them.
 */
public abstract class WorkStation {

	private final int id;
	private Car current = null;
	private Map<Carpart, Integer> log;

	protected WorkStation(int id) {
		this.id = id;
	}

	/**
	 * Sets the current Car in this workstation to the given car.
	 */
	public void setCurrentCar(Car current) {
		this.current = current;
		log.clear();
	}

	/**
	 * @return the current job of this workstation
	 */
	public Car getCurrentCar() {
		return current;
	}

	/**
	 * Installs the given part to the car currently in this WorkStation.
	 * 
	 * @param part
	 *            the carpart that is installed.
	 * @param time
	 *            the time it took to install the given part.
	 */
	public void install(Carpart part, int time) {
		if (current == null) {
			throw new IllegalStateException("No car in this workstation");
		}
		if (!getPendingTasks().contains(part)) {
			throw new IllegalArgumentException("invalid carpart");
		}
		if (time < 0) {
			throw new IllegalArgumentException("invalid time");
		}
		log.put(part, time);
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
	 * @return the time in minutes that has been spent installing carparts this
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
