package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import vehicle.parts.Carpart;
import vehicle.parts.Seats;

public class CustomSeatsSpec extends CustomSpec {

	@Override
	public Set<Carpart> getSupportedCarparts() {
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
