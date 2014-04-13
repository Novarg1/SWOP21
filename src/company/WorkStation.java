package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
	protected String id;

	protected WorkStation() {
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
	public boolean isReady() 
	{
		return (this.getPendingTasks().size() == 0);
	}


	/**
	 * @return a list of tasks that still need to be completed for the current
	 *         job
	 */
	public LinkedList<CarPart> getPendingTasks() 
	{
		LinkedList<CarPart> list = new LinkedList<CarPart>();
		for(CarPart p : current.getProductionSchemeFor(id))
			if(p.isInstalled()==false)
				list.add(p);
		return list;
	}
	
	public String getId()
	{
		return this.id;
	}
}
