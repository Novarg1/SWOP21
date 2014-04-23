package company;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import car.Car;

//TODO bugfix: wanneer schedule leeg is en dan een order krijgt, wordt dit niet
//		automatisch op de assemblyLine gezet.

/**
 * An assemblyline has a list of workstations and can be advanced.
 */
public class AssemblyLine implements Observer {

	private Schedule schedule;
	private WorkStation[] workstations;

	/**
	 * Creates a new assemblyLine with the given list of workstations
	 * 
	 * @param workstations
	 */
	public AssemblyLine(Schedule schedule) {
		if (schedule == null) {
			throw new IllegalArgumentException("invalid schedule");
		}
		this.schedule = schedule;
		workstations = new WorkStation[] { new CarBodyPost(),
				new DriveTrainPost(), new AccessoiresPost() };
	}

	/**
	 * This method is called whenever a carpart is installed in one of the
	 * workstations on this assemblyLine.
	 */
	@Override
	public void update(Observable ws, Object obj) {
		while (isReadyToAdvance() && !schedule.isOutOfOrders()) {
			advance();
		}
	}

	/**
	 * advances the assembly line. The assemblyLine calls its schedule for
	 * getting the next order.
	 */
	private void advance() {
		if (!isReadyToAdvance()) {
			throw new IllegalStateException("Cannot advance assembly line");
		}
		Car next = new Car(schedule.getNextOrder(getHighestWorkTime()));
		for (int i = workstations.length - 1; i > 0; i--) {
			workstations[i].setCurrentCar(workstations[i - 1].getCurrentCar());
		}
		workstations[0].setCurrentCar(next);
	}

	/**
	 * @return true if all workstations on this line are ready
	 */
	public boolean isReadyToAdvance() {
		for (WorkStation workstation : workstations) {
			if (!workstation.isReady()) {
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
		for (WorkStation w : workstations)
			if (w.getCurrentCar() != null)
				return false;
		return true;
	}

	/**
	 * @return the highest of all worktimes of workstations on this
	 *         assemblyline.
	 */
	private int getHighestWorkTime() {
		int highest = 0;
		for (WorkStation ws : workstations) {
			int worktime = ws.getWorktime();
			if (worktime > highest) {
				highest = worktime;
			}
		}
		return highest;
	}
}
