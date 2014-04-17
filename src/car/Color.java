package car;

public enum Color implements CarPart {

	RED, BLUE, BLACK, WHITE, GREEN, YELLOW;

	@Override
	public void install(Car car) {
		car.setColor(this);
	}

	@Override
	public boolean isInstalled(Car car) {
		return this.equals(car.getColor());
	}

	@Override
	public String getAssemblyInstructions() {
		return "paint the car " +this+".";
	}
}
