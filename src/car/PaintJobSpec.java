package car;

import java.util.HashSet;
import java.util.Set;

import car.parts.Carpart;
import car.parts.Color;

public class PaintJobSpec extends CustomSpec {

	@Override
	protected Set<Carpart> getSupportedCarparts() {
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
