package vehicle.order;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Carpart;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Wheels;

public class ModelYBuilder extends TruckOrderBuilder {

	private static final Carpart[] supportedParts = new Carpart[] {
		Body.PLATFORM,
		Color.BLACK, Color.WHITE,
		Engine.STANDARD, Engine.HYBRID,
		Gearbox.MANUAL_8, Gearbox.AUTOMATIC,
		Seats.VINYL_BLACK, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.STANDARD, Wheels.HEAVY_DUTY
	};

	@Override
	public Set<Carpart> getSupportedCarparts() {
		return new HashSet<>(Arrays.asList(supportedParts));
	}

	@Override
	public String toString() {
		return "Truck Model Y";
	}
}
