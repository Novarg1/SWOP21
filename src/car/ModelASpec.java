package car;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class ModelASpec extends ModelSpecification {

	private static final String model = "Model A";
	private static final int BUILDING_TIME = 50;

	public ModelASpec() {
		super(model, BUILDING_TIME);
	}

	@Override
	protected Set<Body> getAllBodies() {
		return EnumSet.of(Body.SEDAN, Body.BREAK);
	}

	@Override
	protected Set<Color> getAllColors() {
		return EnumSet.of(Color.RED, Color.BLUE, Color.BLACK, Color.WHITE);
	}

	@Override
	protected Set<Engine> getAllEngines() {
		return EnumSet.of(Engine.PERFORMANCE, Engine.STANDARD);
	}

	@Override
	protected Set<Gearbox> getAllGearboxes() {
		return EnumSet.of(Gearbox.AUTOMATIC, Gearbox.MANUAL5, Gearbox.MANUAL6);
	}

	@Override
	protected Set<Seats> getAllSeats() {
		return EnumSet.of(Seats.LEATHER_BLACK, Seats.LEATHER_WHITE,
				Seats.VINYL_GREY);
	}

	@Override
	protected Set<Airco> getAllAircos() {
		return EnumSet.of(Airco.MANUAL, Airco.AUTOMATIC);
	}

	@Override
	protected Set<Wheels> getAllWheels() {
		return EnumSet.of(Wheels.COMFORT, Wheels.SPORTS, Wheels.WINTER);
	}

	@Override
	protected Set<Spoiler> getAllSpoilers() {
		return Collections.emptySet();
	}
}
