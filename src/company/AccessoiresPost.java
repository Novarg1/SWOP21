package company;

import car.CarPartAirco;
import car.CarPartSeats;
import car.CarPartWheels;

/**
 * Represents the workpost that is responsible for installing accesoires
 */
public class AccessoiresPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
		CarPartSeats.class,
		CarPartAirco.class,
		CarPartWheels.class
	};

	public AccessoiresPost() {
		this.id = "ACCESSOIRES";
	}

}