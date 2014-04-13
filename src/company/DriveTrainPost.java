package company;

import car.CarPartEngine;
import car.CarPartGearbox;

/**
 * Represents the workpost that is responsible for installing engine and gearbox
 */
public class DriveTrainPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
			CarPartEngine.class, CarPartGearbox.class };

	public DriveTrainPost() {
		super(installableParts);
		this.id = "DRIVETRAIN";
	}

}
