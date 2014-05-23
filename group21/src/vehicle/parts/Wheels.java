package vehicle.parts;

import company.workstations.AccessoriesPost;
import company.workstations.Workstation;

public enum Wheels implements Part {

	WINTER, COMFORT, SPORTS, STANDARD, HEAVY_DUTY;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return AccessoriesPost.class;
	}
}
