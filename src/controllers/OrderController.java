package controllers;

import vehicle.order.Order;
import vehicle.order.OrderBuilder;

public class OrderController
{
	private SystemController systemController;
	
	/**
	 * constructor takes a systemcontroller 
	 * @param s
	 */
	public OrderController(SystemController s)
	{
		systemController = s;
	}
	
	/**
	 * allows the currently logged in user to place an order
	 * and returns the expected delivery date
	 * @param specification
	 * @return
	 */
	public int placeOrder(OrderBuilder specification)
	{
		Order order = new Order(specification,
								systemController.getLoggedInUser());
		systemController.getSchedule().placeOrder(order);
		
		return getExpectedDeliveryDayFor(order);
	}
	
	/**
	 * @param order
	 * @return the ETA for this order
	 */
	public int getExpectedDeliveryDayFor(Order order)
	{
		return systemController.getSchedule().getETA(order).getDay();
	}
}
