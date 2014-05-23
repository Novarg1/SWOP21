package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.order.PaintJob;
import vehicle.parts.Color;
import company.CMCSystem;
import dao.OrderDAOImpl;

public class ScenarioOrderSingleTask {
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
		system.logInUser(3);
	}
	
	private Order singleTask()
	{
		OrderBuilder spec = new PaintJob();
		spec.add(Color.BLUE);
		spec.setClient(system.getLoggedInUser());
		return new Order(spec);	
	}
	
	private boolean checkOrderHasBeenPlaced(Order o)
	{
		List<Order> orders = system.getScheduledOrdersForUser(system.getLoggedInUser());
		boolean placed = false;
		for(Order order : orders)
			if(order == o)
				placed = true;
		return placed;
	}
	
	private boolean checkExpectedProductionTimeForOrder(Order o)
	{
		return (system.getScheduler().getETA(o).getDay() >= 0);
	}

	@Test
	public void test() {
		Order task = singleTask();
		
		system.getScheduler().placeOrder(task);
		
		assertTrue(checkOrderHasBeenPlaced(task));
//		assertTrue(checkExpectedProductionTimeForOrder(task));
	}

}
