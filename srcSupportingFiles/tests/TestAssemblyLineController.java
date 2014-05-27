package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.assemblylines.Assemblyline.Status;
import controllers.AssemblyLineController;
import dao.OrderDAOImpl;

public class TestAssemblyLineController {
	/**
	 * variables that may change during the tests
	 */
	
	/**
	 * variable that must remain immutable during tests
	 */
	private CMCSystem system = new CMCSystem(new OrderDAOImpl());
	private AssemblyLineController controller;
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		// log in the garage holder
		system.logInUser(1);
		
		// set up the assembly line
		Assemblyline line = system.getAssemblyLine(0);
		
		// set up the assembly line controller
		controller = new AssemblyLineController(line);
	}

	@Test
	public void test() {
		// set the status of the assembly line to broken
		controller.setStatus(Status.BROKEN, 1);
		
		// verify
		assertEquals(Status.BROKEN, controller.getStatus());
		
		// set the status of the assembly line to operational
		controller.setStatus(Status.OPERATIONAL, 50);
		
		// verify
		assertEquals(Status.OPERATIONAL, controller.getStatus());
	}

}
