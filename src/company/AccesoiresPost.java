package company;

import car.CarAirco;
import car.CarSeats;
import car.CarWheels;

/**
 * Represents the workpost that is responsible for installing accesoires
 */
public class AccesoiresPost extends WorkStation {

	private static final Class<?>[] installableParts = new Class[] {
		CarSeats.class,
		CarAirco.class,
		CarWheels.class
	};

	public AccesoiresPost() {
		super(installableParts);
	}

}