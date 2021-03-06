package company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import company.assemblylines.Assemblyline;
import company.assemblylines.Line1;
import company.assemblylines.Line2;
import company.assemblylines.Line3;
import company.schedule.Scheduler;
import dao.OrderDAO;
import user.User;
import user.UserManager;
import vehicle.order.Order;

/**
 * this class represents the domains top level class it contains the
 * usermanager, the scheduler and the assembly line
 */
public class CMCSystem {

	private Scheduler scheduler;
	private UserManager userManager;

	/**
	 * default constructor
	 */
	public CMCSystem(OrderDAO dao) {
		userManager = new UserManager();
		Set<Assemblyline> assemblylines = new HashSet<>();
		assemblylines.add(new Line1((dao == null) ? 0 : dao.getDay()));
		assemblylines.add(new Line2((dao == null) ? 0 : dao.getDay()));
		assemblylines.add(new Line3((dao == null) ? 0 : dao.getDay()));
		if (dao == null) {
			scheduler = new Scheduler(assemblylines);
		} else {
			scheduler = new Scheduler(assemblylines, dao.getAllPendingOrders(),
					dao.getAllFinishedOrders());
		}
	}

	/**
	 * returns all the upcoming orders for a given user
	 * 
	 * @param user
	 * @return List<Order> l : all elements of l are ordered by this user and
	 *         not finished
	 */
	public List<Order> getScheduledOrdersForUser(User user) {
		List<Order> result = scheduler.getPendingOrders();
		Iterator<Order> it = result.iterator();
		while(it.hasNext()) {
			if(!it.next().getClient().getUserName().equals(user.getUserName())) {
				it.remove();
			}
		}
		return result;
	}

	/**
	 * returns all the finished orders for a given user
	 * 
	 * @param user
	 * @return List<Order> l : all elemenets of l are ordered by this user and
	 *         finished
	 */
	public List<Order> getFinishedOrdersForUser(User user) {
		List<Order> list = new LinkedList<Order>();
		for (Order o : scheduler.getFinishedOrders())
			if (o.getClient().getUserName() == user.getUserName())
				list.add(o);
		return list;
	}

	/**
	 * inspector for the scheduler
	 * 
	 * @return this.scheduler
	 */
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * this function logs user n in where n is that users position in the
	 * usermanager's list
	 * 
	 * @param n
	 *            - 1. Manager 2. GarageHolder 3. Mechanic 4. CustomShopOwner
	 */
	public void logInUser(int n) {
		this.userManager.logInUser(n);
	}

	/**
	 * returns the user that is currently logged in
	 * 
	 * @return the user that is now logged in
	 */
	public User getLoggedInUser() {
		return this.userManager.getLoggedInUser();
	}

	/**
	 * inspector for finishedOrders
	 * 
	 * @return this.finishedOrders
	 */
	public List<Order> getAllFinishedOrders() {
		return scheduler.getFinishedOrders();
	}

	/**
	 * @param n
	 * @return the assembly line at position n
	 */
	public Assemblyline getAssemblyLine(int n) {
		
		return (Assemblyline) scheduler.getAssemblyLines().toArray()[n];
	}
}
