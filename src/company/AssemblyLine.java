package company;

import car.Car;
import car.CarOrder;

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public class AssemblyLine {

	private WorkStation[] workstations;
	private Schedule schedule;

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * @param workstations
	 */
	public AssemblyLine(Schedule schedule) {
		if(schedule == null) {
			throw new IllegalArgumentException();
		}
		this.schedule = schedule;
		workstations = new WorkStation[] {
				new CarBodyPost(),
				new DriveTrainPost(),
				new AccessoiresPost()
		};
	}

	/**
	 * advances the assembly line
	 */
	public void advance() {
		if(!isReadyToAdvance()) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		for(int i = workstations.length - 1; i > 0; i--) {
			workstations[i].setCurrentCar(workstations[i - 1].getCurrentCar());
		}
		workstations[0].setCurrentCar(new Car());
	}

	/**
	 * @return true if all workstations on this line are ready
	 */
	public boolean isReadyToAdvance() {
		for (int i = 0; i < workstations.length; i++) {
			if(workstations[i] != null && !workstations[i].isReady()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return The list of workstations this assemblyLine contains
	 */
	public WorkStation[] getWorkstations() {
		return workstations;
	}
	
	public boolean isEmpty()
	{
		for(WorkStation w : workstations)
			if(w.getCurrentCar() != null)
				return false;
		return true;
	}
}
