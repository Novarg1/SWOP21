package company;

import java.util.List;

import car.Car;
import car.CarPart;

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
	 * @return true if all tasks are completed for the current car in this
	 *         workstation
	 */
	public boolean isReady() {
		return this.getPendingTasks().isEmpty();
	}

	/**
	 * @return a list of tasks that still need to be completed for the current
	 *         job
	 */
	public List<CarPart> getPendingTasks() {
		return null; // TODO
	}

	public int getId() {
		return this.id;
	}
}
