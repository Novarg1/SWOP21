package company;

import car.CarBody;
import car.CarColor;

public class CarBodyPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
		CarBody.class,
		CarColor.class
	};

	public CarBodyPost() {
		super(installableParts);
	}

}
