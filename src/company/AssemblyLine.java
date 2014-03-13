package company;

import car.Car;
import car.CarInProduction;
import car.CarOrder;

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public class AssemblyLine {

	private WorkStation[] workStations;

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * @param workStations
	 */
	public AssemblyLine(WorkStation[] workStations) {
		if(workStations == null || workStations.length < 1) {
			throw new IllegalArgumentException();
		}
		this.workStations = workStations;
	}

	/**
	 * advances the assembly line
	 * 
	 * @return
	 * 		The carOrder that was finished;
	 * 		null if no car was finished
	 */
	public CarOrder advance(CarOrder order) {
		if(!isReadyToAdvance()) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		CarInProduction next = new CarInProduction(new Car(), order);
		for (int i = 0; i < workStations.length; i++) {
			CarInProduction temp = workStations[i].getCurrentJob();
			workStations[i].setCurrentJob(next);
			next = temp;
		}
		return next==null ? null : next.ORDER;
	}

	/**
	 * @return true if all workstations on this line are ready
	 */
	public boolean isReadyToAdvance() {
		for (int i = 0; i < workStations.length; i++) {
			if(workStations[i] != null && !workStations[i].isReady()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return The list of workstations this assemblyLine contains
	 */
	public WorkStation[] getWorkstations() {
		return workStations;
	}
}
