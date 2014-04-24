package view;

import java.util.List;

import util.LineReader;
import controllers.SystemController;
import controllers.ScheduleController;

public class ViewAdaptSchedule extends View
{
	ScheduleController scheduleController;
	
	public ViewAdaptSchedule(SystemController c) {
		super(c);
		scheduleController = new ScheduleController(systemController.getSchedule());
	}

	@Override
	public boolean show() 
	{
		List<String> algorithms = scheduleController.getAlgorithms();
		
		System.out.print("The current selected scheduling algorithm is \"");
		System.out.println(scheduleController.getCurrentAlgorithm() + "\"");
		
		System.out.println("Do you want to change this to");
		
		int index = 1;
		
		for(String a:algorithms)
			System.out.println("(" + (index++) + ") " + a);
		
		index = LineReader.readInt();
		
		if(index > 0 && index <= algorithms.size())
			scheduleController.setAlgorithm(algorithms.get(index-1));
		return true;
	}
}
