package vehicle.parts;

import company.workstations.CarBodyPost;
import company.workstations.Workstation;

public enum Body implements Carpart {

	SEDAN, BREAK, SPORT, PLATFORM, CLOSED;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return CarBodyPost.class;
	}
}