package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import user.UserManager;
import vehicle.order.ModelA;
import vehicle.order.OrderBuilder;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Part;
import vehicle.parts.Seats;
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;

public class TestCarSpecification {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		OrderBuilder spec = new ModelA();
		
		assertTrue(spec.getViableOptions(Body.class).size() == 2);
		assertTrue(spec.getViableOptions(Color.class).size() == 4);
		assertTrue(spec.getViableOptions(Engine.class).size() == 2);
		assertTrue(spec.getViableOptions(Gearbox.class).size() == 3);
		assertTrue(spec.getViableOptions(Airco.class).size() == 2);
		assertTrue(spec.getViableOptions(Seats.class).size() == 3);
		assertTrue(spec.getViableOptions(Wheels.class).size() == 3);
		assertTrue(spec.getViableOptions(Spoiler.class).size() == 0);
		
		assertTrue(!spec.containsPart(Body.class));
		assertTrue(!spec.containsPart(Color.class));
		assertTrue(!spec.containsPart(Engine.class));
		assertTrue(!spec.containsPart(Gearbox.class));
		assertTrue(!spec.containsPart(Airco.class));
		assertTrue(!spec.containsPart(Seats.class));
		assertTrue(!spec.containsPart(Wheels.class));
		assertTrue(!spec.containsPart(Spoiler.class));
		
		spec.add((Part) spec.getViableOptions(Body.class).toArray()[0]);
		assertTrue(!spec.isValid());

		spec.add((Part) spec.getViableOptions(Color.class).toArray()[0]);
		assertTrue(!spec.isValid());
		
		spec.add(Engine.PERFORMANCE_25DL_V6);
		assertTrue(!spec.isValid());

		spec.add((Part) spec.getViableOptions(Gearbox.class).toArray()[0]);
		assertTrue(!spec.isValid());
		
		spec.add((Part) spec.getViableOptions(Airco.class).toArray()[0]);
		assertTrue(!spec.isValid());

		spec.add((Part) spec.getViableOptions(Seats.class).toArray()[0]);
		assertTrue(!spec.isValid());

		spec.add((Part) spec.getViableOptions(Wheels.class).toArray()[0]);
		
		spec.setClient((new UserManager()).logInUser(1));
		
		assertTrue(spec.isValid());
		
		assertTrue(spec.containsPart(Body.class));
		assertTrue(spec.containsPart(Color.class));
		assertTrue(spec.containsPart(Engine.class));
		assertTrue(spec.containsPart(Gearbox.class));
		assertTrue(spec.containsPart(Airco.class));
		assertTrue(spec.containsPart(Seats.class));
		assertTrue(spec.containsPart(Wheels.class));
		assertTrue(!spec.containsPart(Spoiler.class));
	}

}
