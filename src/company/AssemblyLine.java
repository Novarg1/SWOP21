package company;

import java.util.Arrays;

import car.Car;
import car.Order;

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public class AssemblyLine {

	private WorkStation[] workstations;

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * @param workstations
	 */
	public AssemblyLine() {
		workstations = new WorkStation[] {
				new CarBodyPost(),
				new DriveTrainPost(),
				new AccessoiresPost()
		};
	}

	/**
	 * advances the assembly line. If 
	 * 
	 * @param order The order for the car that is to be assembled next.
	 */
	public void advance(Order order) {
		if(!isReadyToAdvance()) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		for(int i = workstations.length - 1; i > 0; i--) {
			workstations[i].setCurrentCar(workstations[i - 1].getCurrentCar());
		}
		workstations[0].setCurrentCar(new Car(order));
	}

	/**
	 * @return true if all workstations on this line are ready
	 */
	public boolean isReadyToAdvance() {
		for (WorkStation workstation : workstations) {
			if(!workstation.isReady()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return The list of workstations this assemblyLine contains
	 */
	public WorkStation[] getWorkstations() {
		return Arrays.copyOf(workstations, workstations.length);
	}
	
	/**
	 * @return true if no workstation on this assemblyline has a car.
	 */
	public boolean isEmpty() {
		for(WorkStation w : workstations)
			if(w.getCurrentCar() != null)
				return false;
		return true;
	}
}
