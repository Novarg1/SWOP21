package vehicle.assemblytasks;

import company.workstations.Workstation;
import vehicle.Vehicle;
import vehicle.parts.Carpart;

/**
 * Represents the assembly task of installing one specific carpart
 */
public class InstallPart extends Task {

	private Carpart part;

	/**
	 * Constructs a new task representing the installation of the given carpart
	 * on the given vehicle.
	 */
	public InstallPart(Carpart part, Vehicle vehicle) {
		super(vehicle);
		if (part == null) {
			throw new IllegalArgumentException();
		}
		this.part = part;
	}

	@Override
	public void perform() {
		vehicle.install(part);
	}

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return part.getResponsibleWorkstation();
	}

	@Override
	public String toString() {
		return "install " + part.getClass().getName() + ": " + part;
	}

	@Override
	public boolean isPerformed() {
		return vehicle.getParts().contains(part);
	}
}
