package vehicle.assemblytasks;

import vehicle.Vehicle;

public class InstallToolStorage extends CargoTask {

	public InstallToolStorage(Vehicle vehicle) {
		super(vehicle);
	}

	@Override
	public String toString() {
		return "Install tool storage";
	}
}
