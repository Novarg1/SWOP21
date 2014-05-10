package vehicle.assemblytasks;

import vehicle.Vehicle;

public class AddCargoProtection extends CargoTask {

	public AddCargoProtection(Vehicle vehicle) {
		super(vehicle);
	}

	@Override
	public String toString() {
		return "Add cargo protection";
	}
}
