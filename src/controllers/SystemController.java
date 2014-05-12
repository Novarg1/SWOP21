package controllers;

import java.util.List;

import user.User;
import vehicle.order.Order;
import company.CMCSystem;
import company.schedule.Scheduler;

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

//	/**
//	 * @return all workstations in a list
//	 */
//	public List<Workstation> getWorkstations() {
//		List<Workstation> list = new ArrayList<Workstation>();
//		for (Workstation w : this.cmcSytem.getAssemblyLine().getWorkstations()) {
//			list.add(w);
//		}
//		return list;
//	}

	/**
	 * @return all orders that were finished by this companys
	 */
	public List<Order> getAllFinishedOrders() {
		return cmcSytem.getAllFinishedOrders();
	}
}
