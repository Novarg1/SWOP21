package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.assemblylines.Assemblyline.Status;
import dao.OrderDAOImpl;

public class ScenarioManageAssemblyLineStatus {

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
		system.logInUser(0);
	}

	@Test
	public void test() {
		Assemblyline l = system.getAssemblyLine(0);
		l.setStatus(Status.MAINTENANCE, 0);
		assertEquals(Status.MAINTENANCE, l.getStatus());
	}

}
