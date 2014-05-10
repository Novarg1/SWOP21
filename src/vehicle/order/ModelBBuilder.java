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
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;

/**
 * Represents model B
 */
public class ModelBBuilder extends CarModelBuilder {

	private static final Carpart[] supportedParts = new Carpart[] {
		Body.SEDAN, Body.BREAK, Body.SPORT,
		Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
		Engine.PERFORMANCE_25DL_V6, Engine.STANDARD_2L_V4, Engine.ULTRA_3L_V8,
		Gearbox.AUTOMATIC_5, Gearbox.MANUAL_6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.COMFORT, Wheels.SPORTS, Wheels.WINTER,
		Spoiler.LOW
	};

	@Override
	public Set<Carpart> getSupportedCarparts() {
		return new HashSet<>(Arrays.asList(supportedParts));
	}

	@Override
	public String toString() {
		return "Car Model B";
	}
}
