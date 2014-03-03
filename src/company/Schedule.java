package company;

import java.util.LinkedList;

import util.TimeStamp;
import car.Car;
import car.CarInProduction;
import car.CarOrder;

public class Schedule {

	private static final int START_HOUR = 6;
	private static final int END_HOUR = 22;
	private static final int BUILD_TIME = 180;
	
	private TimeStamp currentTime;
	private LinkedList<CarInProduction> schedule;
	
	public Schedule() {
		schedule = new LinkedList<CarInProduction>();
	}
	
	//TODO what if there are no car orders left?
	/**
	 * @return
	 * 		next car to be assembled on assembly line;
	 * 		null if no next car is scheduled for the current day
	 */
	public CarInProduction next() {
		CarInProduction next = schedule.peek();
		
		//TODO
		
		return null;
	}
	
	public void addOrder(CarOrder order) {
		schedule.add(new CarInProduction(new Car(order.), order));
	}
}
