package car;

/**
 * Orderspecification for a complete car of Model A
 */
public class ModelASpec extends OrderSpecification {

	private static final String name = "ModelA";
	private static final int BUILDING_TIME = 50;
	private static final CarPart[] supportedParts = new CarPart[] {
		Body.SEDAN, Body.BREAK,
		Color.RED, Color.BLUE, Color.BLACK, Color.WHITE,
		Engine.PERFORMANCE, Engine.STANDARD,
		Gearbox.AUTOMATIC, Gearbox.MANUAL5, Gearbox.MANUAL6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.COMFORT, Wheels.SPORTS, Wheels.WINTER
	};

	public ModelASpec() {
		super(name, BUILDING_TIME);
	}

	@Override
	protected CarPart[] getAllSupportedParts() {
		return supportedParts;
	}
}
