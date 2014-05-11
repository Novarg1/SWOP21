package dao;

import java.util.List;

import vehicle.order.Order;

/**
 * Order DAO - abstraction to load persisted orders
 * @author jonathanlangens
 *
 */

public interface OrderDAO 
{
	public List<Order> getAllFinishedOrders();
	public List<Order> getAllPendingOrders();
}
