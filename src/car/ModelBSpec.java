package car;

import java.util.Arrays;
import java.util.List;

public class ModelBSpec extends ModelSpecification {

	private static final String model = "Model B";

	public ModelBSpec() {
		super(model);
		this.addModelSpecificationRestriction(new RestricitionSport(this));
	}

	@Override
	public int getBuildingTimePerWorkstation() {
		return 50;
	}

	@Override
	protected List<Body.Options> getAllBodyOptions() {
		return Arrays.asList(new Body.Options[] { Body.Options.SEDAN,
				Body.Options.BREAK, Body.Options.SPORT });
	}

	@Override
	protected List<Color.Options> getAllColorOptions() {
		return Arrays
				.asList(new Color.Options[] { Color.Options.RED,
						Color.Options.BLUE, Color.Options.GREEN,
						Color.Options.YELLOW });
	}

	@Override
	protected List<Engine.Options> getAllEngineOptions() {
		return Arrays.asList(new Engine.Options[] { Engine.Options.PERFORMANCE,
				Engine.Options.STANDARD, Engine.Options.ULTRA });
	}

	@Override
	protected List<Gearbox.Options> getAllGearboxOptions() {
		return Arrays.asList(new Gearbox.Options[] { Gearbox.Options.AUTOMATIC,
				Gearbox.Options.MANUAL6 });
	}

	@Override
	protected List<Seats.Options> getAllSeatsOptions() {
		return Arrays.asList(new Seats.Options[] { Seats.Options.LEATHER_BLACK,
				Seats.Options.LEATHER_WHITE, Seats.Options.VINYL_GREY });
	}

	@Override
	protected List<Airco.Options> getAllAircoOptions() {
		return Arrays.asList(new Airco.Options[] { Airco.Options.MANUAL,
				Airco.Options.AUTOMATIC });
	}

	@Override
	protected List<Wheels.Options> getAllWheelsOptions() {
		return Arrays.asList(new Wheels.Options[] { Wheels.Options.COMFORT,
				Wheels.Options.SPORTS, Wheels.Options.WINTER });
	}

	@Override
	protected List<Spoiler.Options> getAllSpoilerOptions() {
		return Arrays.asList(new Spoiler.Options[] {Spoiler.Options.LOW});
	}
}
