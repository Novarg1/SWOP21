//package tests;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import controllers.user.GarageHolder;
//import util.TimeStamp;
//import car.*;
//
//public class TestCar {
//
//	Car car;
//	CarModel model;
//	CarModelSpecification spec;
//	CarOrder order;
//
//	@Before
//	public void initialize() {
//		car = new Car();
//		model = new CarModel("testModel") {
//
//			@Override
//			public CarPartBody[] getPossibleBodies() {
//				return new CarPartBody[] { CarPartBody.BODY_BREAK };
//			}
//
//			@Override
//			public CarPartAirco[] getPossibleAircos() {
//				return new CarPartAirco[] { CarPartAirco.CLIMATE_CONTROL };
//			}
//
//			@Override
//			public CarPartColor[] getPossibleColors() {
//				return new CarPartColor[] { CarPartColor.BLACK, CarPartColor.BLUE };
//			}
//
//			@Override
//			public CarPartEngine[] getPossibleEngines() {
//				return new CarPartEngine[] { CarPartEngine.PERFORMANCE };
//			}
//
//			@Override
//			public CarPartGearbox[] getPossibleGearboxes() {
//				return new CarPartGearbox[] { CarPartGearbox.AUTOMATIC };
//			}
//
//			@Override
//			public CarPartSeats[] getPossibleSeats() {
//				return new CarPartSeats[] { CarPartSeats.LEATHER_BLACK,
//						CarPartSeats.VINYL_GREY };
//			}
//
//			@Override
//			public CarPartWheels[] getPossibleWheels() {
//				return new CarPartWheels[] { CarPartWheels.COMFORT };
//			}
//
//		};
//		spec = new CarModelSpecification(model, CarPartBody.BODY_BREAK, CarPartColor.BLACK,
//				CarPartEngine.PERFORMANCE, CarPartGearbox.AUTOMATIC,
//				CarPartSeats.LEATHER_BLACK, CarPartAirco.CLIMATE_CONTROL,
//				CarPartWheels.COMFORT);
//		order = new CarOrder(new GarageHolder("",""), spec);
//	}
//
//	@Test
//	public void testModel1() {
//		assertTrue(model.isValidSpecification(spec));
//	}
//
//	@Test
//	public void testModel2() {
//		boolean rejected = false;
//		try {
//			new CarModelSpecification(model, CarPartBody.BODY_BREAK, CarPartColor.BLACK,
//					CarPartEngine.PERFORMANCE, CarPartGearbox.AUTOMATIC,
//					CarPartSeats.LEATHER_WHITE, CarPartAirco.CLIMATE_CONTROL,
//					CarPartWheels.COMFORT);
//		} catch(IllegalArgumentException e) {
//			rejected = true;
//		}
//		assertTrue(rejected);
//	}
//
//	@Test
//	public void testSpecification() {
//		assertEquals(spec.get(CarPartWheels.class), CarPartWheels.COMFORT);
//	}
//
//	@Test
//	public void testCar() {
//		car.install(spec.AIRCO);
//		car.install(spec.BODY);
//		car.install(spec.COLOR);
//		car.install(spec.ENGINE);
//		car.install(spec.GEARBOX);
//		car.install(spec.SEATS);
//		car.install(spec.WHEELS);	
//		assertTrue(car.isComplete());
//		assertTrue(car.hasPart(spec.AIRCO));
//		assertTrue(car.hasPart(spec.BODY));
//		assertTrue(car.hasPart(spec.COLOR));
//		assertTrue(car.hasPart(spec.ENGINE));
//		assertTrue(car.hasPart(spec.GEARBOX));
//		assertTrue(car.hasPart(spec.SEATS));
//		assertTrue(car.hasPart(spec.WHEELS));
//		assertFalse(car.hasPart(CarPartWheels.SPORTS));
//	}
//	
//	@Test
//	public void testOrder() {
//		TimeStamp t1 = new TimeStamp(0, 0, 0);
//		TimeStamp t2 = new TimeStamp(1, 1, 1);
//		assertFalse(order.isFinished());
//		order.setCompletionTime(t1);
//		assertEquals(order.getCompletionTime(),t1);
//		order.setFinished(t2);
//		try {
//		order.setCompletionTime(t1);
//		} catch(IllegalStateException e) { }
//		assertEquals(order.getCompletionTime(),t2);
//	}
//
//}
