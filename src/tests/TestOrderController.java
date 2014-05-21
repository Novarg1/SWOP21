package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vehicle.order.ModelA;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import company.CMCSystem;
import controllers.OrderController;
import controllers.SystemController;
import dao.OrderDAOImpl;

public class TestOrderController {
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
	
	public OrderBuilder makeOrderSpec() {
		OrderBuilder os = new ModelA();
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for (Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		os.setClient(system.getLoggedInUser());
		return os;
	}

	@Test
	public void test() {
		OrderController c = new OrderController(new SystemController(system));
		
		int prev = system.getScheduledOrdersForUser(system.getLoggedInUser()).size();
		
		int expect = c.placeOrder(makeOrderSpec());
		
		int next = system.getScheduledOrdersForUser(system.getLoggedInUser()).size();
		
		assertTrue(1 == (next - prev));
		
		List<Order> orders = system.getScheduledOrdersForUser(system.getLoggedInUser());
		
		Order last = orders.get(orders.size()-1);
		
		int expectL = c.getExpectedDeliveryDayFor(last);
		
		assertTrue(expect == expectL);
	}

}
