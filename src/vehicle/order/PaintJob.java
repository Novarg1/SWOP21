package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import vehicle.parts.Part;
import vehicle.parts.Color;

public class PaintJob extends CustomOrderBuilder {

	@Override
	public Set<Part> getSupportedCarparts() {
		Set<Part> result = new HashSet<>();
		for (Color	color : Color.values()) {
			result.add(color);
		}
		return result;
	}

	@Override
	public String toString() {
		return "Spray-paint job";
	}
}
