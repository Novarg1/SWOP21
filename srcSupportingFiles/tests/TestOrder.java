package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import company.CMCSystem;
import company.workstations.AccessoriesPost;
import dao.OrderDAOImpl;

import user.User;
import util.Timestamp;
import vehicle.order.*;
import vehicle.parts.Part;

/**
 * This is a class that collects unit tests for the {@link Order} class
 * 
 * @author Wonne Joosen
 *
 */
public class TestOrder {

	private CMCSystem cmcSystem;
	private Order orderA;
	private Order orderB;
	private Order orderC;
	private Order orderX;
	private Order orderY;
	private User user;
	
	@Before
	public void setUpMutableFixture(){
		cmcSystem = new CMCSystem(new OrderDAOImpl());
		cmcSystem.logInUser(1);
		user = cmcSystem.getLoggedInUser();	
		orderA = makeOrder(new ModelA(),user);
		cmcSystem.logInUser(1);
		user = cmcSystem.getLoggedInUser();	
		orderB = makeOrder(new ModelB(),user);
		cmcSystem.logInUser(1);
		user = cmcSystem.getLoggedInUser();	
		orderC = makeOrder(new ModelC(),user);
		cmcSystem.logInUser(1);
		user = cmcSystem.getLoggedInUser();	
		orderX = makeOrder(new ModelX(),user);
		cmcSystem.logInUser(1);
		user = cmcSystem.getLoggedInUser();	
		orderY = makeOrder(new ModelY(),user);
	}
	
	public OrderBuilder makeOrderSpec(OrderBuilder os,User user){
		for (Class<? extends Part> type : os.getSupportedTypes()) {
			for(Part part : os.getViableOptions(type)) {
				os.add(part);
			}
		}
		os.setClient(user);
		return os;
	}
	
	public Order makeOrder(OrderBuilder os,User user){
		OrderBuilder spec = makeOrderSpec(os,user);
		Order order = new Order(spec);
		return order;
	}

	@Test
	public void matchesTest(){
		assertTrue(orderA.matches(orderA));
		assertFalse(orderA.matches(orderX));
	}
	
	@Test
	public void cloneTest(){
		Order cloneOfOrderA = orderA.clone();
		orderA.setFinished(Timestamp.beginningOfDay(1));
		assertEquals(cloneOfOrderA.getClient(),orderA.getClient());
		cloneOfOrderA.setFinished(Timestamp.beginningOfDay(2));
		assertFalse((orderA.getCompletionTime().compareTo(cloneOfOrderA.getCompletionTime())) == 0);
	}
	
	@Test(expected = IllegalStateException.class)
	public void doubleFinishedTest(){
		orderB.setFinished(Timestamp.beginningOfDay(1));
		orderB.setFinished(Timestamp.beginningOfDay(2));
	}
	
	@Test
	public void getTypeTest(){
		Class<?> type = orderC.getType();
		assertEquals(type, ModelC.class);
	}

	@Test
	public void toStringTest(){
		String string = orderC.toString();
		assertEquals(string,"Order"); //TODO
	}

	@Test
	public void getBuildingTimeTest(){
		int buildingTime = orderA.getBuildingTimeFor(AccessoriesPost.class);
		assertEquals(buildingTime, 50);
	}
	
	@Test
	public void getBuildingTimeTest_Fail(){
		int buildingTime = orderA.getBuildingTimeFor(null);
		assertEquals(buildingTime, 0);
	}
	
	@Test
	public void getDeadlineTest(){
		Timestamp deadline = orderA.getDeadline();
		assertEquals(deadline, null);
	}
	
	@Test
	public void customOrderTest(){
		CustomOrderBuilder spec = new CustomSeats();
		spec.setClient(user);
		spec.setDeadline(Timestamp.beginningOfDay(3));
		Timestamp beginDay3 = Timestamp.beginningOfDay(3);
		cmcSystem.logInUser(2);
		user = cmcSystem.getLoggedInUser();
		Order specialOrder = makeOrder(spec,user);
		assertTrue(specialOrder.getDeadline().compareTo(beginDay3) == 0);
	}
	
	@Test
	public void orderBuilderToStringTest(){
		assertEquals("Car Model A",(new ModelA().toString()));
		assertEquals("Car Model B",(new ModelB().toString()));
		assertEquals("Car Model C",(new ModelC().toString()));
		assertEquals("Truck Model X",(new ModelX().toString()));
		assertEquals("Truck Model Y",(new ModelY().toString()));
	}

}
