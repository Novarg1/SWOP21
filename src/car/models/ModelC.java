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
 * Represents model C
 */
public class ModelC extends CarModel {

	private static final int BUILDING_TIME = 70;
	private static final Carpart[] supportedParts = new Carpart[] {
		Body.SPORT,
		Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE, Engine.ULTRA,
		Gearbox.MANUAL6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.SPORTS, Wheels.WINTER,
		Spoiler.LOW, Spoiler.HIGH
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
		return "Model C";
	}
}
