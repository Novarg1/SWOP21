package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import user.User;
import vehicle.order.Order;
import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.workstations.Workstation;
import controllers.SystemController;
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
	private SystemController controller = new SystemController(system);
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		// log in the shop holder
		controller.logInUser(1);
	}
	
	private boolean checkScheduledOrders(List<Order> orders, User user)
	{
		int ndao = 0;
		int ncon = 0;
		for(Order order : orders)
			if(order.getClient().getUserName() == user.getUserName())
				++ndao;
		for(Order order : dao.getAllPendingOrders())
			if(order.getClient().getUserName() == user.getUserName())
				++ncon;
		
		return ndao == ncon;
	}
	
	private boolean checkFinishedOrders(List<Order> orders, User user)
	{
		int ndao = 0;
		int ncon = 0;
		for(Order order : orders)
			if(order.getClient().getUserName() == user.getUserName())
				++ndao;
		for(Order order : dao.getAllFinishedOrders())
			if(order.getClient().getUserName() == user.getUserName())
				++ncon;
		
		return ndao == ncon;
	}

	@Test
	public void test() {
		User user = controller.getLoggedInUser();
		
		// assert the shop holder was logged in
		assertEquals("GarageHolder", user.getRole());
		
		// check the scheduled orders
		assertTrue(checkScheduledOrders(controller.getScheduledOrdersFor(user), user));
		
		// check the finished orders
		assertTrue(checkFinishedOrders(controller.getFinishedOrdersFor(user), user));
		
		// check all finished orders
		assertEquals(dao.getAllFinishedOrders().size(), controller.getAllFinishedOrders().size());
		
		// check the scheduler
		assertEquals(system.getScheduler(), controller.getScheduler());
		
		// check the workstations access
		assertEquals(((Assemblyline)system.getScheduler().getAssemblyLines().toArray()[0]).getWorkstations()[0],
						controller.getWorkstationsForAssemblyLine(0).get(0));
		
		// check the assembly lines check out
		assertEquals(system.getAssemblyLine(0).getCurrentTime().getDay(),
						controller.getAssemblyLine(0).getCurrentTime().getDay());
		
		// check the correct workstation gets looked up
		Workstation w = ((Assemblyline)system.getScheduler().getAssemblyLines().toArray()[0]).getWorkstations()[0];
		assertEquals(w, controller.selectWorkstationWithId(0, 0));
	}

	@Test
	public void constructorTest(){
		SystemController sysctrl = new SystemController(system);
		assertTrue(sysctrl.getScheduler().equals(system.getScheduler()));
		SystemController sysctrl2 = new SystemController();
		assertTrue(sysctrl2.getAllFinishedOrders().isEmpty());
	}
}
