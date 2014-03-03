package car;

public abstract class CarModel {
	
	private final String MODEL;
	
	public CarModel(String model) {
		MODEL = model;
	}
	
	public abstract CarBody[] getPossibleBodies();
	
	public abstract CarAirco[] getPossibleAircos();
	
	public abstract CarColor[] getPossibleColors();
	
	public abstract CarEngine[] getPossibleEngines();
	
	public abstract CarGearbox[] getPossibleGearboxes();
	
	public abstract CarSeats[] getPossibleSeats();
	
	public abstract CarWheels[] getPossibleWheels();
	
	@Override
	public String toString() {
		return MODEL;
	}

}
