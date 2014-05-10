package vehicle.parts;

import company.workstations.AccessoiresPost;
import company.workstations.Workstation;

public enum Seats implements Carpart {

	LEATHER_BLACK, LEATHER_WHITE, VINYL_GREY, VINYL_BLACK;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return AccessoiresPost.class;
	}
}
