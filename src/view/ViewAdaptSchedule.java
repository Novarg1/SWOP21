package view;

import java.util.List;

import car.ModelASpec;
import car.parts.Carpart;
import car.parts.CarpartsSet;
import car.parts.Color;
import car.parts.Seats;
import company.FIFO;
import company.SchedulingAlgorithm;
import company.SpecificationBatch;
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
		//List<String> algorithms = scheduleController.getAlgorithms();
		
		System.out.print("The current selected scheduling algorithm is \"");
		System.out.println(scheduleController.getCurrentAlgorithm() + "\"");
		
		System.out.println("Do you want to change this to");
		
		/*int index = 1;
		
		for(String a:algorithms)
			System.out.println("(" + (index++) + ") " + a);*/
		
		System.out.println("(1) FIFO\n(2) SpecificationBatch");
		
		int index = LineReader.readInt();
		
		if( index == 1)
			scheduleController.setAlgorithm(getFIFO());
		else
			scheduleController.setAlgorithm(getSpecificationBatch());
		return true;
	}
	
	private SchedulingAlgorithm getFIFO()
	{
		return new FIFO();
	}
	
	private SchedulingAlgorithm getSpecificationBatch()
	{
		System.out.println("What type of part do you want to set the specification option for?");
		System.out.println("(1) Color\n(2) Seats");
		int choice = LineReader.readInt();
		Class<? extends Carpart> type = (choice==1?Color.class:Seats.class);
		@SuppressWarnings("unchecked")
		List<Carpart> list = (List<Carpart>) (new ModelASpec()).getViableOptions(type);
		System.out.println("which option do you want to set it for?:");
		int index = 1;
		for(Carpart p : list)
			System.out.println("(" + (index++) + ") " + p);
		choice = LineReader.readInt();
		if(choice > 0  && choice <= list.size())
		{
			CarpartsSet set =  new CarpartsSet();
			set.add(list.get(index-1));
			return new SpecificationBatch(set);
		}
		return null;
	}
}
