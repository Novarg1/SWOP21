package car;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class RestricitionSportEngine extends StrictRestriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		return !spec.hasPart(Body.SPORT)
				|| spec.hasPart(Engine.PERFORMANCE)
				|| spec.hasPart(Engine.ULTRA);
	}
}
