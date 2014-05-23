package controllers;

import company.schedule.Scheduler;
import company.schedule.SchedulingAlgorithm;

/**
 * ScheduleController bundles all scheduler related functionality
 * @author jonathanlangens
 *
 */
public class ScheduleController {
	private Scheduler schedule;

	/**
	 * constructor takes a schedule as parameter, this controller will
	 * only work over that schedule
	 * @param s
	 */
	public ScheduleController(Scheduler s) {
		this.schedule = s;
	}

	/**
	 * sets the passed algorithm as current for this schedule
	 * @param algorithm
	 */
	public void setAlgorithm(SchedulingAlgorithm algorithm) {
		schedule.setAlgorithm(algorithm);
	}

	/**
	 * @return the currently deployed algorithm for this schedule
	 */
	public SchedulingAlgorithm getCurrentAlgorithm() {
		return schedule.getCurrentAlgorithm();
	}
}
