package view;

import company.schedule.FIFO;
import company.schedule.SchedulingAlgorithm;
import company.schedule.SpecificationBatch;
import user.User;
import util.LineReader;
import vehicle.order.ModelABuilder;
import vehicle.order.ModelBBuilder;
import vehicle.order.ModelCBuilder;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import controllers.SystemController;
import controllers.ScheduleController;

public class ViewAdaptSchedule extends ViewOrderForm
{
	ScheduleController scheduleController;
	
	public ViewAdaptSchedule(SystemController c) {
		super(c);
		scheduleController = new ScheduleController(systemController.getSchedule());
	}

	@Override
	public boolean show() 
	{
		System.out.print("The current selected scheduling algorithm is \"");
		System.out.println(scheduleController.getCurrentAlgorithm() + "\"");
		
		System.out.println("Do you want to change this to");
		
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
		System.out.println("For which specification do you want to set the batch processor");
		User user = systemController.getLoggedInUser();
		
		System.out.println("For which model type?:\n(1) A\n(2) B\n(3) C");
		int choice = LineReader.readInt();
		
		OrderBuilder spec = getSpecification(choice);
		
		setBody(spec);
		setColor(spec);
		setEngine(spec);
		setGearbox(spec);
		setSeats(spec);
		setAirco(spec);
		setWheels(spec);
		setSpoiler(spec);
		
		Order order = new Order(spec, user);
		
		return new SpecificationBatch(order.getTasks());
	}

	private OrderBuilder getSpecification(int n) {
		OrderBuilder spec = null;
		switch(n)
		{
		case 1: spec = new ModelABuilder();break;
		case 2: spec = new ModelBBuilder();break;
		case 3: spec = new ModelCBuilder();break;
		default: throw new IllegalArgumentException("model type does not exist");
		}
		return spec;
	}
}
