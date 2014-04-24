package controllers;

import java.util.ArrayList;
import java.util.List;

import company.FIFO;
import company.Schedule;
import company.SchedulingAlgorithm;
import util.LineReader;

public class ScheduleController {
	private Schedule schedule;

	/**
	 * constructor takes a schedule as parameter, this controller will
	 * only work over that schedule
	 * @param s
	 */
	public ScheduleController(Schedule s) {
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
