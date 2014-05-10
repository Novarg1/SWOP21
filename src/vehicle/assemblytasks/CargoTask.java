package vehicle.assemblytasks;

import company.workstations.CargoPost;
import company.workstations.Workstation;
import vehicle.Vehicle;

public abstract class CargoTask extends Task {

	private boolean performed = false;
	
	protected CargoTask(Vehicle vehicle) {
		super(vehicle);
	}

	@Override
	public boolean isPerformed() {
		return performed;
	}

	@Override
	public void perform() {
		performed = true;
	}

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return CargoPost.class;
	}
}
