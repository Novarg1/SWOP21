package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import vehicle.assemblytasks.Task;
import company.CMCSystem;
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
	private WorkstationController controller;
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		controller = new WorkstationController(system.getAssemblyLine(0).getWorkstations()[0]);
		// log in the garage holder
		system.logInUser(1);
	}


	@Test
	public void test() {
		Set<Task> tasks = controller.getTasksForWorkstation();
		for(Task t : tasks)
			controller.perform(t, 60);
		assertEquals(0, controller.getTasksForWorkstation().size());
	}

}
