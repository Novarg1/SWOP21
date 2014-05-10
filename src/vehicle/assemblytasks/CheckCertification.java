package vehicle.assemblytasks;

import vehicle.Vehicle;
import company.workstations.CertificationPost;
import company.workstations.Workstation;

public class CheckCertification extends Task {

	private boolean performed = false;
	
	public CheckCertification(Vehicle vehicle) {
		super(vehicle);
	}
	
	@Override
	public void perform() {
		performed = true;
	}

	@Override
	public boolean isPerformed() {
		return performed;
	}
	
	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return CertificationPost.class;
	}

	@Override
	public String toString() {
		return "Perform required certification checks";
	}

}
