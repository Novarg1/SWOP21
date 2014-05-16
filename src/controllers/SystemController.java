package controllers;

import java.util.ArrayList;
import java.util.List;

import user.User;
import vehicle.order.Order;
import company.CMCSystem;
import company.schedule.Scheduler;
import company.workstations.Workstation;

/**
 * System Controller
 * 
 */

public class SystemController {
	private CMCSystem cmcSytem;

	/**
	 * constructor initializes the system
	 */
	public SystemController() {
		this.cmcSytem = new CMCSystem();
	}

	/**
	 * @return the user that is currently logged in
	 */
	public User getLoggedInUser() {
		return cmcSytem.getLoggedInUser();
	}

	/**
	 * logs user n into the system
	 * 
	 * @param n
	 */
	public void logInUser(int n) {
		cmcSytem.logInUser(n);
	}

//	/**
//	 * @return true if the assembly line can advance
//	 */
//	public boolean canAdvance() {
//		AssemblyLine a = cmcSytem.getAssemblyLine();
//		return a.isReadyToAdvance();
//	}

	/**
	 * @param user
	 * @return the unfinished orders for the passed user
	 */
	public List<Order> getScheduledOrdersFor(User user) {
		return cmcSytem.getScheduledOrdersForUser(user);
	}

	/**
	 * @param user
	 * @return the finished orders for the passed user
	 */
	public List<Order> getFinishedOrdersFor(User user) {
		return cmcSytem.getFinishedOrdersForUser(user);
	}

//	/**
//	 * @param id
//	 * @return true if workstation with ID id has no pending tasks left
//	 */
//	public boolean isWorkPostFinished(int id) {
//		for (Workstation w : cmcSytem.getAssemblyLine().getWorkstations()) {
//			if (w.getId() == id)
//				return w.isReady();
//		}
//		return false;
//	}

	/**
	 * @return the schedule
	 */
	public Scheduler getScheduler() {
		return cmcSytem.getScheduler();
	}
	
	/**
	 * @param n
	 * @return all workstations on assembly line n in a list
	 */
	
	public List<Workstation> getWorkstationsForAssemblyLine(int n)
	{
		List<Workstation> list = new ArrayList<Workstation>();
		
		for(Workstation w : this.cmcSytem.getAssemblyLine(n).getWorkstations())
		{
			list.add(w);
		}
		
		return list;
	}
	
	public Workstation selectWorkstationWithId(int assemblyLine, int id)
	{
		return (this.getWorkstationsForAssemblyLine(assemblyLine)).get(id);
	}

	/**
	 * @return all orders that were finished by this companys
	 */
	public List<Order> getAllFinishedOrders() {
		return cmcSytem.getAllFinishedOrders();
	}
}
