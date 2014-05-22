package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.Timestamp;
import vehicle.order.ModelA;
import vehicle.order.ModelB;
import vehicle.order.ModelC;
import vehicle.order.ModelX;
import vehicle.order.ModelY;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.assemblylines.Assemblyline.Status;
import company.schedule.Scheduler;
import company.workstations.Workstation;
import dao.OrderDAOImpl;

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
	private Assemblyline assemblyLine;	
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
		cmcSystem = new CMCSystem(new OrderDAOImpl());
		schedule = cmcSystem.getScheduler();
		assemblyLine = cmcSystem.getAssemblyLine(0);
	}

	/**
	 * Set up an immutable test fixture.
	 * 
	 * @post
	 */
	@BeforeClass
	public static void setUpImmutableFixture() {
		
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
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		Order order2 = makeOrder(new ModelX());
		Order order3 = makeOrder(new ModelC());
		schedule.placeOrder(order1);
		schedule.placeOrder(order2);
		schedule.placeOrder(order3);
		assertTrue(assemblyLine.getWorkstations()[0].isReady());
		assemblyLine.advance(order1);
		assertFalse(assemblyLine.isReadyToAdvance());
		assertFalse(assemblyLine.getWorkstations()[0].isReady());
	}
	
	@Test (expected = IllegalStateException.class)
	public void advanceAssemblyLineTest_broken(){
		assemblyLine.setStatus(Status.BROKEN, 1);
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		schedule.placeOrder(order1);
		assemblyLine.advance(order1);
	}
	
	@Test (expected = IllegalStateException.class)
	public void advanceAssemblyLineTest_broken_notready(){
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		Order order2 = makeOrder(new ModelC());
		schedule.placeOrder(order1);
		schedule.placeOrder(order2);
		assemblyLine.advance(order1);
		assemblyLine.setStatus(Status.BROKEN, 1);
		assemblyLine.advance(order2);
	}
	
	/**
	 * A test for the startNextDay method (success scenario) 
	 */
	@Test
	public void advanceDayTest_Succes(){
		emptyAssemblyLineTest();
		Timestamp oldTime = assemblyLine.getCurrentTime();
		assemblyLine.startNextDay();
		assertEquals(assemblyLine.getCurrentTime().compareTo(oldTime.getNextDay()),0);
		
	}
	
	/**
	 * A test that checks whether an illegal state exception is thrown when one tries to call the startNextDay() method
	 * when the AssemblyLine is not empty
	 */
	@Test (expected = IllegalStateException.class)
	public void advanceDayTest_Fail(){
		advanceAssemblyLineTest();
		assemblyLine.startNextDay();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setStatusTest_nullStatus(){
		advanceDayTest_Succes();
		Status s = null;
		assemblyLine.setStatus(s, 1);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void setStatusTest_nullStatus_negativeTime(){
		advanceDayTest_Succes();
		Status s = null;
		assemblyLine.setStatus(s, -1);
	}
	
	@Test
	public void checkMaintenanceTest(){
		advanceDayTest_Succes();
		assemblyLine.setStatus(Status.MAINTENANCE, 10);
		assertTrue(assemblyLine.getStatus() == Status.OPERATIONAL);
	}
	
	@Test
	public void cloneTest(){
		Assemblyline a = assemblyLine.clone();
		assertEquals(assemblyLine.isReadyToAdvance(), a.isReadyToAdvance());
		assertEquals(assemblyLine.hasChanged(),a.hasChanged());
	}
	
	@Test
	public void toStringTest(){
		String name = assemblyLine.toString();
		assertEquals(assemblyLine.getClass().getName(), name);
	}
	
	@Test
	public void hasWorkstationTest(){
		cmcSystem.logInUser(1);
		Order testOrder = makeOrder(new ModelA());
		Order testOrder2 = makeOrder(new ModelB());
//		Order testOrder3 = makeOrder(new ModelC());
//		Order testOrder4 = makeOrder(new ModelX());
		Order testOrder5 = makeOrder(new ModelY());
		assemblyLine.setStatus(Status.OPERATIONAL, 1);
		assertTrue(assemblyLine.supports(testOrder));
		assertTrue(assemblyLine.supports(testOrder2));
//		assertFalse(assemblyLine.supports(testOrder3));
//		assertFalse(assemblyLine.supports(testOrder4));
		assertFalse(assemblyLine.supports(testOrder5));
	}
	

}