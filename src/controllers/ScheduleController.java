package controllers;

import java.util.ArrayList;
import java.util.List;

import company.FIFO;
import company.Schedule;
import company.Schedule.Algorithm;
import util.LineReader;

public class ScheduleController
{
	private Schedule schedule;
	
	public ScheduleController(Schedule s)
	{
		this.schedule = s;
	}
	
	public List<String> getAlgorithms()
	{
		List<String> list = new ArrayList<String>();
		list.add("FIFO");
		list.add("Specification Batch");
		return list;
	}
	
	public void setAlgorithm(String algorithm)
	{
		switch(algorithm)
		{
		case "FIFO":schedule.setAlgorithm(Algorithm.FIFO);break;
		case "Specification Batch":schedule.setAlgorithm(Algorithm.SPECIFICATION_BATCH);break;
		}
	}
	
	public Algorithm getCurrentAlgorithm()
	{
		return schedule.getCurrentAlgorithm();
	}
}
