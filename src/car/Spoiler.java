package car;

public class Spoiler extends CarPart<Spoiler.Options> {

	public enum Options {
		HIGH, LOW;
	}
	
	protected Spoiler(Options option) {
		super(option);
	}

	@Override
	public String getAssemblyInstructions() {
		return "bolt the spoiler on the car";
	}
}
