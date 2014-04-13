package company;

import car.CarPartBody;
import car.CarPartColor;

/**
 * Represents the workpost that is responsible for the carbody
 */
public class CarBodyPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
		CarPartBody.class,
		CarPartColor.class
	};

	public CarBodyPost() {
		this.id = "CARBODY";
	}

}
