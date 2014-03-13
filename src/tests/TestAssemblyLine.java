package tests;

import static org.junit.Assert.*;

import javax.management.StandardMBean;

import org.junit.Before;
import org.junit.Test;

import system.user.GarageHolder;
import car.Car;
import car.CarAirco;
import car.CarBody;
import car.CarColor;
import car.CarEngine;
import car.CarGearbox;
import car.CarInProduction;
import car.CarModel;
import car.CarOrder;
import car.CarPart;
import car.CarSeats;
import car.CarSpecification;
import car.CarWheels;
import car.StandardModel;
import company.AssemblyLine;
import company.Company;
import company.WorkStation;

public class TestAssemblyLine {

	Company company;
	CarInProduction job;

	@Before
	public void initialize() {
		company = new Company();
		Car car = new Car();
		CarSpecification spec = new CarSpecification(new StandardModel(), CarBody.BODY_BREAK, CarColor.BLACK,
				CarEngine.PERFORMANCE, CarGearbox.AUTOMATIC,
				CarSeats.LEATHER_BLACK, CarAirco.CLIMATE_CONTROL,
				CarWheels.COMFORT);
		CarOrder order = new CarOrder(new GarageHolder("",""), spec);
		job = new CarInProduction(car,order);
	}

	@Test
	public void test() {
		assertTrue(company.getAssemblyLine().isReadyToAdvance());

		for (WorkStation ws : company.getAssemblyLine().getWorkstations()) {
			ws.setCurrentJob(job);
			assertFalse(company.getAssemblyLine().isReadyToAdvance());
			for (CarPart part : ws.getPendingTasks()) {
				ws.install(part);
			}
		}
		assertTrue(job.CAR.isComplete());
		for (CarPart part : job.ORDER.SPECIFICATION.getAll()) {
			assertTrue(job.CAR.hasPart(part));
		}
//		company.getAssemblyLine().getWorkstations()[1] = null;
//		company.advanceAssemblyLine(60);
//		assertNull(company.getAssemblyLine().getWorkstations()[0]);
//		assertNull(company.getAssemblyLine().getWorkstations()[2]);
//		assertEquals(company.getAssemblyLine().getWorkstations()[1], job);
	}
}
