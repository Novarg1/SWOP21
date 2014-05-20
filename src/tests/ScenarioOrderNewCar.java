package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import user.User;
import vehicle.order.ModelA;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import vehicle.parts.PartsSet;
import company.CMCSystem;
import dao.OrderDAOImpl;


/**
 * A class collecting scenario tests for the OrderNewCar use case
 * 
 * @author Wonne Joosen
 *
 */
public class ScenarioOrderNewCar {
	
	/**
	 * Instance variables that may change during individual tests
	 */
	

	/**
	 * Class variables that do not change during individual tests
	 * 
	 */
	private CMCSystem cmcSystem = new CMCSystem(new OrderDAOImpl());
	
	/**
	 * Method for setting up an immutable fixture
	 */
	@BeforeClass
	public static void setUpImmutableFixture() {
	
	}
	
	public OrderBuilder makeOrderSpec(){
		OrderBuilder os = new ModelA();
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for(Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		return os;
	}
	
	public Order makeOrder(){
		cmcSystem.logInUser(1);
		OrderBuilder spec = makeOrderSpec();
		Order order = new Order(spec);
		return order;
	}

	
	/**
	 * Test for the main success scenario
	 */
	@Test
	public void orderNewCar_MainSuccesScenario() {
		// log in as a garage holder
		cmcSystem.logInUser(1);
		User currentUser = cmcSystem.getLoggedInUser();
		List<Order> unfinishedOrders = cmcSystem.getScheduledOrdersForUser(currentUser);
		List<Order> finishedOrders = cmcSystem.getFinishedOrdersForUser(currentUser);
		assertFalse(unfinishedOrders.isEmpty());
		assertTrue(finishedOrders.isEmpty());
		int nUnfinishedOrders = unfinishedOrders.size();
		OrderBuilder orderSpec = makeOrderSpec();
		orderSpec.setClient(currentUser);
		Order order = new Order(orderSpec);
		cmcSystem.getScheduler().placeOrder(order);
		unfinishedOrders = cmcSystem.getScheduledOrdersForUser(currentUser);
		assertTrue(unfinishedOrders.size() > nUnfinishedOrders);
	}

	// will make different test scenario where all tasks on a assembly line are
	// performed 
//	@Test
//	public void advanceAssemblyLine() {
//		orderNewCar_MainSuccesScenario();
//		PartsSet cpSet = cmcSystem.getAssemblyLine().getWorkstations()[0].getPendingTasks();
//		for (Part cp : cpSet){
//			cmcSystem.getAssemblyLine().getWorkstations()[0].install(cp, 1);
//		}
//		assertTrue(cmcSystem.getAssemblyLine().getWorkstations()[0].isReady());
//	}

}
