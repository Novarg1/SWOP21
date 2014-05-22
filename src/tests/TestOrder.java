package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import company.CMCSystem;
import company.workstations.AccessoiresPost;
import dao.OrderDAOImpl;

import user.User;
import util.Timestamp;
import vehicle.assemblytasks.Task;
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
		orderA = makeOrder(new ModelA());
		orderB = makeOrder(new ModelB());
		orderC = makeOrder(new ModelC());
		orderX = makeOrder(new ModelX());
		user = cmcSystem.getLoggedInUser();	
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
	public void matchesTest(){
		assertTrue(orderA.matches(orderA));
		assertFalse(orderA.matches(orderX));
	}
	
	@Test
	public void cloneTest(){
		Order cloneOfOrderA = orderA.clone();
		orderA.setFinished(Timestamp.beginningOfDay(1));
		assertEquals(cloneOfOrderA.getClient(),orderA.getClient());
		assertEquals(cloneOfOrderA.getTasks(),orderA.getTasks());
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
		int buildingTime = orderA.getBuildingTimeFor(AccessoiresPost.class);
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
		CustomOrderBuilder co;
	}

}
