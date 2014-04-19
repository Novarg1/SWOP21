package company;

import java.util.LinkedList;
import java.util.List;

import car.Order;

public class CMCSystem 
{
	private Schedule schedule;
	private AssemblyLine assemblyLine;
	private UserManager userManager;
	
	private List<Order> finishedOrders;
	
	public CMCSystem() {
		schedule = new Schedule();
		assemblyLine = new AssemblyLine(schedule);
		finishedOrders = new LinkedList<Order>();
		userManager = new UserManager();
	}
	
	public List<Order> getScheduledOrdersForUser(String user) {
		LinkedList<Order> list = schedule.getUpcomingOrders();
		for(Order o : list)
			if(o.CLIENT != user)
				list.remove(o);
		return list;
	}
	
	public List<Order> getFinishedOrdersForUser(String user) {
		List<Order> list = new LinkedList<Order>();
		for(Order o : finishedOrders)
			if(o.CLIENT == user)
				list.add(o);
		return list;
	}
	
	public AssemblyLine getAssemblyLine()
	{
		return this.assemblyLine;
	}
	
	public void advance(int time) {
		if(!this.assemblyLine.isReadyToAdvance()) {
			throw new IllegalStateException("assemblyLine is not ready to advance");
		}
		
		if(time <= 0) {
			throw new IllegalArgumentException("cannot advance to same or previous time");
		}
		
		// there are 2 basic scenarios:
		// scenario 1: the next order can be pushed on the assembly line today
		// scenario 2: the next order is for tomorrow however the time has to be
		//             increased and the assembly line pushed
		Order orderToPush = null;
		if(this.schedule.canNextOrderBeBuildToday()) {
			orderToPush = this.schedule.prepareNextOrder();
		}
		
		assemblyLine.advance(orderToPush);
		
		this.schedule.increaseDayTime(time);
		
		if(schedule.getCurrentTime().shouldBeFinished() &&
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
