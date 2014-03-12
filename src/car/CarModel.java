package car;

import java.util.Arrays;

/**
 * A carmodel specifies all parts that can be installed on cars with this model
 */
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

	/**
	 * @return all possible bodies that cars of this model can have
	 */
	public abstract CarBody[] getPossibleBodies();

	/**
	 * @return all possible airco's that cars of this model can have
	 */
	public abstract CarAirco[] getPossibleAircos();

	/**
	 * @return all possible colors that cars of this model can have
	 */
	public abstract CarColor[] getPossibleColors();

	/**
	 * @return all possible engines that cars of this model can have
	 */
	public abstract CarEngine[] getPossibleEngines();

	/**
	 * @return all possible gearboxes that cars of this model can have
	 */
	public abstract CarGearbox[] getPossibleGearboxes();

	/**
	 * @return all possible seats that cars of this model can have
	 */
	public abstract CarSeats[] getPossibleSeats();

	/**
	 * @return all possible wheels that cars of this model can have
	 */
	public abstract CarWheels[] getPossibleWheels();

	@Override
	public String toString() {
		return MODEL;
	}

}
