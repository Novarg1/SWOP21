package vehicle.restrictions;

import java.util.Set;

import vehicle.order.OrderBuilder;
import vehicle.parts.Part;
import vehicle.parts.PartsSet;

/**
 * All chosen parts must be supported by the order-type
 */
public class SupportedPartsRestriction extends StrictRestriction {

	private Set<Part> supportedParts;
	
	public SupportedPartsRestriction(OrderBuilder spec) {
		supportedParts = spec.getSupportedCarparts();
	}
	
	@Override
	protected boolean isFulfilled(PartsSet set) {
		for (Part part : set) {
			if (!supportedParts.contains(part)) {
				return false;
			}
		}
		return true;
	}
}
