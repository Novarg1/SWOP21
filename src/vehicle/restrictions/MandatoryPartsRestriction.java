package vehicle.restrictions;

import java.util.Set;

import vehicle.order.OrderSpecification;
import vehicle.parts.Airco;
import vehicle.parts.Carpart;
import vehicle.parts.CarpartsSet;
import vehicle.parts.Spoiler;

/**
 * All parts except airco and spoiler are mandatory.
 */
public class MandatoryPartsRestriction extends PartialRestriction {

	private Set<Class<? extends Carpart>> mandatoryParts;
	
	public MandatoryPartsRestriction(OrderSpecification spec) {
		mandatoryParts = spec.getSupportedTypes();
		mandatoryParts.remove(Airco.class);
		mandatoryParts.remove(Spoiler.class);
	}
	
	@Override
	protected boolean isFulfilled(CarpartsSet set) {
		for (Class<? extends Carpart> type : mandatoryParts) {
			if(!set.containsType(type)) {
				return false;
			}
		}
		return true;
	}
}
