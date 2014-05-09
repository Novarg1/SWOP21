package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import user.User;
import vehicle.order.ModelASpec;
import vehicle.order.Order;
import vehicle.order.OrderSpecification;
import vehicle.parts.Carpart;
import vehicle.parts.CarpartsSet;
import company.CMCSystem;


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
	private CMCSystem cmcSystem = new CMCSystem();
	
	/**
	 * Method for setting up an immutable fixture
	 */
	@BeforeClass
	public static void setUpImmutableFixture() {

	}
	
	public OrderSpecification makeOrderSpec(){
		OrderSpecification os = new ModelASpec();
		for (Class<? extends Carpart> type : os.getSupportedTypes()) {
			for(Carpart part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		return os;
	}
	
	public Order makeOrder(){
		User user = new User("dummyGarageHolder") {
			
			@Override
			public String getRole() {
				// TODO Auto-generated method stub
				return "GarageHolder";
			}
		};
		OrderSpecification spec = makeOrderSpec();
		Order order = new Order(spec, user);
		return order;
	}

	
	/**
	 * Test for the main success scenario
	 */
	@Test
	public void orderNewCar_MainSuccesScenario() {
		// log in as a garage holder
		cmcSystem.logInUser(2);
		User currentUser = cmcSystem.getLoggedInUser();
		List<Order> unfinishedOrders = cmcSystem.getScheduledOrdersForUser(currentUser);
		List<Order> finishedOrders = cmcSystem.getFinishedOrdersForUser(currentUser);
		assertTrue(unfinishedOrders.isEmpty());
		assertTrue(finishedOrders.isEmpty());
		OrderSpecification orderSpec = makeOrderSpec();
		Order order = new Order(orderSpec,currentUser);
		cmcSystem.getSchedule().placeOrder(order);
		unfinishedOrders = cmcSystem.getScheduledOrdersForUser(currentUser);
		assertFalse(unfinishedOrders.isEmpty());
	}
	
	@Test
	public void advanceAssemblyLine() {
		orderNewCar_MainSuccesScenario();
		CarpartsSet cpSet = cmcSystem.getAssemblyLine().getWorkstations()[0].getPendingTasks();
		for (Carpart cp : cpSet){
			cmcSystem.getAssemblyLine().getWorkstations()[0].install(cp, 1);
		}
		assertTrue(cmcSystem.getAssemblyLine().getWorkstations()[0].isReady());
	}

}
