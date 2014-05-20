package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import user.User;
import vehicle.order.ModelA;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import company.CMCSystem;
import dao.OrderDAOImpl;


/**
 * This class collects all unit tests for the CMCSystem class
 * 
 * @author Wonne Joosen
 *
 */
public class TestCMCSystem {

	/**
	 * Instance variables
	 */
	private CMCSystem cmcSystem;
	
	@Before
	public void initialize(){
		cmcSystem = new CMCSystem(new OrderDAOImpl());
		cmcSystem.logInUser(1);
	}
	
	/**
	 * Helper method for makeOrder()
	 * 
	 * @return An arbitrary OrderSpecification object
	 */
	public OrderBuilder makeOrderSpec(){
		OrderBuilder os = new ModelA();
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for(Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		return os;
	}
	
	/**
	 * Helper method to be able to quickly make an arbitrary order
	 * 
	 * @return An arbitrary order
	 */
	public Order makeOrder(){
		OrderBuilder spec = makeOrderSpec();
		Order order = new Order(spec);
		return order;
	}
	
	/**
	 * Tests the login method
	 */
	@Test
	public void testLogin(){
		cmcSystem.logInUser(1);
		String role = cmcSystem.getLoggedInUser().getRole();
		System.out.println(role);
		assertEquals(role,"GarageHolder");
		
		cmcSystem.logInUser(2);
		role = cmcSystem.getLoggedInUser().getRole();
		System.out.println(role);
		assertEquals(role,"Mechanic");
		
		cmcSystem.logInUser(3);
		role = cmcSystem.getLoggedInUser().getRole();
		System.out.println(role);
		assertEquals(role,"ShopHolder");
		
		cmcSystem.logInUser(0);
		role = cmcSystem.getLoggedInUser().getRole();
		System.out.println(role);
		assertEquals(role,"Manager");
		
	}

	/**
	 * Tests the ordering of a car via the schedule, and whether the order appears in the list of scheduled orders
	 */
	@Test
	public void testOrders(){
		cmcSystem.logInUser(1);
		User garageholder = cmcSystem.getLoggedInUser();
		List<Order> unfinishedOrders = cmcSystem.getScheduledOrdersForUser(garageholder);
		List<Order> finishedOrders = cmcSystem.getFinishedOrdersForUser(garageholder);
		assertTrue(unfinishedOrders.isEmpty());
		assertTrue(finishedOrders.isEmpty());
		OrderBuilder orderSpec = makeOrderSpec();
		Order order = new Order(orderSpec);
		cmcSystem.getScheduler().placeOrder(order);
		unfinishedOrders = cmcSystem.getScheduledOrdersForUser(garageholder);
		assertFalse(unfinishedOrders.isEmpty());
		assertTrue(finishedOrders.isEmpty());
	}
	
	/**
	 * Tests if all the finished orders for all users are returned in a proper way
	 */
	@Test
	public void testAllFinishedOrders(){
		testOrders();
		assertTrue(cmcSystem.getAllFinishedOrders().isEmpty());
	}

	/**
	 * Simple tester for the getters for assemblyLine and schedule
	 */
	@Test
	public void testGetters(){
		assertNotNull(cmcSystem.getAssemblyLine(0));
		assertNotNull(cmcSystem.getScheduler());

	}
	
	/**
	 * Tests whether orders for a wrong user are returned
	 */
	@Test
	public void testIllegalUser(){
		cmcSystem.logInUser(1);
		User garageholder = cmcSystem.getLoggedInUser();
		List<Order> unfinishedOrders = cmcSystem.getScheduledOrdersForUser(garageholder);
		List<Order> finishedOrders = cmcSystem.getFinishedOrdersForUser(garageholder);
		assertTrue(unfinishedOrders.isEmpty());
		assertTrue(finishedOrders.isEmpty());
		OrderBuilder orderSpec = makeOrderSpec();
		Order order = new Order(orderSpec);
		cmcSystem.getScheduler().placeOrder(order);
		unfinishedOrders = cmcSystem.getScheduledOrdersForUser(garageholder);
		assertFalse(unfinishedOrders.isEmpty());
		assertTrue(finishedOrders.isEmpty());
		User dummyUser = new User("dummy") {
			
			@Override
			public String getRole() {
				// TODO Auto-generated method stub
				return "Dummy";
			}
		};
		assertFalse(cmcSystem.getFinishedOrdersForUser(dummyUser).contains(order));
		assertFalse(cmcSystem.getScheduledOrdersForUser(dummyUser).contains(order));
		assertFalse(cmcSystem.getFinishedOrdersForUser(garageholder).contains(order));
		
		
	}
}
