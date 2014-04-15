package car;

public class Seats extends CarPart<Seats.Options> {

	public enum Options {
		LEATHER_BLACK, LEATHER_WHITE, VINYL_GREY;
	}

	protected Seats(Options option) {
		super(option);
	}

	@Override
	public String getAssemblyInstructions() {
		// TODO Auto-generated method stub
		return "pick up the seats and insert them in the car";
	}
}
