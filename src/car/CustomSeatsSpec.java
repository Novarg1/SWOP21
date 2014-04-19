package car;

/**
 * orderspecification for installing custom seats in a car.
 */
public class CustomSeatsSpec extends CustomsSpec {

	private static final String name = "Custom Seats";
	
	public CustomSeatsSpec() {
		super(name);
	}

	@Override
	protected CarPart[] getAllSupportedParts() {
		return Seats.values();
	}
}
