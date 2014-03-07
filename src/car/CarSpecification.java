package car;

public abstract class CarSpecification {
	private CarBody body;
	private CarColor color;
	private CarEngine engine;
	private CarGearbox gearbox;
	private CarSeats seats;
	private CarAirco airco;
	private CarWheels wheels;

	public CarSpecification(CarModel model, CarBody body, CarColor color,
			CarEngine engine, CarGearbox gearbox, CarSeats seats,
			CarAirco airco, CarWheels wheels) throws Exception {
		this.body = body;
		this.color = color;
		this.engine = engine;
		this.gearbox = gearbox;
		this.seats = seats;
		this.airco = airco;
		this.wheels = wheels;
		if (!model.isValidSpecification(this)) {
			throw new IllegalArgumentException("invalid specification");
		}
	}

	/**
	 * @return the body
	 */
	public CarBody getBody() {
		return body;
	}

	/**
	 * @return the color
	 */
	public CarColor getColor() {
		return color;
	}

	/**
	 * @return the engine
	 */
	public CarEngine getEngine() {
		return engine;
	}

	/**
	 * @return the gearbox
	 */
	public CarGearbox getGearbox() {
		return gearbox;
	}

	/**
	 * @return the seats
	 */
	public CarSeats getSeats() {
		return seats;
	}

	/**
	 * @return the airco
	 */
	public CarAirco getAirco() {
		return airco;
	}

	/**
	 * @return the wheels
	 */
	public CarWheels getWheels() {
		return wheels;
	}

	@Override
	public String toString() {
		return "Color: " + this.color + "\nBody: " + this.body + "\nEngine: "
				+ this.engine + "\nGearbox: " + this.gearbox + "\nSeats: "
				+ this.seats + "\nAirco: " + this.airco + "\nWheels: "
				+ this.wheels;
	}
}
