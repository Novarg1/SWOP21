package car;

/**
 * Orderspecification for a complete car of Model C
 */
public class ModelCSpec extends OrderSpecification {

	private static final String name = "ModelC";
	private static final int BUILDING_TIME = 70;
	private static final CarPart[] supportedParts = new CarPart[] {
		Body.SPORT,
		Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE, Engine.ULTRA,
		Gearbox.MANUAL6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.SPORTS, Wheels.WINTER,
		Spoiler.LOW, Spoiler.HIGH
	};
	
	public ModelCSpec() {
		super(name, BUILDING_TIME);
	}

	@Override
	protected CarPart[] getAllSupportedParts() {
		return supportedParts;
	}
}
