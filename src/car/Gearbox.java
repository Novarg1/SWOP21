package car;

public class Gearbox extends CarPart<Gearbox.Options> {

	public enum Options {
		MANUAL5, MANUAL6, AUTOMATIC;
	}
	
	protected Gearbox(Options option) {
		super(option);
	}
	
	public String getAssemblyInstructions() {
		return "install the gearbox";
	}
}
