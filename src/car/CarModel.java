package car;

import java.util.Arrays;

public abstract class CarModel {

	private final String MODEL;

	public CarModel(String model) {
		MODEL = model;
	}

	public boolean isValidSpecification(CarSpecification spec) {
		return (Arrays.asList(getPossibleAircos()).contains(spec.getAirco())
				&& Arrays.asList(getPossibleBodies()).contains(spec.getBody())
				&& Arrays.asList(getPossibleColors()).contains(spec.getColor())
				&& Arrays.asList(getPossibleEngines()).contains(spec.getEngine())
				&& Arrays.asList(getPossibleGearboxes()).contains(spec.getGearbox())
				&& Arrays.asList(getPossibleSeats()).contains(spec.getSeats())
				&& Arrays.asList(getPossibleWheels()).contains(spec.getWheels()));
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
