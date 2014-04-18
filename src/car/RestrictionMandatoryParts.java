package car;

/**
 * Body, color, engine, gearbox, seats and wheels are mandatory.
 */
public class RestrictionMandatoryParts extends PartialRestriction {

	@Override
	protected boolean isFulfilled(ModelSpecification spec) {
		return spec.hasPart(Body.class)
				&& spec.hasPart(Color.class)
				&& spec.hasPart(Engine.class)
				&& spec.hasPart(Gearbox.class)
				&& spec.hasPart(Seats.class)
				&& spec.hasPart(Wheels.class);
	}
}
