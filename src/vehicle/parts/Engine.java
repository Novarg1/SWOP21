package vehicle.parts;

import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public enum Engine implements Carpart {

	STANDARD_2L_V4, PERFORMANCE_25DL_V6, ULTRA_3L_V8, STANDARD, HYBRID;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return DriveTrainPost.class;
	}
}
