package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.schedule.Scheduler;
import company.workstations.CertificationPost;
import company.workstations.Workstation;
import dao.OrderDAOImpl;

import vehicle.assemblytasks.*;
import vehicle.order.ModelA;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Airco;
import vehicle.parts.Part;

public class TestTask {

	private Task task;
	private InstallPart installPart;
	private CheckCertification checkCertification;
	private AddCargoProtection addCargoProtection;
	private InstallToolStorage installToolStorage;
	private CargoTask cargoTask;
	private CMCSystem cmcSystem;
	private Scheduler schedule;
	private Assemblyline assemblyLine;
	
	@Before
	public void setUpMutableFixture(){
		Airco airco = Airco.AUTOMATIC;
		installPart = new InstallPart(airco);
		checkCertification = new CheckCertification();
		addCargoProtection = new AddCargoProtection();
		installToolStorage = new InstallToolStorage();
		cmcSystem = new CMCSystem(new OrderDAOImpl());
		schedule = cmcSystem.getScheduler();
		assemblyLine = cmcSystem.getAssemblyLine(0);
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
	public void toStringTest(){
		assertEquals("install vehicle.parts.Airco: AUTOMATIC",installPart.toString());
		assertEquals("Perform required certification checks",checkCertification.toString());
		assertEquals("Add cargo protection",addCargoProtection.toString());
		assertEquals("Install tool storage", installToolStorage.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void installPartTest_partIsNull(){
		InstallPart installNullPart = new InstallPart(null);
	}
	
	@Test
	public void getResponsibleWorkStationTest_Certification(){
		assertEquals(CertificationPost.class, checkCertification.getResponsibleWorkstation());
	}
	
	@Test
	public void getTimeTest(){
		cmcSystem.logInUser(1);
		Order orderA = makeOrder(new ModelA());
		Workstation ws = assemblyLine.getWorkstations()[0];
		ws.setOrder(orderA);
		Set<Task> allTasks = ws.getAllTasks();
		Task someTask = new CheckCertification();
		for(Task t : allTasks)
			someTask = t;
		cmcSystem.logInUser(3);
		someTask.perform(ws, 3);
		assertEquals(3, someTask.getTime()); //-> zou het moeten zijn, maar is vergeten in de implementatie
//		assertEquals(-1, someTask.getTime()); //moet eigenlijk 3 zijn... maar time wordt in performTask niet aangepast...
	}
	
	@Test (expected = IllegalStateException.class)
	public void getTimeTest_notPerformed(){
		int time = installPart.getTime();
	}
}
