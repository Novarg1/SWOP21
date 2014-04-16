package car;

import java.util.EnumSet;
import java.util.Set;

public class ModelCSpec extends ModelSpecification {

	private static final String model = "Model C";

	public ModelCSpec() {
		super(model);
	}

	@Override
	public int getBuildingTimePerWorkstation() {
		return 50;
	}

	@Override
	protected Set<Body.Options> getAllBodyOptions() {
		return EnumSet.of(Body.Options.SPORT);
	}

	@Override
	protected Set<Color.Options> getAllColorOptions() {
		return EnumSet.of(Color.Options.BLACK, Color.Options.WHITE);
	}

	@Override
	protected Set<Engine.Options> getAllEngineOptions() {
		return EnumSet.of(Engine.Options.PERFORMANCE, Engine.Options.ULTRA);
	}

	@Override
	protected Set<Gearbox.Options> getAllGearboxOptions() {
		return EnumSet.of(Gearbox.Options.MANUAL6);
	}

	@Override
	protected Set<Seats.Options> getAllSeatsOptions() {
		return EnumSet.of(Seats.Options.LEATHER_BLACK,
				Seats.Options.LEATHER_WHITE);
	}

	@Override
	protected Set<Airco.Options> getAllAircoOptions() {
		return EnumSet.of(Airco.Options.MANUAL, Airco.Options.AUTOMATIC);
	}

	@Override
	protected Set<Wheels.Options> getAllWheelsOptions() {
		return EnumSet.of(Wheels.Options.SPORTS, Wheels.Options.WINTER);
	}

	@Override
	protected Set<Spoiler.Options> getAllSpoilerOptions() {
		return EnumSet.of(Spoiler.Options.HIGH, Spoiler.Options.LOW);
	}
}
