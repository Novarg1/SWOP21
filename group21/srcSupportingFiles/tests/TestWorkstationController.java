package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import vehicle.assemblytasks.Task;
import vehicle.order.ModelC;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import company.CMCSystem;
import company.workstations.Workstation;
import dao.OrderDAOImpl;
import controllers.WorkstationController;

public class TestWorkstationController {
	/**
	 * variables that may change during the tests
	 */
	
	/**
	 * variable that must remain immutable during tests
	 */
	private CMCSystem system = new CMCSystem(new OrderDAOImpl());
	private Workstation workstation;
	private WorkstationController controller;
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		controller = new WorkstationController(system.getAssemblyLine(0).getWorkstations()[0]);
		workstation = system.getAssemblyLine(0).getWorkstations()[0];
		// log in the garage holder
		system.logInUser(1);
	}

	/**
	 * Helper method to create an {@link Order} object
	 * @param os
	 * @return
	 */
	public Order makeOrder(OrderBuilder os){
		OrderBuilder spec = makeOrderSpec(os);
		Order order = new Order(spec);
		return order;
	}
	
	/**
	 * Helper method to create an order specification
	 * @param os
	 * @return an order specification as a {@link OrderBuilder}
	 */
	public OrderBuilder makeOrderSpec(OrderBuilder os){
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for(Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		os.setClient(system.getLoggedInUser());
		return os;
	}

	@Test
	public void test() {
		Set<Task> tasks = controller.getTasksForWorkstation();
		for(Task t : tasks)
			controller.perform(t, 60);
		assertEquals(0, controller.getTasksForWorkstation().size());
	}
	
	/**
	 * Tests the performTask() method
	 */
	@Test
	public void performTaskTest(){
		system.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		workstation.setOrder(order1);
		Set<Task> pendingTasks = workstation.getPendingTasks();
		Task task = null;
		for(Task t : pendingTasks)
			task = t;
		controller.perform(task,1);
		assertTrue(task.isPerformed());
	}
	
	

}
