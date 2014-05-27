package controllers;

import java.util.ArrayList;
import java.util.List;

import user.User;
import vehicle.order.Order;
import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.schedule.Scheduler;
import company.workstations.Workstation;
import dao.OrderDAOImpl;

/**
 * System Controller - provides all basic functionalities
 * 
 */
public class SystemController {
	
	private CMCSystem cmcSytem;

	/**
	 * constructor initializes the system
	 */
	public SystemController() {
		this.cmcSytem = new CMCSystem(new OrderDAOImpl());
	}
	
	public SystemController(CMCSystem system)
	{
		this.cmcSytem = system;
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

	public List<Workstation> getWorkstationsForAssemblyLine(int n) {
		List<Workstation> list = new ArrayList<Workstation>();
		
		for (Workstation w : this.cmcSytem.getAssemblyLine(n).getWorkstations()) {
			list.add(w);
		}

		return list;
	}

	/**
	 * @param assemblyLine
	 * @param id
	 * @return the workstation with id 'id' on assembly line 'assemblyLine'
	 */
	public Workstation selectWorkstationWithId(int assemblyLine, int id) {
		return (this.getWorkstationsForAssemblyLine(assemblyLine)).get(id);
	}

	/**
	 * @param n
	 * @return the nth assembly line
	 */
	public Assemblyline getAssemblyLine(int n) {
		return cmcSytem.getAssemblyLine(n);
	}

	/**
	 * @return all orders that were finished by this companys
	 */
	public List<Order> getAllFinishedOrders() {
		return cmcSytem.getAllFinishedOrders();
	}
}
