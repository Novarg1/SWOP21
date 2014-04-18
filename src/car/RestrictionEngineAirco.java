package car;

/**
 * If the ultra engine is selected, you can only fit the manual airco.
 */
public class RestrictionEngineAirco extends StrictRestriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		return !spec.hasPart(Engine.ULTRA)
				|| !spec.hasPart(Airco.class)
				|| spec.hasPart(Airco.MANUAL);
	}
}
