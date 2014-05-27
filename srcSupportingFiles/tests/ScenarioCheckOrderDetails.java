package tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vehicle.order.Order;
import company.CMCSystem;
import dao.OrderDAOImpl;

public class ScenarioCheckOrderDetails {

	/**
	 * variables that may change during the tests
	 */
	
	/**
	 * variable that must remain immutable during tests
	 */
	private CMCSystem system = new CMCSystem(new OrderDAOImpl());
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		// log in the garage holder
		system.logInUser(1);
	}

	@Test
	public void test() {
		// assign all the orders for this user to orders
		List<Order> orders = system.getFinishedOrdersForUser(system.getLoggedInUser());
		
		// there are 6 orders for this user in the database
		assert(orders.size()==6);
	}

}
