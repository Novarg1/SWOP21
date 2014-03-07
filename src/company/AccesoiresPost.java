package company;

import car.CarAirco;
import car.CarSeats;
import car.CarWheels;

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