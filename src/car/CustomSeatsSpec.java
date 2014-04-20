package car;

import java.util.HashSet;
import java.util.Set;

import car.parts.Carpart;
import car.parts.Seats;

public class CustomSeatsSpec extends CustomSpec {

	@Override
	protected Set<Carpart> getSupportedCarparts() {
		Set<Carpart> result = new HashSet<>();
		for (Seats seats : Seats.values()) {
			result.add(seats);
		}
		return result;
	}

	@Override
	public String toString() {
		return "Custom Seats";
	}

}
