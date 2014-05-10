package vehicle.parts;

import company.workstations.CarBodyPost;
import company.workstations.Workstation;

public enum Color implements Carpart {

	RED, BLUE, BLACK, WHITE, GREEN, YELLOW;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return CarBodyPost.class;
	}
}
