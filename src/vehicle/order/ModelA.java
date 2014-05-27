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
import vehicle.parts.Wheels;

public class ModelA extends CarModelBuilder {

	private static final int BUILDING_TIME = 50;
	private static final Part[] supportedParts = new Part[] {
		Body.SEDAN, Body.BREAK,
		Color.RED, Color.BLUE, Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE_25DL_V6, Engine.STANDARD_2L_V4,
		Gearbox.AUTOMATIC_5, Gearbox.MANUAL_5, Gearbox.MANUAL_6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.COMFORT, Wheels.SPORTS, Wheels.WINTER
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
		return "Car Model A";
	}
}
