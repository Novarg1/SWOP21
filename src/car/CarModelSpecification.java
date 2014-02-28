package car;

import java.util.ArrayList;
import java.util.List;

public class CarModelSpecification {
	
	private List<CarOrder> orderList;
	
	public CarModelSpecification () {
		this.orderList = new ArrayList<CarOrder>();
	}
	
	public void addOrder(CarOrder order) {
		this.orderList.add(order);
	}
	
	public CarBody[] getPossibleBodies() {
		return CarBody.class.getEnumConstants();
	}
	
	public CarAirco[] getPossibleAircos() {
		return CarAirco.class.getEnumConstants();
	}
	
	public CarColor[] getPossibleColors() {
		return CarColor.class.getEnumConstants();
	}
	
	public CarEngine[] getPossibleEngines() {
		return CarEngine.class.getEnumConstants();
	}
	
	public CarGearbox[] getPossibleGearboxes() {
		return CarGearbox.class.getEnumConstants();
	}
	
	public CarSeats[] getPossibleSeats() {
		return CarSeats.class.getEnumConstants();
	}
	
	public CarWheels[] getPossibleWheels() {
		return CarWheels.class.getEnumConstants();
	}
}
