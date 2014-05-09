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

public class ModelASpec extends ModelSpec {

	private static final int BUILDING_TIME = 50;
	private static final Carpart[] supportedParts = new Carpart[] {
		Body.SEDAN, Body.BREAK,
		Color.RED, Color.BLUE, Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE_25DL_V6, Engine.STANDARD_2L_V4,
		Gearbox.AUTOMATIC_5, Gearbox.MANUAL_5, Gearbox.MANUAL_6,
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
