package vehicle.parts;

import company.workstations.BodyPost;
import company.workstations.Workstation;

public enum Body implements Part {

	SEDAN, BREAK, SPORT, PLATFORM, CLOSED;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return BodyPost.class;
	}
}