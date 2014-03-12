package car;

/**
 * Represents a car
 */
public class Car {

	private CarBody body = null;
	private CarColor color = null;
	private CarEngine engine = null;
	private CarGearbox gearbox = null;
	private CarSeats seats = null;
	private CarAirco airco = null;
	private CarWheels wheels = null;

	public Car() {

	}

	@Override
	public String toString() {
		return "Body: " + this.body +
				"\nColor: " + this.color + 
				"\nEngine: " + this.engine +
				"\nGearbox: " + this.gearbox +
				"\nSeats: " + this.seats +
				"\nAirco: " + this.airco +
				"\nWheels: " + this.wheels;
	}

	/**
	 * @return true if this car has been completed and is fully functional
	 */
	public boolean isComplete() {
		return (body != null &&
				color != null &&
				engine != null &&
				gearbox != null &&
				seats != null &&
				airco != null &&
				wheels != null);
	}

	/**
	 * Installs the given part on this car
	 * @param part
	 */
	public void install(CarPart part) {
		if(part.getClass() == CarBody.class) {
			this.body = (CarBody)part;
		}
		else if(part.getClass() == CarColor.class) {
			this.color = (CarColor)part;
		}
		else if(part.getClass() == CarEngine.class) {
			this.engine = (CarEngine)part;
		}
		else if(part.getClass() == CarGearbox.class) {
			this.gearbox = (CarGearbox)part;
		}
		else if(part.getClass() == CarSeats.class) {
			this.seats = (CarSeats)part;
		}
		else if(part.getClass() == CarAirco.class) {
			this.airco = (CarAirco)part;
		}
		else if(part.getClass() == CarWheels.class) {
			this.wheels = (CarWheels)part;
		}
		throw new IllegalArgumentException();
	}

	/**
	 * @param part
	 * @return true if the given part is installed on this car
	 */
	public boolean hasPart(CarPart part) {
		if(part.getClass() == CarBody.class) {
			return this.body == (CarBody)part;
		}
		else if(part.getClass() == CarColor.class) {
			return this.color == (CarColor)part;
		}
		else if(part.getClass() == CarEngine.class) {
			return this.engine == (CarEngine)part;
		}
		else if(part.getClass() == CarGearbox.class) {
			return this.gearbox == (CarGearbox)part;
		}
		else if(part.getClass() == CarSeats.class) {
			return this.seats == (CarSeats)part;
		}
		else if(part.getClass() == CarAirco.class) {
			return this.airco == (CarAirco)part;
		}
		else if(part.getClass() == CarWheels.class) {
			return this.wheels == (CarWheels)part;
		}
		throw new IllegalArgumentException();
	}
}
