package car;

import java.util.Arrays;
import java.util.List;

//TODO complete
public class ModelCSpec extends ModelSpecification {

	private static final String model = "Model C";

	public ModelCSpec() {
		super(model);
		this.addModelSpecificationRestriction(new RestricitionSport(this));
	}

	@Override
	public int getBuildingTimePerWorkstation() {
		return 50;
	}

	@Override
	protected List<Body.Options> getAllBodyOptions() {
		return Arrays.asList(new Body.Options[] { Body.Options.SPORT });
	}

	@Override
	protected List<Color.Options> getAllColorOptions() {
		return Arrays.asList(new Color.Options[] { Color.Options.BLACK,
				Color.Options.WHITE });
	}

	@Override
	protected List<Engine.Options> getAllEngineOptions() {
		return Arrays.asList(new Engine.Options[] { Engine.Options.PERFORMANCE,
				Engine.Options.ULTRA });
	}

	@Override
	protected List<Gearbox.Options> getAllGearboxOptions() {
		return Arrays.asList(new Gearbox.Options[] { Gearbox.Options.MANUAL6 });
	}

	@Override
	protected List<Seats.Options> getAllSeatsOptions() {
		return Arrays.asList(new Seats.Options[] { Seats.Options.LEATHER_BLACK,
				Seats.Options.LEATHER_WHITE });
	}

	@Override
	protected List<Airco.Options> getAllAircoOptions() {
		return Arrays.asList(new Airco.Options[] { Airco.Options.MANUAL,
				Airco.Options.AUTOMATIC });
	}

	@Override
	protected List<Wheels.Options> getAllWheelsOptions() {
		return Arrays.asList(new Wheels.Options[] { Wheels.Options.SPORTS,
				Wheels.Options.WINTER });
	}

	@Override
	protected List<Spoiler.Options> getAllSpoilerOptions() {
		return Arrays.asList(new Spoiler.Options[] { Spoiler.Options.HIGH,
				Spoiler.Options.LOW });
	}
}
