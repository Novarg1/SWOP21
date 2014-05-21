package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vehicle.order.Order;
import company.CMCSystem;
import controllers.StatisticsController;
import controllers.SystemController;
import dao.OrderDAOImpl;

public class TestStatisticsController {
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

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		StatisticsController c = new StatisticsController(new SystemController(system));
		
		double avgCarsPerDayS = c.getAverageCarsProducedPerDay();
		double avgCarsPerDay = system.getAllFinishedOrders().size()/system.getScheduler().getDay();
		assertTrue(avgCarsPerDay == avgCarsPerDayS);
		
		int medCarsPerDayS = c.getMedianCarsProducedPerDay();
		int medCarsPerDay = (int)avgCarsPerDay;
		assertTrue(medCarsPerDayS == medCarsPerDay);
		
		int carsLastTwoDaysS = c.getNumberCarsProducedLast2Days();
		int carsLastTwoDays = 0;
		int ltDay = system.getScheduler().getDay()-2;
		for(Order o : system.getAllFinishedOrders())
			if(o.getCompletionTime().getDay() >= ltDay)
				carsLastTwoDays++;
		assertTrue(carsLastTwoDaysS ==  carsLastTwoDays);
	}

}
