package car;

public class Color extends CarPart<Color.Options> {

	public enum Options {
		RED, BLUE, BLACK, WHITE, GREEN, YELLOW;
	}
	
	public Color(Options option) {
		super(option);
	}

	@Override
	public String getAssemblyInstructions() {
		return "paint the car " + this;
	}
}
