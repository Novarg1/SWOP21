package controllers;

import java.util.Set;

import vehicle.assemblytasks.Task;
import company.workstations.Workstation;

/**
 * assembly controller allows manipulation of 1 workstation
 */
public class WorkstationController {

	private Workstation workstation;

	/**
	 * constructor takes a workstation and sets its member to the passed station
	 * 
	 * @param w
	 */
	public WorkstationController(Workstation w) {
		workstation = w;
	}

	/**
	 * @return all unfinished tasks for this workstation
	 */
	public Set<Task> getTasksForWorkstation() {
		return workstation.getPendingTasks();
	}

	/**
	 * performs the given task in the given time.
	 */
	public void perform(Task task, int time) {
		task.perform(workstation, time);
	}
}
