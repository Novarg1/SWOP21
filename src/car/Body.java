package car;

public class Body extends CarPart<Body.Options> {

	public enum Options {
		SEDAN, BREAK, SPORT;
	}

	public Body(Options option) {
		super(option);
	}

	@Override
	public String getAssemblyInstructions() {
		return "install the body";
	}
}