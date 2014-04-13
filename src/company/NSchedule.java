package company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import car.CarOrder;
import car.CarPart;

public class NSchedule 
{
	private LinkedList<CarOrder> upcoming;
	private LinkedList<Day> days;
	
	public NSchedule()
	{
		this.upcoming = new LinkedList<CarOrder>();
		this.days = new LinkedList<Day>();
		this.days.push(new Day(1));
	}
	
	public Day getCurrentDay()
	{
		return this.days.peek();
	}
	
	public void startNewDay()
	{
		this.days.push(this.days.peek().getNextDay());
	}
	
	public void increaseDayTime(int time)
	{
		this.days.peek().increaseTime(time);
	}
	
	public void addOrder(CarOrder order)
	{
		this.upcoming.add(order);
	}
	
	private CarOrder getNextOrder()
	{
		return this.upcoming.pop();
	}
	
	public int getExpectedWorkTimeForNextOrder(int numberOfWorkstations)
	{
		return this.upcoming.peek().SPECIFICATION.getBuildingTimePerWorkstation() * numberOfWorkstations;
	}
	
	public boolean canNextOrderBeBuildToday(int numberOfWorkstations)
	{
		int additionalTime = this.getExpectedWorkTimeForNextOrder(numberOfWorkstations);
		return !this.days.peek().shouldBeFinished(additionalTime);
	}
	
	public CarOrder prepareNextOrder()
	{
		CarOrder order = this.getNextOrder();
		Map<String, LinkedList<CarPart>> productionSchedule = new HashMap<String, LinkedList<CarPart>>();
		
		// preparing the schedule for the car body post
		LinkedList<CarPart> CarBodyPost = new LinkedList<CarPart>();
		CarBodyPost.add(order.SPECIFICATION.getBody());
		CarBodyPost.add(order.SPECIFICATION.getColor());
		
		// preparing the schedule for the drive train post
		LinkedList<CarPart> DriveTrainPost = new LinkedList<CarPart>();
		DriveTrainPost.add(order.SPECIFICATION.getEngine());
		DriveTrainPost.add(order.SPECIFICATION.getGearbox());
		
		// preparing the schedule for the accessoires post
		LinkedList<CarPart> AccessoiresPost = new LinkedList<CarPart>();
		AccessoiresPost.add(order.SPECIFICATION.getSeats());
		if(order.SPECIFICATION.getAircoChosen())
			AccessoiresPost.add(order.SPECIFICATION.getAirco());
		AccessoiresPost.add(order.SPECIFICATION.getWheels());
		if(order.SPECIFICATION.getSpoilerChosen())
			AccessoiresPost.add(order.SPECIFICATION.getSpoiler());
		
		// creating the hashmap
		productionSchedule.put("CARBODY", CarBodyPost);
		productionSchedule.put("DRIVETRAIN", DriveTrainPost);
		productionSchedule.put("ACCESSOIRES", AccessoiresPost);
		
		order.setProductionScheme(productionSchedule);
		
		return order;
	}
}
