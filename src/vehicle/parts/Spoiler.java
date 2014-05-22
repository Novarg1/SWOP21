package vehicle.parts;

import company.workstations.AccessoriesPost;
import company.workstations.Workstation;

public enum Spoiler implements Part {

	HIGH, LOW;

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return AccessoriesPost.class;
	}
}
