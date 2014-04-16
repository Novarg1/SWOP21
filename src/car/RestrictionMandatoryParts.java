package car;

/**
 * Body, color, engine, gearbox, seats and wheels are mandatory.
 */
public class RestrictionMandatoryParts extends Restriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		return spec.bodyChosen()
				&& spec.colorChosen()
				&& spec.engineChosen()
				&& spec.gearboxChosen()
				&& spec.seatsChosen()
				&& spec.wheelsChosen();
	}

	@Override
	protected boolean isPartiallyFulfilled(ModelSpecification spec) {
		return true;
	}

}
