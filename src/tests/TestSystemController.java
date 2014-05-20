package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import user.User;
import vehicle.order.Order;
import company.CMCSystem;
import dao.OrderDAO;
import dao.OrderDAOImpl;

public class TestSystemController {
	/**
	 * variables that may change during the tests
	 */
	
	/**
	 * variable that must remain immutable during tests
	 */
	private OrderDAO dao = new OrderDAOImpl();
	private CMCSystem system = new CMCSystem(dao);
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		// log in the garage holder
		system.logInUser(1);
	}
	
	private boolean checkScheduledOrders(List<Order> orders, User user)
	{
		for(Order order : dao.getAllPendingOrders())
			if(order.getClient() == user)
			{
				boolean checked = false;

				for(Order userOrder : orders)
					if(userOrder == order)
						checked = true;
				
				if(checked == false)
					return false;
			}
		return true;
	}
	
	private boolean checkFinishedOrders(List<Order> orders, User user)
	{
		for(Order order : dao.getAllFinishedOrders())
			if(order.getClient() == user)
			{
				boolean checked = false;

				for(Order userOrder : orders)
					if(userOrder == order)
						checked = true;
				
				if(checked == false)
					return false;
			}
		return true;
	}

	@Test
	public void test() {
		// check the log in case
		system.logInUser(3);

		User user = system.getLoggedInUser();
		
		// assert the shop holder was logged in
		assertEquals("ShopHolder", user.getRole());
		
		// check the scheduled orders
		assert(checkScheduledOrders(system.getScheduledOrdersForUser(user), user));
		
		// check the finished orders
		assert(checkFinishedOrders(system.getFinishedOrdersForUser(user), user));
		
		// check all finished orders
		assertEquals(dao.getAllFinishedOrders().size(), system.getAllFinishedOrders().size());
	}

}
