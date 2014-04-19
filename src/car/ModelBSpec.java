package car;

/**
 * Orderspecification for a complete car of Model B
 */
public class ModelBSpec extends OrderSpecification {

	private static String name = "ModelB";
	private static final CarPart[] supportedParts = new CarPart[] {
		Body.SEDAN, Body.BREAK, Body.SPORT,
		Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
		Engine.PERFORMANCE, Engine.STANDARD, Engine.ULTRA,
		Gearbox.AUTOMATIC, Gearbox.MANUAL6,
		Seats.LEATHER_BLACK, Seats.LEATHER_WHITE, Seats.VINYL_GREY,
		Airco.MANUAL, Airco.AUTOMATIC,
		Wheels.COMFORT, Wheels.SPORTS, Wheels.WINTER,
		Spoiler.LOW
	};

	public ModelBSpec() {
		super(name);
	}
	
	@Override
	protected CarPart[] getAllSupportedParts() {
		return supportedParts;
	}
}
