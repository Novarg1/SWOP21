package vehicle.order;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import company.workstations.Workstation;

import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Part;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Spoiler;
import vehicle.parts.Wheels;

/**
 * Represents model C
 */
public class ModelC extends CarModelBuilder {

	private static final int BUILDING_TIME = 70;
	private static final Part[] supportedParts = new Part[] {
		Body.SPORT,
		Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE_25DL_V6, Engine.ULTRA_3L_V8,
		Gearbox.MANUAL_6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.SPORTS, Wheels.WINTER,
		Spoiler.LOW, Spoiler.HIGH
	};
	
	@Override
	public Set<Part> getSupportedCarparts() {
		return new HashSet<>(Arrays.asList(supportedParts));
	}

	@Override
	protected int getBuildTimeFor(Class<? extends Workstation> ws) {
		return BUILDING_TIME;
	}
	
	@Override
	public String toString() {
		return "Car Model C";
	}
}
