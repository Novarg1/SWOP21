package vehicle.parts;

import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public enum Gearbox implements Part {

	MANUAL_5, MANUAL_6, MANUAL_8, AUTOMATIC_5, AUTOMATIC;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return DriveTrainPost.class;
	}
}
