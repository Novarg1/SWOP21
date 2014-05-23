package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import vehicle.parts.Part;
import vehicle.parts.Seats;

public class CustomSeats extends CustomOrderBuilder {

	@Override
	public Set<Part> getSupportedCarparts() {
		Set<Part> result = new HashSet<>();
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
