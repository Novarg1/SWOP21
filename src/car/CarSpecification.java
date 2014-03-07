package car;

public class CarSpecification {
	public final CarBody BODY;
	public final CarColor COLOR;
	public final CarEngine ENGINE;
	public final CarGearbox GEARBOX;
	public final CarSeats SEATS;
	public final CarAirco AIRCO;
	public final CarWheels WHEELS;

	public CarSpecification(CarModel model, CarBody body, CarColor color,
			CarEngine engine, CarGearbox gearbox, CarSeats seats,
			CarAirco airco, CarWheels wheels) throws Exception {
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

	@Override
	public String toString() {
		return "Color: " + this.COLOR + "\nBody: " + this.BODY + "\nEngine: "
				+ this.ENGINE + "\nGearbox: " + this.GEARBOX + "\nSeats: "
				+ this.SEATS + "\nAirco: " + this.AIRCO + "\nWheels: "
				+ this.WHEELS;
	}
}
