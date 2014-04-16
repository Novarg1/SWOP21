package car;

/**
 * If a car has a sports body it must have the performance or ultra engine.
 */
public class RestricitionSportEngine extends Restriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		try {
			return ! spec.getBody().getOptionType().equals(Body.Options.SPORT)
					|| spec.getEngine().getOptionType().equals(Engine.Options.PERFORMANCE)
					|| spec.getEngine().getOptionType().equals(Engine.Options.ULTRA);
		} catch(NullPointerException e) {
			return false;
		}
	}

	@Override
	protected boolean isPartiallyFulfilled(ModelSpecification spec) {
		return !spec.engineChosen() || !spec.bodyChosen()
				|| this.isFulfilled(spec);
	}
}
