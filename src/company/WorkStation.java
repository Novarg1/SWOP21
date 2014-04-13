package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import car.CarOrder;
import car.CarPart;

/**
 * Represents any workstation in this company. Subclasses should define a list
 * of carpart-classes that this workstation can install on cars
 */
public abstract class WorkStation {

	private CarOrder current = null;
	private boolean ready = true;
	protected final Class<?>[] installableParts;
	protected String id;

	protected WorkStation(Class<?>[] installableParts) {
		this.installableParts = installableParts;
	}

	/**
	 * Sets the current job of this workstation to the given CarInProduction
	 * 
	 * @param current
	 */
	public void setCurrentJob(CarOrder current) {
		this.current = current;
		ready = (current == null);
	}

	/**
	 * @return the current job of this workstation
	 */
	public CarOrder getCurrentJob() {
		return current;
	}

	/**
	 * @return true if all tasks are completed for the current job in this
	 *         workstation
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * Installs the given part to the car currently in this workstation
	 * 
	 * @param part
	 */
	public void install(CarPart part) {
	/*	if (!this.canInstall(part)) {
			throw new IllegalArgumentException();
		}
		current.CAR.install(part);
		ready = getPendingTasks().isEmpty();*/
	}

	/**
	 * @param part
	 * @return true if the car currently in this workstation contains the given
	 *         part
	 */
	public boolean isInstalled(CarPart part) {
		//return current.CAR.hasPart(part);
		return false;
	}

	/**
	 * @return a list of tasks that still need to be completed for the current
	 *         job
	 */
	public List<CarPart> getPendingTasks() {
	/*	if (current == null) {
			return Collections.emptyList();
		}
		List<CarPart> list = new ArrayList<CarPart>(5);
		for (Class<?> partClass : installableParts) {
			CarPart part = current.ORDER.SPECIFICATION.get(partClass);
			if (!isInstalled(part)) {
				list.add(part);
			}
		}
		return list;*/
		return null;
	}

	/**
	 * @param part
	 * @return true if this workstation is suited for installing the given part
	 *         to a car
	 */
	private boolean canInstall(CarPart part) {
		for (int i = 0; i < installableParts.length; i++) {
			if (part.getClass() == installableParts[i]) {
				return true;
			}
		}
		return false;
	}

}
