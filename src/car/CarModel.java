package car;

import java.util.Arrays;

public abstract class CarModel {

	private final String MODEL;

	public CarModel(String model) {
		MODEL = model;
	}

	public boolean isValidSpecification(CarSpecification spec) {
		return (Arrays.asList(getPossibleAircos()).contains(spec.AIRCO)
				&& Arrays.asList(getPossibleBodies()).contains(spec.BODY)
				&& Arrays.asList(getPossibleColors()).contains(spec.COLOR)
				&& Arrays.asList(getPossibleEngines()).contains(spec.ENGINE)
				&& Arrays.asList(getPossibleGearboxes()).contains(spec.GEARBOX)
				&& Arrays.asList(getPossibleSeats()).contains(spec.SEATS)
				&& Arrays.asList(getPossibleWheels()).contains(spec.WHEELS));
	}

	public abstract CarBody[] getPossibleBodies();

	public abstract CarAirco[] getPossibleAircos();

	public abstract CarColor[] getPossibleColors();

	public abstract CarEngine[] getPossibleEngines();

	public abstract CarGearbox[] getPossibleGearboxes();

	public abstract CarSeats[] getPossibleSeats();

	public abstract CarWheels[] getPossibleWheels();

	@Override
	public String toString() {
		return MODEL;
	}

}
