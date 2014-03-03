package car;

public class CarOrder {

	private CarBody body;
	private CarColor color;
	private CarEngine engine;
	private CarGearbox gearbox;
	private CarSeats seats;
	private CarAirco airco;
	private CarWheels wheels;

	//	private CarModelSpecification specification;

	public CarOrder(CarBody body,
			CarColor color,
			CarEngine engine,
			CarGearbox gearbox,
			CarSeats seats,
			CarAirco airco,
			CarWheels wheels)
	{
		this.body = body;
		this.color = color;
		this.engine = engine;
		this.gearbox = gearbox;
		this.seats = seats;
		this.airco = airco;
		this.wheels = wheels;
	}

	//	public void addCarModelSpecification(CarModelSpecification spec) {
	//		this.specification = spec;
	//	}

	@Override
	public String toString() {
		return "Color: " + this.color +
				"\nBody: " + this.body + 
				"\nEngine: " + this.engine +
				"\nGearbox: " + this.gearbox +
				"\nSeats: " + this.seats +
				"\nAirco: " + this.airco +
				"\nWheels: " + this.wheels;
	}

}
