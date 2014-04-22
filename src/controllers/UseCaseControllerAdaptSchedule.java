package controllers;

import company.FIFO;
import company.Schedule.Algorithm;
import util.LineReader;

public class UseCaseControllerAdaptSchedule implements UseCaseController
{
	private SystemController systemController;
	
	public boolean guideUseCase(SystemController c)
	{
		systemController = c;
		
		System.out.print("The current selected scheduling algorithm is \"");
		System.out.println(systemController.getCurrentSchedulingAlgorithm() + "\"");
		
		System.out.println("Do you want to change this to\n(1) FIFO\n(2) Specification Batch");
		switch(LineReader.readInt())
		{
		case 1:systemController.setSchedulingAlgorithm(Algorithm.FIFO);break;
		case 2:systemController.setSchedulingAlgorithm(Algorithm.SPECIFICATION_BATCH);break;
		}
		return true;
	}
}
