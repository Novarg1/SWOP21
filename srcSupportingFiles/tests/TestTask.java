package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import company.workstations.*;

import vehicle.assemblytasks.*;
import vehicle.parts.*;
/**
 * This is a class that collects unit tests for the {@link Task} class and its subclasses
 * 
 * @author Wonne Joosen
 *
 */
public class TestTask {

	private InstallPart installPart1;
	private InstallPart installPart2;
	private CheckCertification checkCertification;
	private InstallToolStorage installToolStorage;
	private AddCargoProtection addCargoProtection;
	
	
	/**
	 * Set up a mutable test fixture.
	 * 
	 *  @post an assembly line is created
	 */
	@Before
	public void setUpMutableFixture() {
		Airco airco = Airco.AUTOMATIC;
		Engine engine = Engine.STANDARD;
		installPart1 = new InstallPart(airco);
		installPart2 = new InstallPart(engine);
		checkCertification = new CheckCertification();
		installToolStorage = new InstallToolStorage();
		addCargoProtection = new AddCargoProtection();
	}
	
	@Test
	public void performTest(){
		installPart1.perform();
		assertTrue(installPart1.isPerformed());
	}
	
	@Test
	public void getResponsibleWorkstationTest_cargoPost(){
		assertEquals(CargoPost.class, installToolStorage.getResponsibleWorkstation());
	}
	
	@Test
	public void getResponsibleWorkstationTest_InstallPart(){
		assertEquals(AccessoiresPost.class, installPart1.getResponsibleWorkstation());
		assertEquals(DriveTrainPost.class, installPart2.getResponsibleWorkstation());
	}
	
	@Test
	public void getResponsibleWorkstationTest_checkCertification(){
		assertEquals(CertificationPost.class,checkCertification.getResponsibleWorkstation());
	}
	
	@Test
	public void toStringTest_installToolStorage(){
		assertEquals("Install tool storage", installToolStorage.toString());
	}
	
	@Test
	public void toStringTest_installPart(){
		Airco part = Airco.AUTOMATIC;
		InstallPart installPart = new InstallPart(part);
		assertEquals("install " + "vehicle.parts.Airco" + ": " + part,installPart.toString());
	}
	
	@Test
	public void toStringTest_checkCertification(){
		assertEquals("Perform required certification checks",checkCertification.toString());
	}
	
	@Test
	public void toStringTest_addCargoProtection(){
		assertEquals("Add cargo protection",addCargoProtection.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void installPartConstructorTest_argumentNull(){
		InstallPart installPart = new InstallPart(null);
	}
	

}
