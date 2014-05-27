package dao;

import java.util.List;

import vehicle.order.Order;

/**
 * Order DAO - abstraction to load persisted orders
 * 
 * @author jonathanlangens
 * 
 */
public interface OrderDAO {

	/**
	 * @return all orders that have been finished already on system startup.
	 */
	public List<Order> getAllFinishedOrders();

	/**
	 * @return All orders that are pending at the startup of the system.
	 */
	public List<Order> getAllPendingOrders();

	/**
	 * @return the day at which the system will start.
	 */
	public int getDay();
}
