package controllers;

import java.util.ArrayList;
import java.util.List;

import company.FIFO;
import company.Schedule;
import company.SchedulingAlgorithm;
import util.LineReader;

public class ScheduleController {
	private Schedule schedule;

	public ScheduleController(Schedule s) {
		this.schedule = s;
	}

	public List<String> getAlgorithms() {
		List<String> list = new ArrayList<String>();
		list.add("FIFO");
		list.add("Specification Batch");
		return list;
	}

	public void setAlgorithm(SchedulingAlgorithm algorithm) {
		schedule.setAlgorithm(algorithm);
	}

	public SchedulingAlgorithm getCurrentAlgorithm() {
		return schedule.getCurrentAlgorithm();
	}
}
