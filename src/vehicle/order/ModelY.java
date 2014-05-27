package vehicle.order;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import company.workstations.BodyPost;
import company.workstations.CertificationPost;
import company.workstations.Workstation;
import vehicle.parts.Airco;
import vehicle.parts.Body;
import vehicle.parts.Part;
import vehicle.parts.Color;
import vehicle.parts.Engine;
import vehicle.parts.Gearbox;
import vehicle.parts.Seats;
import vehicle.parts.Wheels;

public class ModelY extends TruckOrderBuilder {

	private static final Part[] supportedParts = new Part[] {
		Body.PLATFORM,
		Color.BLACK, Color.WHITE,
		Engine.STANDARD, Engine.HYBRID,
		Gearbox.MANUAL_8, Gearbox.AUTOMATIC,
		Seats.VINYL_BLACK, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.STANDARD, Wheels.HEAVY_DUTY
	};

	@Override
	public Set<Part> getSupportedCarparts() {
		return new HashSet<>(Arrays.asList(supportedParts));
	}

	@Override
	protected int getBuildTimeFor(Class<? extends Workstation> ws) {
		if(ws.equals(BodyPost.class)) {
			return 120;
		}
		if(ws.equals(CertificationPost.class)) {
			return 45;
		}
		return super.getBuildTimeFor(ws);
	}
	
	@Override
	public String toString() {
		return "Truck Model Y";
	}
}
