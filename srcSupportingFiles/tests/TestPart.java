package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import company.workstations.*;

import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Part;
import vehicle.parts.Seats;
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;

public class TestPart {

	
	/**
	 * Instance variables that may
	 * change during individual tests.
	 */
	private Airco aircoAuto = Airco.AUTOMATIC;
	private Airco aircoMan = Airco.MANUAL;
	private Body body = Body.SEDAN;
	private Color color = Color.BLACK;
	private Engine engine = Engine.HYBRID;
	private Gearbox gearbox = Gearbox.AUTOMATIC;
	private Seats seats = Seats.LEATHER_BLACK;
	private Wheels wheels = Wheels.STANDARD;
	private Spoiler spoiler = Spoiler.HIGH;
	
	

	@Test
	public void testAirco(){
		assertEquals(Airco.class,aircoAuto.getClass());
		assertEquals(AccessoriesPost.class,aircoAuto.getResponsibleWorkstation());
		assertEquals("AUTOMATIC",aircoAuto.name());
		assertEquals("MANUAL",aircoMan.name());
		assertFalse(aircoAuto.equals(aircoMan));
		assertEquals(0,aircoMan.compareTo(aircoMan));
	}
	
	@Test
	public void testPartInterface(){
		Part partAirco = aircoMan;
		Part partBody = body;
		Part partColor = color;
		Part partEngine = engine;
		Part partGearbox = gearbox;
		Part partSeats = seats;
		Part partWheels = wheels;
		Part partSpoiler = spoiler;
		assertEquals(AccessoriesPost.class, partAirco.getResponsibleWorkstation());
		assertEquals(BodyPost.class, partBody.getResponsibleWorkstation());
		assertEquals(BodyPost.class, partColor.getResponsibleWorkstation());
		assertEquals(DriveTrainPost.class, partEngine.getResponsibleWorkstation());
		assertEquals(DriveTrainPost.class, partGearbox.getResponsibleWorkstation());
		assertEquals(AccessoriesPost.class, partSeats.getResponsibleWorkstation());
		assertEquals(AccessoriesPost.class, partWheels.getResponsibleWorkstation());
		assertEquals(AccessoriesPost.class, partSpoiler.getResponsibleWorkstation());
		assertTrue(body.getClass().getPackage() == Package.getPackage("vehicle.parts"));
	}
	

	@SuppressWarnings("static-access")
	@Test
	public void test_noException(){
		aircoMan.values();
		aircoAuto.values();
		body.values();
		color.values();
		engine.values();
		gearbox.values();
		seats.values();
		wheels.values();
		spoiler.values();
	}
}
