package car;

import java.util.HashSet;
import java.util.Set;

/**
 * invariant: Van elke subklasse van carPart mag hoogstens 1 instantie in de
 * lijst van parts zitten
 */
public class Car {

	private Order order;
	private Set<CarPart> parts;

	public Car(Order order) {
		this.order = order;
		parts = new HashSet<>();
	}

	public Order getOrder() {
		return order;
	}

	public CarPart getPart(Class<? extends CarPart> type) {
		for (CarPart part : parts) {
			if(part.getClass().equals(type)) {
				return part;
			}
		}
		return null;
	}
	
	public void install(CarPart part) {
		parts.remove(getPart(part.getClass()));
		parts.add(part);
	}
	
	public boolean hasPart(Class<? extends CarPart> type) {
		return getPart(type) != null;
	}

	public boolean hasPart(CarPart part) {
		return parts.contains(part);

	}

	public boolean matchesOrder() {
		return parts.equals(order.SPECIFICATION.getParts());
	}
}
