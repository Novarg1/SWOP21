package car;

public abstract class CarModelSpecification 
{	
	private CarBody body;
	private CarColor color;
	private CarEngine engine;
	private CarGearbox gearbox;
	private CarSeats seats;
	private CarAirco airco;
	private CarWheels wheels;
	
	public CarModelSpecification(CarBody body,
									CarColor color,
									CarEngine engine,
									CarGearbox gearbox,
									CarSeats seats,
									CarAirco airco,
									CarWheels wheels) throws Exception
							{
								this.body = body;
								this.color = color;
								this.engine = engine;
								this.gearbox = gearbox;
								this.seats = seats;
								this.airco = airco;
								this.wheels = wheels;
								if(this.isValidSpecification()==false)
								{
									throw new Exception();
								}
							}
	
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
	
	public abstract boolean isValidSpecification();
}
