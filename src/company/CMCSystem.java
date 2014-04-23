package company;

import java.util.LinkedList;
import java.util.List;

import user.User;
import user.UserManager;
import car.Order;

/**
 * this class represents the domains top level class
 * it contains the usermanager, the scheduler and the
 * assembly line
 * 
 * @author jonathanlangens
 *
 */
public class CMCSystem 
{
	private Schedule schedule;
	private AssemblyLine assemblyLine;
	private UserManager userManager;
	private List<Order> finishedOrders;
	
	/**
	 * default constructor
	 */
	public CMCSystem() {
		schedule = new Schedule();
		assemblyLine = new AssemblyLine(schedule);
		finishedOrders = new LinkedList<Order>();
		userManager = new UserManager();
	}
	
	/**
	 * returns all the upcoming orders for a given user
	 * 
	 * @param user
	 * @return List<Order> l : all elements of l are ordered by this user and not finished
	 */
	public List<Order> getScheduledOrdersForUser(User user) {
		List<Order> list = schedule.getPendingOrders();
		for(Order o : list)
			if(o.getClient() != user)
				list.remove(o);
		return list;
	}
	
	/**
	 * returns all the finished orders for a given user
	 * 
	 * @param user
	 * @return List<Order> l : all elemenets of l are ordered by this user and finished
	 */
	public List<Order> getFinishedOrdersForUser(User user) {
		List<Order> list = new LinkedList<Order>();
		for(Order o : finishedOrders)
			if(o.getClient() == user)
				list.add(o);
		return list;
	}
	
	/**
	 * inspector for the assembly line
	 * @return this.assemblyLine
	 */
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
//		Order orderToPush = null;
//		if(this.schedule.canNextOrderBeBuildToday()) {
//			orderToPush = this.schedule.prepareNextOrder();
//		}
//		
//		assemblyLine.advance(orderToPush);
//		
//		this.schedule.increaseDayTime(time);
//		
//		if(schedule.getCurrentTime().shouldBeFinished() &&
//				assemblyLine.isEmpty())
//		{
//			schedule.startNewDay();
//		}
	}
	
	/**
	 * inspector for the schedule
	 * @return this.schedule
	 */
	public Schedule getSchedule()
	{
		return this.schedule;
	}
	
	/**
	 * this function logs user n in where n is that users position
	 * in the usermanager's list
	 * @param n
	 */
	public void logInUser(int n)
	{
		this.userManager.logInUser(n);
	}
	
	/**
	 * returns the user that is currently logged in
	 * @return the user that is now logged in
	 */
	public User getLoggedInUser()
	{
		return this.userManager.getLoggedInUser();
	}
	
	/**
	 * inspector for finishedOrders
	 * @return this.finishedOrders
	 */
	public List<Order> getAllFinishedOrders()
	{
		return finishedOrders;
	}
}
