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
	protected Set<Body.Options> getAllBodyOptions() {
		return EnumSet.of(Body.Options.SEDAN, Body.Options.BREAK);
	}

	@Override
	protected Set<Color.Options> getAllColorOptions() {
		return EnumSet.of(Color.Options.RED, Color.Options.BLUE,
				Color.Options.BLACK, Color.Options.WHITE);
	}

	@Override
	protected Set<Engine.Options> getAllEngineOptions() {
		return EnumSet.of(Engine.Options.PERFORMANCE, Engine.Options.STANDARD);
	}

	@Override
	protected Set<Gearbox.Options> getAllGearboxOptions() {
		return EnumSet.of(Gearbox.Options.AUTOMATIC, Gearbox.Options.MANUAL5,
				Gearbox.Options.MANUAL6);
	}

	@Override
	protected Set<Seats.Options> getAllSeatsOptions() {
		return EnumSet.of(Seats.Options.LEATHER_BLACK,
				Seats.Options.LEATHER_WHITE, Seats.Options.VINYL_GREY);
	}

	@Override
	protected Set<Airco.Options> getAllAircoOptions() {
		return EnumSet.of(Airco.Options.MANUAL, Airco.Options.AUTOMATIC);
	}

	@Override
	protected Set<Wheels.Options> getAllWheelsOptions() {
		return EnumSet.of(Wheels.Options.COMFORT, Wheels.Options.SPORTS,
				Wheels.Options.WINTER);
	}

	@Override
	protected Set<Spoiler.Options> getAllSpoilerOptions() {
		return Collections.emptySet();
	}
}
