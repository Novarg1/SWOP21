package vehicle.restrictions;

import java.util.Set;

import vehicle.order.OrderBuilder;
import vehicle.parts.Carpart;
import vehicle.parts.CarpartsSet;

/**
 * All chosen parts must be supported by the order-type
 */
public class SupportedPartsRestriction extends StrictRestriction {

	private Set<Carpart> supportedParts;
	
	public SupportedPartsRestriction(OrderBuilder spec) {
		supportedParts = spec.getSupportedCarparts();
	}
	
	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		for (Carpart part : set) {
			if (!supportedParts.contains(part)) {
				return false;
			}
		}
		return true;
	}
}
