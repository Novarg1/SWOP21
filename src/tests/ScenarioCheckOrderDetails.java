package tests;

import java.util.List;

import org.junit.Test;

import user.User;
import vehicle.order.Order;
import company.CMCSystem;
import dao.OrderDAOImpl;

/**
 * A class to test all the check order details related scenarios
 * @author jonathanlangens
 *
 */
public class ScenarioCheckOrderDetails 
{
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
	private void reset()
	{
		// log in the garage holder
		system.logInUser(1);
	}

	/**
	 * tests for ...    ... the main succes scenario
	 */
	@Test
	private void checkCorrectOrderRetrieval()
	{
		// reset all variables
		reset();
		
		// assign all the orders for this user to orders
		List<Order> orders = system.getFinishedOrdersForUser(system.getLoggedInUser());
		
		// there are 6 orders for this user in the database
		assert(orders.size()==6);
	}
}
