package vehicle.assemblytasks;

import company.workstations.CertificationPost;
import company.workstations.Workstation;

public class CheckCertification extends Task {
	
	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return CertificationPost.class;
	}

	@Override
	public String toString() {
		return "Perform required certification checks";
	}

}
