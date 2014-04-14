//package tests;
//
//import static org.junit.Assert.*;
//
//import javax.management.StandardMBean;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import car.Car;
//import car.CarPartAirco;
//import car.CarPartBody;
//import car.CarPartColor;
//import car.CarPartEngine;
//import car.CarPartGearbox;
//import car.CarInProduction;
//import car.CarModel;
//import car.CarOrder;
//import car.CarPart;
//import car.CarPartSeats;
//import car.CarModelSpecification;
//import car.CarPartWheels;
//import car.StandardModel;
//import company.AssemblyLine;
//import company.Company;
//import company.WorkStation;
//import controllers.user.GarageHolder;
//
//public class TestAssemblyLine {
//
//	Company company;
//	CarInProduction job;
//
//	@Before
//	public void initialize() {
//		company = new Company();
//		Car car = new Car();
//		CarModelSpecification spec = new CarModelSpecification(new StandardModel(), CarPartBody.BODY_BREAK, CarPartColor.BLACK,
//				CarPartEngine.PERFORMANCE, CarPartGearbox.AUTOMATIC,
//				CarPartSeats.LEATHER_BLACK, CarPartAirco.CLIMATE_CONTROL,
//				CarPartWheels.COMFORT);
//		CarOrder order = new CarOrder(new GarageHolder("",""), spec);
//		job = new CarInProduction(car,order);
//	}
//
//	@Test
//	public void test() {
//		assertTrue(company.getAssemblyLine().isReadyToAdvance());
//
//		for (WorkStation ws : company.getAssemblyLine().getWorkstations()) {
//			ws.setCurrentJob(job);
//			assertFalse(company.getAssemblyLine().isReadyToAdvance());
//			for (CarPart part : ws.getPendingTasks()) {
//				ws.install(part);
//			}
//		}
//		assertTrue(job.CAR.isComplete());
//		for (CarPart part : job.ORDER.SPECIFICATION.getAll()) {
//			assertTrue(job.CAR.hasPart(part));
//		}
////		company.getAssemblyLine().getWorkstations()[1] = null;
////		company.advanceAssemblyLine(60);
////		assertNull(company.getAssemblyLine().getWorkstations()[0]);
////		assertNull(company.getAssemblyLine().getWorkstations()[2]);
////		assertEquals(company.getAssemblyLine().getWorkstations()[1], job);
//	}
//}
