package car.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import car.parts.Airco;
import car.parts.Body;
import car.parts.Carpart;
import car.parts.Color;
import car.parts.Engine;
import car.parts.Gearbox;
import car.parts.Seats;
import car.parts.Spoiler;
import car.parts.Wheels;

/**
 * Represents model B
 */
public class ModelB extends CarModel {

	private static final Carpart[] supportedParts = new Carpart[] {
		Body.SEDAN, Body.BREAK, Body.SPORT,
		Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
		Engine.PERFORMANCE, Engine.STANDARD, Engine.ULTRA,
		Gearbox.AUTOMATIC, Gearbox.MANUAL6,
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
		return "Model B";
	}
}
