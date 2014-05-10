package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import vehicle.parts.Carpart;
import vehicle.parts.Color;

public class PaintJobBuilder extends CustomOrderBuilder {

	@Override
	public Set<Carpart> getSupportedCarparts() {
		Set<Carpart> result = new HashSet<>();
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
