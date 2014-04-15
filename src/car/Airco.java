package car;

public class Airco extends CarPart<Airco.Options> {

	public Airco(Options option) {
		super(option);
	}

	public enum Options {
		MANUAL,
		AUTOMATIC;
	}

	@Override
	public String getAssemblyInstructions() {
		return "install the airco";
	}

}
