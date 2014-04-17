package car;

/**
 * If the ultra engine is selected, you can only fit the manual airco.
 */
public class RestrictionEngineAirco extends Restriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		try {
			return !spec.getEngine().equals(Engine.ULTRA)
					|| !spec.aircoChosen()
					|| spec.getAirco().equals(Airco.MANUAL);
		} catch (NullPointerException e) {
			return false;
		}
	}

	@Override
	protected boolean isPartiallyFulfilled(ModelSpecification spec) {
		return !spec.aircoChosen() || !spec.engineChosen() || isFulfilled(spec);
	}
}
