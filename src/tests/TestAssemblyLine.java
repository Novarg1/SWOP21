package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.User;
import vehicle.order.ModelA;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import company.AssemblyLine;
import company.CMCSystem;
import company.schedule.Scheduler;
import company.workstations.Workstation;

/**
 * This is a class that collects unit tests for the AssemblyLine class
 * 
 * @author Wonne Joosen
 *
 */
public class TestAssemblyLine {
	
	/**
	 * Instance variables that may
	 * change during individual tests.
	 */
	private Scheduler schedule;
	private AssemblyLine assemblyLine;	
	private CMCSystem cmcSystem;

	/**
	 * Class variables that do not
	 * change during the entire test case. 
	 */



	/**
	 * Set up a mutable test fixture.
	 * 
	 *  @post an assembly line is created
	 */
	@Before
	public void setUpMutableFixture() {
		cmcSystem = new CMCSystem();
		schedule = cmcSystem.getScheduler();
		assemblyLine = cmcSystem.getAssemblyLine();

	}

	/**
	 * Set up an immutable test fixture.
	 * 
	 * @post
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
		User user = new User("dummyGarageHolder") {
			
			@Override
			public String getRole() {
				// TODO Auto-generated method stub
				return "GarageHolder";
			}
		};
		OrderBuilder spec = makeOrderSpec();
		Order order = new Order(spec, user);
		return order;
	}

	/**
	 * Tests whether an assembly line with empty workstations is ready
	 */
	@Test
	public void emptyAssemblyLineTest() {
		for(Workstation ws : assemblyLine.getWorkstations() ) {
			assertTrue(ws.isReady());
		}
		assertTrue(assemblyLine.isReadyToAdvance());
	}
	
	/**
	 * Test whether the assembly line can properly be advanced
	 */
	@Test
	public void advanceAssemblyLineTest(){
		Order order1 = makeOrder();
		Order order2 = makeOrder();
		Order order3 = makeOrder();
		schedule.placeOrder(order1);
		schedule.placeOrder(order2);
		schedule.placeOrder(order3);
		assertTrue(assemblyLine.getWorkstations()[0].isReady());
	}
}