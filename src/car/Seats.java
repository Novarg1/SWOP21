package car;

public enum Seats implements CarPart {

	LEATHER_BLACK, LEATHER_WHITE, VINYL_GREY;

	@Override
	public void install(Car car) {
		car.setSeats(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getSeats());
	}

	@Override
	public String getAssemblyInstructions() {
		return "install "+this+" seats";
	}
}
