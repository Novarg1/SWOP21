package car;
/*
 * Everything goes on a pimpmobile
 */
public class PimpMobileSpecification extends CarModelSpecification
{

	public PimpMobileSpecification(CarBody body, CarColor color,
			CarEngine engine, CarGearbox gearbox, CarSeats seats,
			CarAirco airco, CarWheels wheels) throws Exception {
		super(body, color, engine, gearbox, seats, airco, wheels);
		// TODO Auto-generated constructor stub
	}

	public boolean isValidSpecification()
	{
		return true;
	}
}
