package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import company.workstations.AccessoriesPost;

import vehicle.parts.*;



/**
 * This is a class that collects unit tests for the {@link PartsSet} class
 * 
 * @author Wonne Joosen
 *
 */
public class TestPartsSet {

	
	/**
	 * Instance variables that may
	 * change during individual tests.
	 */
	private PartsSet emptyPartsSet;
	private PartsSet regularPartsSet;
	private Airco airco = Airco.AUTOMATIC;
	private Body body = Body.SEDAN;
	private Color color = Color.BLACK;
	private Engine engine = Engine.HYBRID;
	private Gearbox gearbox = Gearbox.AUTOMATIC;
	private Seats seats = Seats.LEATHER_BLACK;
	private Wheels wheels = Wheels.STANDARD;
	private Spoiler spoiler = Spoiler.HIGH;


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
		regularPartsSet = new PartsSet();
		emptyPartsSet = new PartsSet();

	}

	/**
	 * Set up an immutable test fixture.
	 * 
	 * @post
	 */
	@BeforeClass
	public static void setUpImmutableFixture() {

	}
	
	@Test
	public void addTest(){
		assertTrue(regularPartsSet.isEmpty());
		regularPartsSet.add(airco);
		regularPartsSet.add(body);
		regularPartsSet.add(color);
		regularPartsSet.add(engine);
		regularPartsSet.add(gearbox);
		regularPartsSet.add(seats);
		regularPartsSet.add(wheels);
		assertTrue(regularPartsSet.contains(Airco.class));
		assertTrue(regularPartsSet.contains(Body.class));
		assertTrue(regularPartsSet.contains(Color.class));
		assertTrue(regularPartsSet.contains(Engine.class));
		assertTrue(regularPartsSet.contains(Gearbox.class));
		assertTrue(regularPartsSet.contains(Seats.class));
		assertTrue(regularPartsSet.contains(Wheels.class));
		assertFalse(regularPartsSet.contains(Spoiler.class));
	}
	
	@Test
	public void getTypesTest(){
		addTest();
		Set<?> types = regularPartsSet.getTypes();
		assertTrue(types.contains(Airco.class));
		assertTrue(types.contains(Body.class));
		assertTrue(types.contains(Color.class));
		assertTrue(types.contains(Engine.class));
		assertTrue(types.contains(Gearbox.class));
		assertTrue(types.contains(Seats.class));
		assertTrue(types.contains(Wheels.class));
		assertFalse(types.contains(Spoiler.class));
		
	}
	
	@Test
	public void removeTest(){
		addTest();
		regularPartsSet.remove(Airco.class);
		assertFalse(regularPartsSet.contains(Airco.class));
	}
	
	@Test
	public void toStringTest(){
		emptyPartsSet.add(airco);
		String stringRepresentation = airco.getClass().getName()+ ": "+ airco + "\n";
		assertEquals(emptyPartsSet.toString(), stringRepresentation);
		emptyPartsSet.clear();
	}
	
	@Test
	public void spoilerTest(){
		addTest();
		regularPartsSet.add(spoiler);
		assertTrue(regularPartsSet.contains(Spoiler.class));
		assertEquals(spoiler.getResponsibleWorkstation(), AccessoriesPost.class);
	}

}
