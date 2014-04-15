package car;

public class Engine extends CarPart<Engine.Options> {

	public enum Options {
		STANDARD, PERFORMANCE, ULTRA;
	}

	public Engine(Options option) {
		super(option);
	}

	@Override
	public String getAssemblyInstructions() {
		return "place the engine in the car";
	}
}
