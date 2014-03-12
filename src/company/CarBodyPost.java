package company;

import car.CarBody;
import car.CarColor;

/**
 * Represents the workpost that is responsible for the carbody
 */
public class CarBodyPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
		CarBody.class,
		CarColor.class
	};

	public CarBodyPost() {
		super(installableParts);
	}

}
