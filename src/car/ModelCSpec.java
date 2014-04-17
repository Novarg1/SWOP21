package car;

import java.util.EnumSet;
import java.util.Set;

public class ModelCSpec extends ModelSpecification {

	private static final String model = "Model C";
	private static final int BUILDING_TIME = 50;

	public ModelCSpec() {
		super(model, BUILDING_TIME);
	}

	@Override
	public int getBuildingTimePerWorkstation() {
		return 50;
	}

	@Override
	protected Set<Body> getAllBodies() {
		return EnumSet.of(Body.SPORT);
	}

	@Override
	protected Set<Color> getAllColors() {
		return EnumSet.of(Color.BLACK, Color.WHITE);
	}

	@Override
	protected Set<Engine> getAllEngines() {
		return EnumSet.of(Engine.PERFORMANCE, Engine.ULTRA);
	}

	@Override
	protected Set<Gearbox> getAllGearboxes() {
		return EnumSet.of(Gearbox.MANUAL6);
	}

	@Override
	protected Set<Seats> getAllSeats() {
		return EnumSet.of(Seats.LEATHER_BLACK,
				Seats.LEATHER_WHITE);
	}

	@Override
	protected Set<Airco> getAllAircos() {
		return EnumSet.of(Airco.MANUAL, Airco.AUTOMATIC);
	}

	@Override
	protected Set<Wheels> getAllWheels() {
		return EnumSet.of(Wheels.SPORTS, Wheels.WINTER);
	}

	@Override
	protected Set<Spoiler> getAllSpoilers() {
		return EnumSet.of(Spoiler.HIGH, Spoiler.LOW);
	}
}
