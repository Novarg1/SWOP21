package vehicle.assemblytasks;

import company.workstations.CargoPost;
import company.workstations.Workstation;

public abstract class CargoTask extends Task {

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return CargoPost.class;
	}
}
