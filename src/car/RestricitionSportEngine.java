package car;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class RestricitionSportEngine extends Restriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		try {
			return !spec.getBody().equals(Body.SPORT)
					|| spec.getEngine().equals(Engine.PERFORMANCE)
					|| spec.getEngine().equals(Engine.ULTRA);
		} catch (NullPointerException e) {
			return false;
		}
	}

	@Override
	protected boolean isPartiallyFulfilled(ModelSpecification spec) {
		return !spec.engineChosen() || !spec.bodyChosen()
				|| this.isFulfilled(spec);
	}
}
