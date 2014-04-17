package company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.TimeStamp;
import car.CarOrder;
import car.CarPart;

/*
 * TODO ideetje (voor mezelf om morgen verder te denken):
 * maak terug car klasse; 
 * geef car maar geen carOrder aan workposts;
 * workposts vragen aan schedule wat er op hun car moet;
 * modelspecification houdt dan geen parts maar types bij;
 */

/**
 * Represents the schedule of the company. Contains a list of pending orders,
 * the current time and algorithms for scheduling the pending orders.
 */
public class Schedule {

	public enum Algorithm {
		FIFO, SPECIFICATION_BATCH;
	}

	private LinkedList<CarOrder> pending;
	private LinkedList<CarOrder> schedule;
	private TimeStamp currentTime;
	private Algorithm algorithm;

	/**
	 * creates new schedule with an empty list of carOrders and a current
	 * time at the beginning of the first day. The schedule uses the FIFO
	 * scheduling algorithm.
	 */
	public Schedule() {
		this.pending = new LinkedList<>();
		this.schedule = new LinkedList<>();
		this.currentTime = TimeStamp.FirstDay();
		this.algorithm = Algorithm.FIFO;
	}

	/**
	 * @return the current time
	 */
	public TimeStamp getCurrentTime() {
		return currentTime;
	}

	/**
	 * Sets current time to beginning of next day.
	 */
	public void startNewDay() {
		currentTime = currentTime.getNextDay();
	}

	/**
	 * increases the current time with given time.
	 * @param time increase in time (minutes)
	 */
	public void increaseDayTime(int time) {
		currentTime = currentTime.increaseTime(time);
	}

	/**
	 * @return the currently used scheduling algorithm.
	 */
	public Algorithm getCurrentAlgorithm() {
		return algorithm;
	}

	/**
	 * sets the scheduling algorithm to the given algorithm.
	 */
	public void setAlgorithm(Algorithm algorithm) {
		if(algorithm == null) {
			throw new IllegalArgumentException("scheduling algorithm can't be null");
		}
		this.algorithm = algorithm;
		updateSchedule();
	}

	/**
	 * updates the schedule, using the currently selected scheduling
	 * algorithm.
	 */
	private void updateSchedule() {
		switch(algorithm) {
		case FIFO: scheduleFIFO(); break;
		case SPECIFICATION_BATCH: scheduleSpecificationBatch(); break;
		}
	}

	/**
	 * updates the schedule using the FIFO algorithm
	 */
	private void scheduleFIFO() {
		schedule.clear();
		//TODO
	}

	/**
	 * updates the schedule using the specification batch algorithm
	 */
	private void scheduleSpecificationBatch() {
		schedule.clear();
		//TODO
	}

	/**
	 * adds given order to the list of pending orders and updates the schedule.
	 * Estimated completion time will be set in order.
	 */
	public void placeOrder(CarOrder order) {
		this.pending.addLast(order);
		updateSchedule();
	}

	/**
	 * Returns the next order that is scheduled to start assembly and removes
	 * it from the pending orders.
	 */
	private CarOrder getNextOrder() {
		return this.pending.pop();
	}

	/**
	 * @return All upcoming orders, in the order they are scheduled.
	 */
	public LinkedList<CarOrder> getUpcomingOrders() {
		return new LinkedList<>(schedule);
	}

	public int getExpectedWorkTimeForNextOrder(int numberOfWorkstations) {
		return this.pending.peek().SPECIFICATION.getBuildingTimePerWorkstation() * numberOfWorkstations;
		//TODO incorrect; take other orders into account
	}

	public boolean canNextOrderBeBuildToday(int numberOfWorkstations) {
		int additionalTime = this.getExpectedWorkTimeForNextOrder(numberOfWorkstations);
		return !this.currentTime.shouldBeFinishedAfter(additionalTime);
	}

	public CarOrder prepareNextOrder() {
		CarOrder order = this.getNextOrder();
		Map<String, List<CarPart<?>>> productionSchedule = new HashMap<>();

		// preparing the schedule for the car body post
		LinkedList<CarPart<?>> carBodyPost = new LinkedList<>();
		carBodyPost.add(order.SPECIFICATION.getBody());
		carBodyPost.add(order.SPECIFICATION.getColor());

		// preparing the schedule for the drive train post
		LinkedList<CarPart<?>> driveTrainPost = new LinkedList<>();
		driveTrainPost.add(order.SPECIFICATION.getEngine());
		driveTrainPost.add(order.SPECIFICATION.getGearbox());

		// preparing the schedule for the accessoires post
		LinkedList<CarPart<?>> accessoiresPost = new LinkedList<>();
		accessoiresPost.add(order.SPECIFICATION.getSeats());
		if(order.SPECIFICATION.aircoChosen())
			accessoiresPost.add(order.SPECIFICATION.getAirco());
		accessoiresPost.add(order.SPECIFICATION.getWheels());
		if(order.SPECIFICATION.spoilerChosen())
			accessoiresPost.add(order.SPECIFICATION.getSpoiler());

		// creating the hashmap
		productionSchedule.put("CARBODY", carBodyPost);
		productionSchedule.put("DRIVETRAIN", driveTrainPost);	//TODO String-based; moet anders
		productionSchedule.put("ACCESSOIRES", accessoiresPost);

		order.setProductionScheme(productionSchedule);

		return order;
	}
}
