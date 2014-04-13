package company;

import java.util.LinkedList;

import car.CarOrder;

public class CMCSystem 
{
	private Schedule schedule;
	private AssemblyLine assemblyLine;
	private UserManager userManager;
	
	private LinkedList<CarOrder> finishedOrders;
	
	public CMCSystem()
	{
		schedule = new Schedule();
		
		WorkStation[] workstations = new WorkStation[3];
		workstations[0] = new CarBodyPost();
		workstations[1] = new DriveTrainPost();
		workstations[2] = new AccessoiresPost();
		assemblyLine = new AssemblyLine(workstations);
		
		finishedOrders = new LinkedList<CarOrder>();
		
		userManager = new UserManager();
	}
	
	public LinkedList<CarOrder> getScheduledOrdersForUser(String user)
	{
		LinkedList<CarOrder> list = schedule.getUpcomingOrders();
		for(CarOrder o : list)
			if(o.CLIENT != user)
				list.remove(o);
		return list;
	}
	
	public LinkedList<CarOrder> getFinishedOrdersForUser(String user)
	{
		LinkedList<CarOrder> list = new LinkedList<CarOrder>();
		for(CarOrder o : finishedOrders)
			if(o.CLIENT == user)
				list.add(o);
		return list;
	}
	
	public AssemblyLine getAssemblyLine()
	{
		return this.assemblyLine;
	}
	
	public void advance(int time) throws Exception
	{
		if(!this.assemblyLine.isReadyToAdvance())
		{
			throw new Exception();
		}
		
		if(time <= 0)
		{
			throw new IllegalArgumentException("cannot advance to same or previous time");
		}
		
		// there are 2 basic scenarios:
		// scenario 1: the next order can be pushed on the assembly line today
		// scenario 2: the next order is for tomorrow however the time has to be
		//             increased and the assembly line pushed
		CarOrder orderToPush = null;
		if(this.schedule.canNextOrderBeBuildToday(3))
		{
			orderToPush = this.schedule.prepareNextOrder();
		}
		
		CarOrder newlyFinished = this.assemblyLine.advance(orderToPush);
		if(newlyFinished != null)
			this.finishedOrders.add(newlyFinished);
		
		this.schedule.increaseDayTime(time);
		
		if(schedule.getCurrentDay().shouldBeFinished() &&
				assemblyLine.isEmpty())
		{
			schedule.startNewDay();
		}
	}
	
	public Schedule getSchedule()
	{
		return this.schedule;
	}
	
	public void logInUser(int n)
	{
		this.userManager.logInUser(n);
	}
	
	public String getLoggedInUser()
	{
		return this.userManager.getLoggedInUser();
	}
}
