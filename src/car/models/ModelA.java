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
import car.parts.Wheels;

/**
 * Represents model A.
 */
public class ModelA extends CarModel {

	private static final int BUILDING_TIME = 50;
	private static final Carpart[] supportedParts = new Carpart[] {
		Body.SEDAN, Body.BREAK,
		Color.RED, Color.BLUE, Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE, Engine.STANDARD,
		Gearbox.AUTOMATIC, Gearbox.MANUAL5, Gearbox.MANUAL6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.COMFORT, Wheels.SPORTS, Wheels.WINTER
	};

	@Override
	public Set<Carpart> getSupportedCarparts() {
		return new HashSet<>(Arrays.asList(supportedParts));
	}

	@Override
	public int getBuildingTimePerWorkstation() {
		return BUILDING_TIME;
	}
	
	@Override
	public String toString() {
		return "Model A";
	}
}
