package car;

/**
 * A carspecification contains specific parts that a car should have
 */
public class CarSpecification {
	public final CarBody BODY;
	public final CarColor COLOR;
	public final CarEngine ENGINE;
	public final CarGearbox GEARBOX;
	public final CarSeats SEATS;
	public final CarAirco AIRCO;
	public final CarWheels WHEELS;

	/**
	 * Creates a carspecification with the given specifics
	 * @param model
	 * @param body
	 * @param color
	 * @param engine
	 * @param gearbox
	 * @param seats
	 * @param airco
	 * @param wheels
	 */
	public CarSpecification(CarModel model, CarBody body, CarColor color,
			CarEngine engine, CarGearbox gearbox, CarSeats seats,
			CarAirco airco, CarWheels wheels) {
		this.BODY = body;
		this.COLOR = color;
		this.ENGINE = engine;
		this.GEARBOX = gearbox;
		this.SEATS = seats;
		this.AIRCO = airco;
		this.WHEELS = wheels;
		if (!model.isValidSpecification(this)) {
			throw new IllegalArgumentException("invalid specification");
		}
	}

	/**
	 * @param part
	 * @return the specific part of the given class that this specification
	 *         contains
	 */
	public CarPart get(Class<?> part) {
		if (part.equals(AIRCO.getClass()))
			return AIRCO;
		if (part.equals(BODY.getClass()))
			return BODY;
		if (part.equals(COLOR.getClass()))
			return COLOR;
		if (part.equals(ENGINE.getClass()))
			return ENGINE;
		if (part.equals(GEARBOX.getClass()))
			return GEARBOX;
		if (part.equals(SEATS.getClass()))
			return SEATS;
		if (part.equals(WHEELS.getClass()))
			return WHEELS;
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return "Color: " + this.COLOR + "\nBody: " + this.BODY + "\nEngine: "
				+ this.ENGINE + "\nGearbox: " + this.GEARBOX + "\nSeats: "
				+ this.SEATS + "\nAirco: " + this.AIRCO + "\nWheels: "
				+ this.WHEELS;
	}
}