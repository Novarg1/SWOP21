package company;

import car.CarEngine;
import car.CarGearbox;

/**
 * Represents the workpost that is responsible for installing engine and gearbox
 */
public class DriveTrainPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
			CarEngine.class, CarGearbox.class };

	public DriveTrainPost() {
		super(installableParts);
	}

}
