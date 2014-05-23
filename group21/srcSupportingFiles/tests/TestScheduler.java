package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import user.User;
import vehicle.order.ModelA;
import vehicle.order.ModelB;
import vehicle.order.ModelC;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;

import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.schedule.FIFO;
import company.schedule.Scheduler;
import company.schedule.SpecificationBatch;
import dao.OrderDAOImpl;

/**
 * This is a class that collects unit tests for the {@link Scheduler} class
 * 
 * TODO: krijg coverage niet hoger, er zitten 2 private methoden in Scheduler die nergens gebruikt worden?
 * 		 nextIsTomorrow nog niet geimplementeerd
 * 		 geen idee hoe ik die update(..) moet testen
 * @author Wonne Joosen
 *
 */
public class TestScheduler {
	
	private Scheduler scheduler;
	private CMCSystem cmcSystem;
	private Assemblyline assemblyLine;
	private Set<Assemblyline> assemblyLines;
	
	
	/**
	 * Set up a mutable test fixture.
	 * 
	 *  @post an assembly line is created
	 */
	@Before
	public void setUpMutableFixture() {
		cmcSystem = new CMCSystem(new OrderDAOImpl());
		scheduler = cmcSystem.getScheduler();
		assemblyLine = cmcSystem.getAssemblyLine(0);
		assemblyLines = new HashSet<Assemblyline>();
		assemblyLines.add(cmcSystem.getAssemblyLine(0));
		assemblyLines.add(cmcSystem.getAssemblyLine(1));
		assemblyLines.add(cmcSystem.getAssemblyLine(2));
	}
	
	public OrderBuilder makeOrderSpec(OrderBuilder os){
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for(Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		os.setClient(cmcSystem.getLoggedInUser());
		return os;
	}
	
	public Order makeOrder(OrderBuilder os){
		OrderBuilder spec = makeOrderSpec(os);
		Order order = new Order(spec);
		return order;
	}
	
	@Test
	public void outOfOrdersTest_9FakeLeft(){
		assertEquals(9,scheduler.getPendingOrders().size()); //has 9 fake orders from orderDAOImpl
		assertFalse(scheduler.isOutOfOrders());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorNullArgumentTest(){
		Scheduler dummyScheduler = new Scheduler(null);
	}
	
	@Test
	public void constructorTest(){
		Scheduler validScheduler = new Scheduler(assemblyLines);
		assertTrue(validScheduler.getCurrentAlgorithm().getClass() == FIFO.class);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void placeNullOrderTest(){
		scheduler.placeOrder(null);
	}
	
	@Test
	public void placeOrder(){
		cmcSystem.logInUser(1);
		User user = cmcSystem.getLoggedInUser();
		Order normalOrder1 = makeOrder(new ModelA());
		Order normalOrder2 = makeOrder(new ModelB());
		Order normalOrder3 = makeOrder(new ModelC());
		scheduler.placeOrder(normalOrder1);
		scheduler.placeOrder(normalOrder2);
		scheduler.placeOrder(normalOrder3);
		List<Order> pendingOrders = scheduler.getPendingOrders();
		assertTrue(pendingOrders.contains(normalOrder1));
		assertTrue(pendingOrders.contains(normalOrder2));
		assertTrue(pendingOrders.contains(normalOrder3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setAlgorithmTest_IllegalArgumentSpecBatch(){
		scheduler.setAlgorithm(new SpecificationBatch(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setAlgorithmTest_NullArgument(){
		scheduler.setAlgorithm(null);
	}
	
	@Test
	public void getPendingOrdersTest_lineAdvanced(){
		placeOrder();
		List<Order> pendingOrders = scheduler.getPendingOrders();
		Order o = pendingOrders.get(0);
		assemblyLine.advance(o);
		assertEquals(13,scheduler.getPendingOrders().size());
	}
}
