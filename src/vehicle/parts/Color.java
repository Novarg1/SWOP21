package vehicle.parts;

import company.workstations.BodyPost;
import company.workstations.Workstation;

public enum Color implements Part {

	RED, BLUE, BLACK, WHITE, GREEN, YELLOW;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return BodyPost.class;
	}
}
