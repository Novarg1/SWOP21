package controllers;

import java.util.ArrayList;
import java.util.List;

import user.User;
import vehicle.order.Order;
import company.AssemblyLine;
import company.CMCSystem;
import company.Schedule;
import company.WorkStation;

/** System Controller
 * 
 */

public class SystemController 
{	
	private CMCSystem cmcSytem;
	
	/**
	 * constructor initializes the system
	 */
	public SystemController()
	{
		this.cmcSytem = new CMCSystem();
	}

	/**
	 * @return the user that is currently logged in
	 */
	public User getLoggedInUser()
	{
		return cmcSytem.getLoggedInUser();
	}
	
	/**
	 * logs user n into the system
	 * @param n
	 */
	public void logInUser(int n)
	{
		cmcSytem.logInUser(n);
	}
	
	/**
	 * @return true if the assembly line can advance
	 */
	public boolean canAdvance()
	{
		AssemblyLine a = cmcSytem.getAssemblyLine();
		return a.isReadyToAdvance();
	}
	
	/**
	 * @param user
	 * @return the unfinished orders for the passed user
	 */
	public List<Order> getScheduledOrdersFor(User user)
	{
		return cmcSytem.getScheduledOrdersForUser(user);
	}
	
	/**
	 * @param user
	 * @return the finished orders for the passed user
	 */
	public List<Order> getFinishedOrdersFor(User user)
	{
		return cmcSytem.getFinishedOrdersForUser(user);
	}
	
	/**
	 * @param id
	 * @return true if workstation with ID id has no pending tasks left
	 */
	public boolean isWorkPostFinished(int id)
	{
		for(WorkStation w : cmcSytem.getAssemblyLine().getWorkstations())
		{
			if(w.getId() == id)
				return w.isReady();
		}
		return false;
	}
	
	/**
	 * @return the schedule
	 */
	public Schedule getSchedule()
	{
		return cmcSytem.getSchedule();
	}
	
	/**
	 * @return all workstations in a list
	 */
	public List<WorkStation> getWorkstations()
	{
		List<WorkStation> list = new ArrayList<WorkStation>();
		for(WorkStation w : this.cmcSytem.getAssemblyLine().getWorkstations())
		{
			list.add(w);
		}
		return list;
	}
	
	/**
	 * @param id
	 * @return the workstation with ID id
	 */
	public WorkStation getWorkstation(int id)
	{
		for(WorkStation w : this.cmcSytem.getAssemblyLine().getWorkstations())
			if(w.getId() == id)
				return w;
		return null;
	}
	
	/**
	 * @return all orders that were finished by this companys
	 */
	public List<Order> getAllFinishedOrders()
	{
		return cmcSytem.getAllFinishedOrders();
	}
}
