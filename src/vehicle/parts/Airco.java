
package vehicle.parts;

import company.workstations.AccessoriesPost;
import company.workstations.Workstation;

public enum Airco implements Part {

	MANUAL, AUTOMATIC;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return AccessoriesPost.class;
	}
}
