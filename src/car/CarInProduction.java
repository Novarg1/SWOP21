package car;

/**
 * A carInProduction object ontains a car and a carorder
 */
public class CarInProduction {

	public final Car CAR;
	public final CarOrder ORDER;
	
	public CarInProduction(Car car, CarOrder order) {
		CAR = car;
		ORDER = order;
	}
}
