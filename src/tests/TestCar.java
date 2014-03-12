package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import system.user.GarageHolder;
import util.TimeStamp;
import car.*;

public class TestCar {

	Car car;
	CarModel model;
	CarSpecification spec;
	CarOrder order;

	@Before
	public void initialize() {
		car = new Car();
		model = new CarModel("testModel") {

			@Override
			public CarBody[] getPossibleBodies() {
				return new CarBody[] { CarBody.BODY_BREAK };
			}

			@Override
			public CarAirco[] getPossibleAircos() {
				return new CarAirco[] { CarAirco.CLIMATE_CONTROL };
			}

			@Override
			public CarColor[] getPossibleColors() {
				return new CarColor[] { CarColor.BLACK, CarColor.BLUE };
			}

			@Override
			public CarEngine[] getPossibleEngines() {
				return new CarEngine[] { CarEngine.PERFORMANCE };
			}

			@Override
			public CarGearbox[] getPossibleGearboxes() {
				return new CarGearbox[] { CarGearbox.AUTOMATIC };
			}

			@Override
			public CarSeats[] getPossibleSeats() {
				return new CarSeats[] { CarSeats.LEATHER_BLACK,
						CarSeats.VINYL_GREY };
			}

			@Override
			public CarWheels[] getPossibleWheels() {
				return new CarWheels[] { CarWheels.COMFORT };
			}

		};
		spec = new CarSpecification(model, CarBody.BODY_BREAK, CarColor.BLACK,
				CarEngine.PERFORMANCE, CarGearbox.AUTOMATIC,
				CarSeats.LEATHER_BLACK, CarAirco.CLIMATE_CONTROL,
				CarWheels.COMFORT);
		order = new CarOrder(new GarageHolder("",""), spec);
	}

	@Test
	public void testModel1() {
		assertTrue(model.isValidSpecification(spec));
	}

	@Test
	public void testModel2() {
		boolean rejected = false;
		try {
			new CarSpecification(model, CarBody.BODY_BREAK, CarColor.BLACK,
					CarEngine.PERFORMANCE, CarGearbox.AUTOMATIC,
					CarSeats.LEATHER_WHITE, CarAirco.CLIMATE_CONTROL,
					CarWheels.COMFORT);
		} catch(IllegalArgumentException e) {
			rejected = true;
		}
		assertTrue(rejected);
	}

	@Test
	public void testSpecification() {
		assertEquals(spec.get(CarWheels.class), CarWheels.COMFORT);
	}

	@Test
	public void testCar() {
		car.install(spec.AIRCO);
		car.install(spec.BODY);
		car.install(spec.COLOR);
		car.install(spec.ENGINE);
		car.install(spec.GEARBOX);
		car.install(spec.SEATS);
		car.install(spec.WHEELS);	
		assertTrue(car.isComplete());
		assertTrue(car.hasPart(spec.AIRCO));
		assertTrue(car.hasPart(spec.BODY));
		assertTrue(car.hasPart(spec.COLOR));
		assertTrue(car.hasPart(spec.ENGINE));
		assertTrue(car.hasPart(spec.GEARBOX));
		assertTrue(car.hasPart(spec.SEATS));
		assertTrue(car.hasPart(spec.WHEELS));
		assertFalse(car.hasPart(CarWheels.SPORTS));
	}
	
	@Test
	public void testOrder() {
		TimeStamp t1 = new TimeStamp(0, 0, 0);
		TimeStamp t2 = new TimeStamp(1, 1, 1);
		assertFalse(order.isFinished());
		order.setCompletionTime(t1);
		assertEquals(order.getCompletionTime(),t1);
		order.setFinished(t2);
		try {
		order.setCompletionTime(t1);
		} catch(IllegalStateException e) { }
		assertEquals(order.getCompletionTime(),t2);
	}

}
