package car;

import java.util.EnumSet;
import java.util.Set;

public class ModelBSpec extends ModelSpecification {

	private static final String model = "Model B";

	public ModelBSpec() {
		super(model);
	}

	@Override
	protected Set<Body> getAllBodies() {
		return EnumSet.of(Body.SEDAN, Body.BREAK,
				Body.SPORT);
	}

	@Override
	protected Set<Color> getAllColors() {
		return EnumSet.of(Color.RED, Color.BLUE,
				Color.GREEN, Color.YELLOW);
	}

	@Override
	protected Set<Engine> getAllEngines() {
		return EnumSet.of(Engine.PERFORMANCE, Engine.STANDARD,
				Engine.ULTRA);
	}

	@Override
	protected Set<Gearbox> getAllGearboxes() {
		return EnumSet.of(Gearbox.AUTOMATIC, Gearbox.MANUAL6);
	}

	@Override
	protected Set<Seats> getAllSeats() {
		return EnumSet.of(Seats.LEATHER_BLACK,
				Seats.LEATHER_WHITE, Seats.VINYL_GREY);
	}

	@Override
	protected Set<Airco> getAllAircos() {
		return EnumSet.of(Airco.MANUAL, Airco.AUTOMATIC);
	}

	@Override
	protected Set<Wheels> getAllWheels() {
		return EnumSet.of(Wheels.COMFORT, Wheels.SPORTS,
				Wheels.WINTER);
	}

	@Override
	protected Set<Spoiler> getAllSpoilers() {
		return EnumSet.of(Spoiler.LOW);
	}
}
