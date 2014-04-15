package car;

public class Wheels extends CarPart<Wheels.Options> {

	public enum Options {
		WINTER, COMFORT, SPORTS;
	}
	
	protected Wheels(Options option) {
		super(option);
	}

	@Override
	public String getAssemblyInstructions() {
		return "place the wheels on the car";
	}
}
