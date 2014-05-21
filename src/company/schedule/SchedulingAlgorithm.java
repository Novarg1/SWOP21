package company.schedule;

import java.util.List;
import java.util.Set;

import company.workstations.Workstation;
import vehicle.order.Order;

public abstract class SchedulingAlgorithm 
{
	// the order list is a pointer to the original orderlist in the scheduler
	protected List<Order> orderList;
	
	// the sorted order list is the list maintained by the scheduling algorithm
	protected List<Order> sortedOrderList;
	
	/**
	 * default constructor takes an original list to work with into account
	 * @param originalList
	 */
	public SchedulingAlgorithm(List<Order> originalList)
	{
		orderList = originalList;
		recalculate();
	}
	
	/**
	 * addOrder adds an order to the original list and recalculates the sorted list
	 * @param o
	 */
	public void addOrder(Order o)
	{
		orderList.add(o);
		this.recalculate();
	}
	
	/**
	 * pop first order removes the head from the sorted orderlist and 
	 * the corresponding order from the original order list
	 * @return the first order on the sorted list
	 */
	public Order popFirstOrder()
	{
		if(sortedOrderList.size()==0)
			return null;
		
		Order first = sortedOrderList.remove(0);
		orderList.remove(first);
		
		return first;
	}
	
	/**
	 * returns the ETA day count for the passed order taking the current time
	 * and the number of assembly lines into account
	 * 
	 * the ETA day count is the number of days after today (0 = today) that
	 * we expect the order to be build
	 * 
	 * @param order
	 * @param currentTime
	 * @param nAssemblyLines
	 * @return
	 */
	public int getETA(Order order, int currentTime, int nAssemblyLines)
	{
		int timeleftToday = ((17*60)-((int)currentTime/60))-3;
		if(timeleftToday < 0)
			timeleftToday = 0;
		int nCarsToBeBuildToday = timeleftToday * nAssemblyLines;
		int nPos = sortedOrderList.indexOf(order);
		if(nPos<nCarsToBeBuildToday)
			return 0;
		else
		{
			nPos -= nCarsToBeBuildToday;
			nPos /= (14 * nAssemblyLines);
			return nPos;
		}
	}
	
	/**
	 * takes into account that at the start of the day we want to give
	 * precedence to small orders
	 * @return
	 */
	public Order getNextOrderStartOfDay(Set<Class<? extends Workstation>> availableWorkstations)
	{
		for(Order o : sortedOrderList)
			if(canBeBuildOn(o, availableWorkstations))
				return popOrder(o);
		return null;
	}
	
	/**
	 * returns the next order to be build
	 * @param availableWorkstations
	 * @return
	 */
	public Order getNextOrder(Set<Class<? extends Workstation>> availableWorkstations)
	{
		for(Order o : sortedOrderList)
			if(canBeBuildOn(o, availableWorkstations))
				return popOrder(o);
		return null;
	}
	
	/**
	 * returns the last order to be build on the following workstation today
	 * @param availableWorkstations
	 * @return
	 */
	public Order getNextOrderEndOfDay(Set<Class<? extends Workstation>> availableWorkstations)
	{
		for(Order o : sortedOrderList)
			if(canBeBuildOn(o, availableWorkstations))
				return popOrder(o);
		return null;
	}
	
	/**
	 * removes the order o from both the orderList and the sortedOrderList
	 * @param o
	 * @return
	 */
	private Order popOrder(Order o)
	{
		orderList.remove(o);
		sortedOrderList.remove(o);
		return o;
	}
	
	/**
	 * @param o
	 * @param availableWorkstations
	 * @return true if the order can be build on the available workstations
	 */
	private boolean canBeBuildOn(Order o, Set<Class<? extends Workstation>> availableWorkstations)
	{
		for(Class<? extends Workstation> w : o.getNeededWorkstations())
		{
			if(availableWorkstations.contains(w) == false)
				return false;
		}
		return true;
	}
	
	/**
	 * has to be overridden by all children of scheduling algorithm
	 * here the different algorithms get to sort the sorted list by 
	 * their heuristic
	 */
	public abstract void recalculate();
}
