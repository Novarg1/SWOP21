package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import company.CMCSystem;
import dao.OrderDAOImpl;
import vehicle.assemblytasks.Task;
import vehicle.order.ModelY;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;

/**
 * This is a class that collects unit tests for the Car class
 * 
 * @author Wonne Joosen
 *
 */
public class TestCar {
	private CMCSystem system = new CMCSystem(new OrderDAOImpl());
	private Order testOrder;
	
	@Before
	public void initialize() {
		system.logInUser(1);
		testOrder = makeOrder();
	}

	
	/**
	 * Helper method to create an example OrderSpecification object
	 * 
	 * @return A valid OrderSpecification, with arbitrary car parts specified
	 */
	public OrderBuilder makeOrderSpec(){
		OrderBuilder os = new ModelY();
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for(Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		os.setClient(system.getLoggedInUser());
		return os;
	}
	
	/**
	 * Helper method to make an order object
	 * @return A Order object with some OrderSpecification
	 */
	public Order makeOrder(){
		OrderBuilder spec = makeOrderSpec();
		Order order = new Order(spec);
		return order;
	}
	
	private boolean allTasksPerformed(Set<Task> tasks)
	{
		for(Task t : tasks)
			if(t.isPerformed()==false)
				return false;
		return true;
	}

	/**
	 * Tester for the install() method 
	 * Also checks whether ,after installing all parts, the Car matches the Order that was passed as an argument for its constructor
	 */
	@Test
	public void installTest(){
		for(Task task : testOrder.getTasks())
		{
			task.perform();
		}
		assertTrue(allTasksPerformed(testOrder.getTasks()));
	}
	
}	