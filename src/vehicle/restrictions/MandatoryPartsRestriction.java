package vehicle.restrictions;

import java.util.Set;

import vehicle.order.OrderBuilder;
import vehicle.parts.Airco;
import vehicle.parts.Part;
import vehicle.parts.PartsSet;
import vehicle.parts.Spoiler;

/**
 * All parts except airco and spoiler are mandatory.
 */
public class MandatoryPartsRestriction extends Restriction {

	private Set<Class<? extends Part>> mandatoryParts;
	
	public MandatoryPartsRestriction(OrderBuilder spec) {
		mandatoryParts = spec.getSupportedTypes();
		mandatoryParts.remove(Airco.class);
		mandatoryParts.remove(Spoiler.class);
	}
	
	@Override
	protected boolean isFulfilled(PartsSet set) {
		for (Class<? extends Part> type : mandatoryParts) {
			if(!set.contains(type)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean isPartiallyFulfilled(PartsSet set) {
		return true;
	}
}
