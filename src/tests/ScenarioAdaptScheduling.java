package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vehicle.order.ModelA;
import vehicle.order.Order;
import vehicle.order.OrderBuilder;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Wheels;
import company.CMCSystem;
import company.schedule.Scheduler;
import company.schedule.SpecificationBatch;
import dao.OrderDAOImpl;

public class ScenarioAdaptScheduling {
	/**
	 * variables that may change during the tests
	 */
	
	/**
	 * variable that must remain immutable during tests
	 */
	private CMCSystem system = new CMCSystem(new OrderDAOImpl());
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		// log in the garage holder
		system.logInUser(1);
	}

	@Test
	public void test() {
		Scheduler scheduler = system.getScheduler();
		
		// build a sample
		OrderBuilder spec = new ModelA();
		spec.add(Body.SEDAN);
		spec.add(Color.WHITE);
		spec.add(Engine.STANDARD);
		spec.add(Gearbox.MANUAL_5);
		spec.add(Seats.LEATHER_WHITE);
		spec.add(Wheels.COMFORT);
		spec.add(Airco.MANUAL);
		Order sample = new Order(spec);
		
		// set up the algorithm
		SpecificationBatch batch = new SpecificationBatch(sample);
		
		// set the schedulers algorithm to batch
		scheduler.setAlgorithm(batch);
		
		// assert the correctness
		assertEquals(batch, scheduler.getCurrentAlgorithm());
	}

}
