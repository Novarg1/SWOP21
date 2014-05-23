package tests;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import vehicle.assemblytasks.Task;
import vehicle.order.ModelA;
import vehicle.order.ModelB;
import vehicle.order.ModelC;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import static org.junit.Assert.*;


import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.schedule.Scheduler;
import company.workstations.Workstation;
import dao.OrderDAOImpl;

/**
 * This class collects unit tests for the Workstation class
 * 
 * @author Wonne Joosen
 */
public class TestWorkstation {

	private Scheduler schedule;
	private Assemblyline assemblyLine;	
	private CMCSystem cmcSystem;
	private Workstation workstation1;
	private Workstation workstation2;
	private Workstation workstation3;
	


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
		workstation1 = assemblyLine.getWorkstations()[0];
		workstation2 = assemblyLine.getWorkstations()[1];
		workstation3 = assemblyLine.getWorkstations()[2];
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
	public void performTaskTest(){
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		workstation1.setOrder(order1);
		Set<Task> pendingTasks = workstation1.getPendingTasks();
		Task task = null;
		for(Task t : pendingTasks)
			task = t;
		workstation1.perform(task,1);
		assertTrue(task.isPerformed());
	}
	
	@Test (expected = IllegalStateException.class)
	public void performTaskTest_currentNull(){
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		workstation1.setOrder(order1);
		Set<Task> pendingTasks = workstation1.getPendingTasks();
		Task task = null;
		for(Task t : pendingTasks)
			task = t;
		Order nullOrder = null;
		workstation2.setOrder(nullOrder);
		workstation2.perform(task, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void performTaskTest_negativeTime(){
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		workstation1.setOrder(order1);
		Set<Task> pendingTasks = workstation1.getPendingTasks();
		Task task = null;
		for(Task t : pendingTasks)
			task = t;
		workstation1.perform(task, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void performTaskTest_noTasksLeft(){
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelB());
		Order order2 = makeOrder(new ModelA());
		workstation3.setOrder(order1);
		workstation2.setOrder(order2);
		Set<Task> pendingTasks = workstation3.getPendingTasks();
		Task task = null;
		for(Task t : pendingTasks)
			task = t;
		workstation2.perform(task, 10);
	}
	
	
	//TODO: om de 3 keer verandert hier precies de type van de workpost?
	@Test
	public void toStringTest(){
		assertEquals(workstation1.toString(),"company.workstations.BodyPost");
		assertEquals(workstation2.toString(),"company.workstations.DriveTrainPost");
//		assertEquals(workstation3.toString(),"company.workstations.CargoPost");
	}
	
	@Test
	public void getWorkTimeTest(){
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		workstation1.setOrder(order1);
		Set<Task> pendingTasks = workstation1.getPendingTasks();
		Task task = null;
		for(Task t : pendingTasks)
			task = t;
		workstation1.perform(task,1);
		assertTrue(task.isPerformed());
		assertEquals(1,workstation1.getWorktime());
	}
	
	@Test
	public void cloneTest(){
		Workstation ws = workstation1.clone();
		assertEquals(ws.countObservers(),workstation1.countObservers());
		assertEquals(ws.isReady(),workstation1.isReady());
		assertEquals(ws.getPendingTasks(),workstation1.getPendingTasks());
		cmcSystem.logInUser(1);
		Order order1 = makeOrder(new ModelC());
		ws.setOrder(order1);
		Task task = null;
		for(Task t : ws.getPendingTasks())
			task = t;
		ws.perform(task, 2);
		Workstation wsClone = ws.clone();
		assertNotSame(ws.getOrder(),workstation1.getOrder());
		assertNotSame(wsClone,workstation1);
	}

}
