package vehicle.assemblytasks;

import company.workstations.Workstation;
import vehicle.parts.Part;

/**
 * Represents the assembly task of installing one specific carpart
 */
public class InstallPart extends Task {

	private Part part;

	/**
	 * Constructs a new task representing the installation of the given carpart
	 * on the given vehicle.
	 */
	public InstallPart(Part part) {
		if (part == null) {
			throw new IllegalArgumentException();
		}
		this.part = part;
	}

	@Override
	public Class<? extends Workstation> getResponsibleWorkstation() {
		return part.getResponsibleWorkstation();
	}

	@Override
	public String toString() {
		return "install " + part.getClass().getName() + ": " + part;
	}
}
