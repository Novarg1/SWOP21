package car;

/**
 * Standard carModel that accepts all possible specs
 */
public class StandardModel extends CarModel {
	
	public StandardModel () {
		super("StandardModel");
	}
	
	public CarBody[] getPossibleBodies() {
		return CarBody.values();
	}
	
	public CarAirco[] getPossibleAircos() {
		return CarAirco.values();
	}
	
	public CarColor[] getPossibleColors() {
		return CarColor.values();
	}
	
	public CarEngine[] getPossibleEngines() {
		return CarEngine.values();
	}
	
	public CarGearbox[] getPossibleGearboxes() {
		return CarGearbox.values();
	}
	
	public CarSeats[] getPossibleSeats() {
		return CarSeats.values();
	}
	
	public CarWheels[] getPossibleWheels() {
		return CarWheels.values();
	}
}
